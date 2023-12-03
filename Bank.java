import java.util.Scanner;

public class Bank extends MetodeBayar{

    private String bankName;
    private String accountNumber;
    Scanner scan = new Scanner(System.in);

    public Bank(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

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
