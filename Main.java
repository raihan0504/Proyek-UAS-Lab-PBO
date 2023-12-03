import java.util.Scanner;

/**
 * Kelas Main adalah kelas utama yang berisi metode main untuk menjalankan program.
 */
public class Main {
    /**
     * Metode utama program yang akan dieksekusi saat program dijalankan.
     *
     * @param args Argumen yang dapat diterima dari baris perintah (command-line arguments).
     */
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

    /**
     * Menampilkan menu untuk pengguna dengan peran pelanggan (customer).
     *
     * @param customerDriver Objek CustomerDriver yang akan menangani operasi pelanggan.
     */
    private static void customerMenu(CustomerDriver customerDriver) {
        customerDriver.Menu();
    }

    /**
     * Menampilkan menu untuk pengguna dengan peran admin.
     *
     * @param adminDriver Objek AdminDriver yang akan menangani operasi admin.
     */
    private static void adminMenu(AdminDriver adminDriver) {
        adminDriver.Menu();
    }
}
