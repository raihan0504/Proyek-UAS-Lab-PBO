import java.util.Scanner;

/**
 * Kelas utama Main digunakan untuk menjalankan program e-commerce sederhana.
 * Program ini mendukung dua peran pengguna: pelanggan (customer) dan admin.
 * Pengguna dapat login sebagai customer atau admin untuk mengakses menu yang sesuai.
 * Setelah login, program akan menampilkan menu yang relevan dengan peran pengguna.
 * Program ini menggunakan objek CustomerDriver dan AdminDriver untuk mengelola interaksi dengan pelanggan dan admin.
 * Juga, AuthManager digunakan untuk mengelola login admin.
 *
 * @author Raihan, Della, Azron
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membuat objek Customer
        Customer customer = new Customer();
        customer.setId("customer1"); // Atur ID pelanggan jika diperlukan

        // Membuat objek Admin
        Admin admin = new Admin();
        admin.setId("admin1"); // Atur ID admin jika diperlukan

        // Membuat objek CustomerDriver dan AdminDriver
        CustomerDriver customerDriver = new CustomerDriver(customer);
        AdminDriver adminDriver = new AdminDriver(admin);
        
        // Menampilkan menu sesuai peran pengguna
        System.out.print("Masukkan peran Anda (customer/admin): ");
        String peran = scanner.next();

        if (peran.equalsIgnoreCase("customer")) {
            customerMenu(customerDriver);
        } else if (peran.equalsIgnoreCase("admin")) {
            // Memeriksa login admin sebelum memasuki menu admin
            if (!AuthManager.isAdminLoggedIn()) {
                AuthManager.loginAdmin();
            }

            if (AuthManager.isAdminLoggedIn()) {
                adminMenu(adminDriver);
            } else {
                System.out.println("Login admin gagal. Silakan coba lagi.");
            }
        } else {
            System.out.println("Peran tidak valid.");
        }

        
    }

    private static void customerMenu(CustomerDriver customerDriver) { //@param customerDriver Objek CustomerDriver yang akan digunakan.
        customerDriver.Menu();
    }

    private static void adminMenu(AdminDriver adminDriver) { //@param adminDriver Objek AdminDriver yang akan digunakan.
        adminDriver.Menu();
    }
}
