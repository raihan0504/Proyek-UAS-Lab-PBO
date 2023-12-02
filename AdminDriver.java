import java.util.ArrayList;
import java.util.Scanner;

public class AdminDriver extends Driver{
    private Admin akun;
    private ListBarang listBarang;
    private ArrayList<Transaksi> listTransaksi;

    public AdminDriver (Admin akun, ListBarang listBarang, ArrayList<Transaksi> listTransaksi){
        this.akun = akun;
        this.listBarang = listBarang;
        this.listTransaksi = listTransaksi;
    }

    public void Menu(){
        Scanner scan = new Scanner(System.in);
        String input;

        do {
            System.out.println("\n1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Tampilkan Barang");
            System.out.println("5. Exit");
            System.out.print("Masukkan Pilihan: ");
            input = scan.next();

            switch (input) {
                case "1":
                    listBarang.tambahBarang();
                    break;
                case "2":
                    listBarang.hapusBarang();;
                    break;
                case "3":
                    listBarang.editBarang();
                    break;
                case "4": 
                    listBarang.tampilBarang();
                    break;
                case "5":
                    System.out.println("Keluar dari program");
                    break;
                default:
                    System.out.println("Inpu tidak valid");
                    break;
            }
        } while (!input.equals("5"));
        scan.close();
    }
}
