import java.util.Scanner;

/**
 * Kelas Bank merupakan implementasi dari metode pembayaran yang menggunakan
 * transfer bank untuk melakukan pembayaran.
 */
public class Bank extends MetodeBayar{

    private String bankName;
    private String accountNumber;
    Scanner scan = new Scanner(System.in);

    /**
     * Konstruktor untuk membuat objek Bank dengan informasi nama bank dan nomor rekening.
     *
     * @param bankName       Nama bank untuk pembayaran.
     * @param accountNumber  Nomor rekening untuk pembayaran.
     */
    public Bank(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    /**
     * Implementasi metode pembayaran menggunakan transfer bank.
     * Pengguna diminta untuk memasukkan nama bank dan nomor rekening,
     * kemudian informasi pembayaran ditampilkan.
     */
    public void bayar() {
        System.out.print("Masukkan Nama Bank: ");
        bankName = scan.nextLine();
        System.out.println("Masukkan No.Rekening: ");
        accountNumber = scan.nextLine();

        System.out.println("Processing Bank payment...");
        System.out.println("Bank: " + bankName);
        System.out.println("Account Number: " + accountNumber);
    }
}
