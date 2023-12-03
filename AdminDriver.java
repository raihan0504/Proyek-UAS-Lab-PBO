import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDriver extends Driver {

    private Admin akun;
    private ListBarang listBarang;
    private ArrayList<Transaksi> listTransaksi;
    private Transaksi transaksi;
    private boolean approved;
    private Customer customer;

    /**
     * Konstruktor kelas AdminDriver. Menginisialisasi objek Admin dan objek
     * ListBarang.
     *
     * @param akun Objek Admin yang akan digunakan dalam kelas.
     */
    public AdminDriver(Admin akun) {
        this.akun = akun;
        this.listBarang = new ListBarang();
        this.transaksi = new Transaksi(customer, listTransaksi);
    }

    /**
     * Metode untuk menampilkan menu operasi administrator.
     */
    public void Menu() {
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
                    transaksi.approveTransaction();
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

}
