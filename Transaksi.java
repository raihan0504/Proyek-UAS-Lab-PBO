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

    public Transaksi(Customer akun, ArrayList arrayList) {
        this.akun = akun;
        this.barang = new ArrayList<>();
        this.historyTransaksi = new ArrayList<>();
        this.approved =false;
    }

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
    
    public void AdminApprove() {
        if (AuthManager.isAdminLoggedIn()) {
            System.out.println("Transaksi disetujui oleh admin");
            approved = true;
            saveApprovalStatus();
        } else {
            System.out.println("Anda harus login sebagai admin untuk melakukan approval.");
        }
    }

    public void adminLogout() {
        AuthManager.logoutAdmin();
        System.out.println("Anda telah logout dari akun admin.");
    }

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
                    bayar(new Bank("BSI","123456"));
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
             approved = false;  // Reset approval status
           
        }
        
        kurangiStokBarang(listBarang);
    }

    

    private void kurangiStokBarang(ListBarang listBarang) {
        for (Barang barangKeranjang : barang) {
            Barang barangListBarang = listBarang.cariBarang(barangKeranjang.getKodeBarang());
    
            if (barangListBarang != null) {
                // // Mengurangi stok barang di ListBarang
                // barangListBarang.kurangiStok(barangKeranjang.getStokBarang());
                
                // Update the stock in the file
                updateStockInFile(barangListBarang);
            }
        }
    }
    
    private void updateStockInFile(Barang updatedBarang) {
        String fileName = "Barang.txt";
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(updatedBarang.getKodeBarang())) {
                    // Update the stock in the current line
                    String[] parts = line.split(",");
                    int currentStock = Integer.parseInt(parts[3]); 
                    int updatedStock = currentStock - updatedBarang.getStokBarang();
                    parts[3] = String.valueOf(updatedStock);
                    line = String.join(",", parts);
                }
                lines.add(line);  // Add the line (modified or not) to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    private void simpanDetailTransaksi() {
        String fileName = "transactions_pending_approval.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("TransactionID: " + getTransactionID());
            writer.newLine();
            writer.write("Tanggal: " + getDate());
            writer.newLine();
            writer.write("Total Barang: " + barang.size());
            writer.newLine();
            // Menyimpan detail transaksi seperti yang dilakukan sebelumnya
    
            writer.write("Total Harga: " + hitungTotalHarga());
            writer.newLine();
            writer.write("Approved: " + (approved ? "1" : "0"));
            writer.newLine();
            writer.write("--------------------------------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private int hitungTotalHarga() {
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

    public void tampilHistoryTransaksiFromFile() {
        String fileName = "transactions_history.txt";  // Change the file name as per your actual file
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("TransactionID:")) {
                    // Assuming the file format includes TransactionID and Total Harga
                    String transactionID = line.split(":")[1].trim();
                    String totalHargaLine = reader.readLine();
                    int totalHarga = Integer.parseInt(totalHargaLine.split(":")[1].trim());
    
                    // Print transaction details
                    System.out.println("TransactionID: " + transactionID);
                    System.out.println("Total Harga: " + totalHarga);
    
                    // Print other details or update your logic accordingly based on your file format
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Barang> getBarang() {
        return barang;
    }

    // Add a method to get the date of the transaction
    private String getDate() {
        // Implement this method to get the date of the transaction
        return "DD-MM-YYYY";  // Replace with the actual date
    }

    public boolean isApproved(){
        return approved;
    }


    private void saveApprovalStatus() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("approval_status.txt", true))) {
            // Format: TransactionID,Approved
            writer.write(akun.getId() + "," + (approved ? "1" : "0"));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadApprovalStatus() {
        List<String> approvalStatusList = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("approval_status.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                approvalStatusList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return approvalStatusList;
    }
    

    private String getTransactionID() {
        // Implementasi ini akan tergantung pada cara Anda ingin menghasilkan ID transaksi.
        // Sebagai contoh, kita bisa menggunakan timestamp.
        long timestamp = System.currentTimeMillis();
        return "TRX" + timestamp;
    }

    private String getTransactionDetails() {
        // Mendapatkan detail transaksi dalam format yang diinginkan
        // Implementasikan sesuai kebutuhan proyek Anda
        // Sebagai contoh, kita hanya mencetak detail barang dalam transaksi
        StringBuilder detailsBuilder = new StringBuilder();
        for (Barang b : barang) {
            detailsBuilder.append("Kode Barang: ").append(b.getKodeBarang()).append(", ");
            detailsBuilder.append("Nama Barang: ").append(b.getNamaBarang()).append(", ");
            detailsBuilder.append("Harga: ").append(b.getHargaBarang()).append(", ");
            detailsBuilder.append("Jumlah: ").append(b.getStokBarang()).append("\n");
        }
        return detailsBuilder.toString();
    }

    private void saveTransactionDetails() {
        // Simpan detail transaksi ke dalam file
        String fileName = "transactions_pending_approval.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Format: TransactionID,Details
            writer.write(getTransactionID() + "," + getTransactionDetails());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
