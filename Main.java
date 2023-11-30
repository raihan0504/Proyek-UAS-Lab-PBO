
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListBarang listBarang = new ListBarang();
        Scanner scan = new Scanner(System.in);
        String input;

        do {
            System.out.println("\n1. Tambah Barang");
            System.out.println("2. Tampilkan Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Exit");
            System.out.print("Masukkan pilihan: ");
            input = scan.next();

            switch (input) {
                case "1":
                    listBarang.tambahBarang();
                    break;
                case "2":
                    listBarang.tampilBarang();
                    break;
                case "3":
                    listBarang.editBarang();
                    break;
                case "4":
                    listBarang.hapusBarang();
                    break;
                case "5":
                    System.out.println("Program akan berhenti.");
                    break;
                default:
                    System.out.println("Input tidak valid.");
            }
        } while (!input.equals("5"));

        scan.close();
    }
}
