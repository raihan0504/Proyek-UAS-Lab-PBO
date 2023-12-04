import java.util.Scanner;

public class CustomerDriver extends Driver {
    private Customer akun;
    private Admin admin;
    private Transaksi transaksi;
    private ListBarang barang;

    /**
     * Konstruktor untuk CustomerDriver.
     * Menginisialisasi objek Customer, Transaksi, dan ListBarang.
     *
     * @param akun Objek Customer yang akan digunakan dalam driver.
     */
    public CustomerDriver(Customer akun) {
        this.akun = akun;
        this.transaksi = new Transaksi(akun, null);
        this.barang = new ListBarang();
    }

     /**
     * Metode untuk menampilkan menu aksi dan mengelola input pengguna.
     */
    public void Menu() {
        Scanner scan = new Scanner(System.in);
        int input;

        do {
            System.out.println("\nMenu");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah Barang ke Keranjang");
            System.out.println("3. Checkout");
            System.out.println("4. cetak Invoice");
            System.out.println("5. Login admin");
            System.out.println("6. Exit");
            System.out.print("Masukkan Pilihan: ");
            input = scan.nextInt();

            switch (input) {
                case 1:
                    barang.tampilBarang();
                    break;
                case 2:
                    transaksi.tambahBarang(barang);;
                    break;
                case 3:
                    transaksi.checkout(barang);
                    break;
                case 4:
                    Invoice.printInvoice(transaksi);
                    break;
                case 5:
                    AdminDriver adminDriver = new AdminDriver(admin);
                    adminDriver.Menu();
                    break;
                case 6:
                    System.out.println("Terima Kasih telah menggunakan program kami");
                    break;
                default:
                    System.out.println("Input tidak valid");
                    break;
            }
        } while (input != 6);

    }
}
