import java.util.Scanner;

public class Bank extends MetodeBayar{

    private String bankName;
    private String accountNumber;
    Scanner scan = new Scanner(System.in);

     /**
     * Konstruktor kelas Bank dengan parameter nama bank dan nomor rekening.
     *
     * @param bankName       Nama bank untuk diatur.
     * @param accountNumber  Nomor rekening untuk diatur.
     */
    public Bank(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
    public Bank(){
        
    }

    /**
     * Metode bayar yang diimplementasikan dari MetodeBayar.
     * Meminta input nama bank dan nomor rekening dari pengguna, dan menampilkan informasi pembayaran.
     */
    @Override
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
