import java.util.Scanner;

public class CustomerDriver extends Driver{
    private Customer akun;
    private Transaksi transaksi;
    private ListBarang barang;

    public CustomerDriver (Customer akun, Transaksi transaksi, ListBarang barang){
        this.akun = akun;
        this.transaksi = transaksi;
        this.barang = barang;
    }

    @Override
    public void Menu(){
        Scanner scan = new Scanner(System.in);
        String input;

        do{
            System.out.println("\nMenu");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah Barang ke Keranjang");
            System.out.println("3. Checkout");
            System.out.println("4. Lihar History Transaksi");
            System.out.println("5. Exit");
            System.out.print("Masukkan Pilihan: ");
            input = scan.next();

            switch (input) {
                case "1":
                    barang.tampilBarang();
                    break;
                case "2":
                    transaksi.tambahBarang(akun, barang);
                    break;
                case "3":
                    transaksi.checkout(akun);
                    break;
                case "4":
                    transaksi.tampilHistoryTransaksi(akun);
                    break;
                case "5":
                    System.out.println("Terima Kasih telah menggunakan program kami");
                    break;
                default:
                    System.out.println("Input tidak valid");
                    break;
            }
        } while (!input.equals("5"));
        scan.close();
    }
}
