import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transaksi {
    private Customer akun;
    private List<Barang> barang;
    private List<Transaksi> historyTransaksi;
    private boolean approved;
    private AuthManager authManager;

    /**
     * Konstruktor untuk membuat objek Transaksi.
     *
     * @param akun        Objek Customer yang melakukan transaksi.
     * @param arrayList   Daftar barang yang akan dibeli dalam transaksi.
     */
    public Transaksi(Customer akun, ArrayList arrayList) {
        this.akun = akun;
        this.barang = new ArrayList<>();
        this.historyTransaksi = new ArrayList<>();
        this.authManager = authManager;
    }

    /**
     * Menambahkan barang ke dalam keranjang belanja transaksi.
     *
     * @param listBarang Objek ListBarang yang digunakan untuk menampilkan daftar barang.
     */
    public void tambahBarang(ListBarang listBarang) {
        Scanner scan = new Scanner(System.in);

        listBarang.tampilBarang();

        System.out.print("Masukkan kode barang yang ingin ditambahkan ke keranjang: ");
        String kodeBarang = scan.next();

        Barang barangDitambahkan = listBarang.cariBarang(kodeBarang);

        if (barangDitambahkan != null) {
            System.out.print("Masukkan jumlah barang yang ingin ditambahkan: ");
            int jumlah = scan.nextInt();

            if (jumlah > 0 && jumlah <= barangDitambahkan.getStokBarang()) {
                // Add the specified quantity to the cart
                Barang barangKeranjang = new Barang(
                        barangDitambahkan.getKodeBarang(),
                        barangDitambahkan.getNamaBarang(),
                        barangDitambahkan.getHargaBarang(),
                        jumlah
                );
                barang.add(barangKeranjang);
                barangDitambahkan.kurangiStok(jumlah); // Decrease stock based on the chosen quantity
                System.out.println("Barang berhasil ditambahkan ke keranjang!");
            } else {
                System.out.println("Jumlah barang tidak valid atau stok tidak mencukupi.");
            }
        } else {
            System.out.println("Barang dengan kode tersebut tidak ditemukan");
        }
    }
    
    /**
     * Metode yang digunakan untuk memberikan persetujuan transaksi oleh admin.
     */
    public void AdminApprove() {
        if (AuthManager.isAdminLoggedIn()) {
            System.out.println("Transaksi disetujui oleh admin");
            approved = true;
            saveApprovalStatus(null);
        } else {
            System.out.println("Anda harus login sebagai admin untuk melakukan approval.");
        }
    }

    /**
     * Metode untuk logout dari akun admin.
     */
    public void adminLogout() {
        AuthManager.logoutAdmin();
        System.out.println("Anda telah logout dari akun admin.");
    }

    /**
     * Metode untuk melakukan proses checkout pada transaksi.
     *
     * @param listBarang Objek ListBarang yang digunakan untuk mengurangi stok barang.
     */
    public void checkout(ListBarang listBarang) {
        if (barang.isEmpty()) {
            System.out.println("Keranjang belanja kosong");
        } else {
            System.out.println("Checkout berhasil!");
            System.out.println("Detail Transaksi:");
            System.out.println("Total Barang: " + barang.size());

            int totalHarga = 0;
            for (Barang b : barang) {
                System.out.println("Kode Barang: " + b.getKodeBarang());
                System.out.println("Nama Barang: " + b.getNamaBarang());
                System.out.println("Harga: " + b.getHargaBarang());
                System.out.println("Jumlah: " + b.getStokBarang());
                totalHarga += b.getHargaBarang() * b.getStokBarang();
            }
            System.out.println("Total Harga: " + totalHarga);

            System.out.println("\nMetode Pembayaran:");
            System.out.println("1. QRIS");
            System.out.println("2. Bank Transfer");
            System.out.println("3. Cash on Delivery (COD)");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Pilih metode pembayaran (1-3): ");
            int metodePembayaran = scanner.nextInt();

            // Mengurangi stok barang di ListBarang
            kurangiStokBarang(listBarang);

            switch (metodePembayaran) {
                case 1:
                    bayar(new Qris());
                    break;
                case 2:
                    bayar(new Bank());
                    break;
                case 3:
                    bayar(new Cod());
                    break;
                default:
                    System.out.println("Metode pembayaran tidak valid");
            }
             // Menyimpan detail transaksi ke dalam file
             simpanDetailTransaksi();

             System.out.println("Menunggu persetujuan admin...");           
        }
        
        kurangiStokBarang(listBarang);
    }

   
    private void kurangiStokBarang(ListBarang listBarang) {
        for (Barang barangKeranjang : barang) {
            Barang barangListBarang = listBarang.cariBarang(barangKeranjang.getKodeBarang());
    
            if (barangListBarang != null) {
                // Mengurangi stok barang di ListBarang
                barangListBarang.kurangiStok(barangKeranjang.getStokBarang());
            }
        }
    }
    
    private void simpanDetailTransaksi() {
        String fileName = "transactions_pending_approval.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(getTransactionID()+ ",1");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    int hitungTotalHarga() {
        int totalHarga = 0;
        for (Barang b : barang) {
            totalHarga += b.getHargaBarang() * b.getStokBarang();
        }
        return totalHarga;
    }
   

    private void bayar(Bank bank) {
    }

    private void bayar(Qris qris) {
    }

    private void bayar(MetodeBayar metodePembayaran) {
        metodePembayaran.bayar();
    }

    public void approveTransaction() {
        Scanner scanner = new Scanner(System.in);
    
        if (AuthManager.isAdminLoggedIn()) {
            System.out.print("Do you want to approve the transaction? (yes/no): ");
            String approvalInput = scanner.next();
    
            if (approvalInput.equalsIgnoreCase("yes")) {
                System.out.println("Transaction approved by admin");
                approved = true;
            } else if (approvalInput.equalsIgnoreCase("no")) {
                System.out.println("Transaction not approved by admin");
                approved = false;
                // Optionally, you can handle the case when the admin decides not to approve.
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } else {
            System.out.println("You must log in as an admin to approve the transaction.");
        }
    } 

     /**
     * Metode untuk mendapatkan daftar barang dalam transaksi.
     *
     * @return Daftar barang dalam transaksi.
     */
    public List<Barang> getBarang() {
        return barang;
    }

   
    String getDate() {
        return "DD-MM-YYYY"; 
    }

    public boolean isApproved(){
        return approved;
    }


    void saveApprovalStatus(Customer akun2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions_pending_approval.txt", true))) {
            if (akun != null) {
                // Format: CustomerID,Approved
                writer.write(akun.getId() + "," + (approved ? "1" : "0"));
                writer.newLine();
            } else {
                System.out.println("Customer is not set. Unable to save approval status.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadApprovalStatus() {
        List<String> approvalStatusList = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions_pending_approval.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                approvalStatusList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return approvalStatusList;
    }

    public void approveTransactionFromFile() {
        List<String> approvalStatusList = loadApprovalStatus();

        System.out.println("List of Transactions Pending Approval:");
        for (String approvalStatus : approvalStatusList) {
            String[] parts = approvalStatus.split(",");
            if (parts.length == 2) {
                String customerID = parts[0];
                boolean isApproved = parts[1].equals("1");

                System.out.println("Transaction for Customer ID: " + customerID);
                System.out.println("Approval Status: " + (isApproved ? "Approved" : "Not Approved"));
                System.out.println("--------------------------------------");

                // Optionally, perform actions based on the approval status
                if (isApproved) {
                    // Process the approved transaction
                    approved = true;
                } else {
                    // Process the not approved transaction
                    // ...
                }
            } else {
                System.out.println("Invalid format in the approval status file.");
            }
        }
    }
    

    String getTransactionID() {
        // Implementasi ini akan tergantung pada cara Anda ingin menghasilkan ID transaksi.
        // Sebagai contoh, kita bisa menggunakan timestamp.
        long timestamp = System.currentTimeMillis();
        return "TRX" + timestamp;
    }
}
