import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Kelas AdminDriver merupakan kelas yang menangani interaksi dari seorang administrator
 * dalam sistem. Kelas ini menyediakan berbagai fungsi seperti menambah, menghapus,
 * dan mengedit barang, menyetujui transaksi, serta menangani login customer.
 */
public class AdminDriver extends Driver {
    private Admin akun;
    private ListBarang listBarang;
    private ArrayList<Transaksi> listTransaksi;
    private boolean approved;
    private Customer customer;

    public AdminDriver(Admin akun) { //@param akun Objek Admin yang akan digunakan.
        this.akun = akun;
        this.listBarang = new ListBarang();
    }

    public void Menu() { //Metode untuk menampilkan menu admin dan menangani input dari administrator.
        Scanner scan = new Scanner(System.in);
        int input = 0;

        do {
            System.out.println("Admin Menu:");
            System.out.println("\n1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Tampilkan Barang");
            System.out.println("5. Approved Transaksi");
            System.out.println("6. Login Customer");
            System.out.println("7. Exit");
            System.out.print("Masukkan Pilihan: ");

            // Tambahkan penanganan karakter newline di sini
            if (scan.hasNextInt()) {
                input = scan.nextInt();
                scan.nextLine(); // Membaca karakter newline yang tersisa
            } else {
                System.out.println("Input harus berupa angka. Coba lagi.");
                scan.nextLine(); // Membaca karakter newline yang tersisa
                continue;
            }

            switch (input) {
                case 1:
                    listBarang.tambahBarang();
                    System.out.println("Barang berhasil ditambahkan!");
                    break;
                case 2:
                    listBarang.hapusBarang();
                    System.out.println("Barang berhasil dihapus!");
                    break;
                case 3:
                    listBarang.editBarang();
                    System.out.println("Barang berhasil diubah!");
                    break;
                case 4:
                    listBarang.tampilBarang();
                    break;
                case 5:
                    approveTransactionsFromFile();
                    break;
                case 6:
                    CustomerDriver customerDriver = new CustomerDriver(customer);
                    customerDriver.Menu();
                    break;
                case 7:
                    System.out.println("exit dari program!");
                    break;
                default:
                    System.out.println("Input tidak valid. Coba lagi.");
                    break;
            }

        } while (input != 7);

        scan.close();
    }

    private void viewTransaksi() {
        if (listTransaksi.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("List of Transactions:");
            for (Transaksi transaksi : listTransaksi) {
                System.out.println("Transaction ID: " + listTransaksi.indexOf(transaksi));
                System.out.println("Total Items: " + transaksi.getBarang().size());
                System.out.println("Total Amount: " + calculateTotalAmount(transaksi));
                System.out.println("Status: " + (transaksi.isApproved() ? "Approved" : "Pending Approval"));
                System.out.println("---------------------------");
            }
        }
    }

    private int calculateTotalAmount(Transaksi transaksi) {
        int totalAmount = 0;
        for (Barang barang : transaksi.getBarang()) {
            totalAmount += barang.getHargaBarang() * barang.getStokBarang();
        }
        return totalAmount;
    }

    /**
     * Metode untuk menyetujui transaksi dari file dan menghapusnya dari daftar transaksi
     * yang menunggu persetujuan.
     */
    public void approveTransactionsFromFile() {
        List<String> approvalStatusList = loadPendingApprovalTransactions();
    
        if (approvalStatusList.isEmpty()) {
            System.out.println("No transactions available for approval.");
            return;
        }
    
        System.out.println("List of Transactions awaiting approval:");
        for (String approvalStatus : approvalStatusList) {
            String[] parts = approvalStatus.split(",");
            if (parts.length >= 2) {  // Check if the array has at least 2 elements
                String transactionId = parts[0];
                String approvalStatusStr = parts[1];
        
                System.out.println("Transaction ID: " + transactionId);
                System.out.println("Status: " + (approvalStatusStr.equals("1") ? "Approved" : "Pending Approval"));
                System.out.println("---------------------------");
            } else {
                System.out.println("Invalid approval status format: " + approvalStatus);
            }
        }   
        approveTransactions(approvalStatusList); // Pass the list to the method
    }
    


    private void approveSelectedTransaction(List<String> pendingApprovalList) { //@param pendingApprovalList Daftar transaksi yang menunggu persetujuan.
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the Transaction ID to approve (or 'exit' to go back): ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            return;
        }

        try {
            int transactionId = Integer.parseInt(input);
            if (transactionId >= 0 && transactionId < pendingApprovalList.size()) {
                // Menyetujui transaksi yang dipilih
                String selectedTransaction = pendingApprovalList.get(transactionId);
                saveApprovalStatus(selectedTransaction);
                System.out.println("Transaction approved successfully.");
                // Menghapus transaksi dari file pending approval setelah disetujui
                removeTransactionFromPendingApproval(transactionId);
            } else {
                System.out.println("Invalid Transaction ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Transaction ID.");
        }
    }

    private void removeTransactionFromPendingApproval(int transactionId) {
        // Menghapus transaksi dari file transactions_pending_approval.txt
        List<String> pendingApprovalList = loadPendingApprovalTransactions();
        pendingApprovalList.remove(transactionId);

        // Menyimpan kembali sisa transaksi yang menunggu persetujuan ke file
        try (PrintWriter writer = new PrintWriter("transactions_pending_approval.txt")) {
            for (String transactionDetails : pendingApprovalList) {
                writer.println(transactionDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> loadPendingApprovalTransactions() {
        // Membaca transaksi yang menunggu persetujuan dari file
        // transactions_pending_approval.txt
        List<String> pendingApprovalList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions_pending_approval.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pendingApprovalList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pendingApprovalList;
    }

    private void approveTransactions(List<String> approvalStatusList) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter the Transaction ID to approve (or 'exit' to go back): ");
        String input = scanner.nextLine();
    
        if (input.equalsIgnoreCase("exit")) {
            return;
        }
    
        try {
            int transactionId = Integer.parseInt(input);
            if (transactionId >= 0 && transactionId < approvalStatusList.size()) {
                // Approve the selected transaction
                String selectedTransaction = approvalStatusList.get(transactionId);
                loadPendingApprovalTransactions(selectedTransaction, "1"); // Set approval status to "Yes"
                System.out.println("Transaction approved successfully.");
            } else {
                System.out.println("Invalid Transaction ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Transaction ID.");
        }
    }
    

    private void saveApprovalStatus(String selectedTransaction) { //@param selectedTransaction String yang berisi informasi transaksi yang dipilih.
        // Memisahkan TransactionID dan Approved dari string
        String[] parts = selectedTransaction.split(",");
        String transactionId = parts[0];
        String isApproved = parts[1];

        // Menyimpan status persetujuan ke dalam file approval_status.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("approval_status.txt", true))) {
            writer.write(transactionId + "," + isApproved);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
   
    private void loadPendingApprovalTransactions(String selectedTransaction, String approvalStatus) {
        List<String> pendingApprovalList = loadPendingApprovalTransactions();

        // Find and update the selected transaction
        for (int i = 0; i < pendingApprovalList.size(); i++) {
            String transaction = pendingApprovalList.get(i).split(",")[0];
            if (transaction.equals(selectedTransaction)) {
                // Update the approval status
                pendingApprovalList.set(i, selectedTransaction + "," + approvalStatus);
                break;
            }
        }

        // Save the updated transactions back to the file
        try (PrintWriter writer = new PrintWriter("transactions_pending_approval.txt")) {
            for (String transaction : pendingApprovalList) {
                writer.println(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
