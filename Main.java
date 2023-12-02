import java.util.Scanner;

public class Main{
    private Akun akun;
    private Driver driverAkun;

    public void Login(){
        Scanner scan = new Scanner(System.in);
        Admin admin = new Admin();
        Customer customer = new Customer();
        int input;

        do {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            System.out.print("Masukkan pilihan: ");
            input = scan.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Masukkan ID: ");
                    String id = scan.next();
                    System.out.println("Masukkan Password: ");
                    String password = scan.next();

                    if (id.equals(admin.getId()) && password.equals(admin.getPassword())){
                        
                    }
                    else if(id.equals(customer.getId()) && password.equals(customer.getPassword())){

                    }
                    
                    break;
                case 2:
                System.out.println("Terima kasih telah menggunakan program kami");
                break;
                default:
                    break;
            }
        }while(input>0 && input < 2);
    }
    public static void main(String[] args){
        
    }
}
