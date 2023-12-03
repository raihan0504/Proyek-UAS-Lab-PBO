import java.util.Scanner;

/**
 * Kelas AuthManager menyediakan fungsi untuk mengelola proses otentikasi dan otorisasi
 * admin dalam sistem.
 */
public class AuthManager {
    private static boolean adminLoggedIn = false;

    // Metode untuk melakukan login sebagai admin
    public static void loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        // Logika otentikasi admin (gantilah dengan logika otentikasi yang sesuai)
        if (isValidAdmin(username, password)) {
            adminLoggedIn = true;
            System.out.println("Login berhasil sebagai admin.");
        } else {
            System.out.println("Login gagal. Cek kembali username dan password.");
        }
    }

    // Metode untuk melakukan logout sebagai admin
    public static void logoutAdmin() {
        adminLoggedIn = false;
        System.out.println("Logout berhasil dari akun admin.");
    }

    // Metode untuk memeriksa apakah admin sudah login
    public static boolean isAdminLoggedIn() {
        return adminLoggedIn; //@return true jika admin sudah login, false jika tidak.
    }

    /**
     * Logika otentikasi admin (contoh sederhana, gantilah dengan logika yang sesuai).
     *
     * @param username Username yang dimasukkan oleh pengguna.
     * @param password Password yang dimasukkan oleh pengguna.
     * @return true jika otentikasi sukses, false jika gagal.
     */
    private static boolean isValidAdmin(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }
}
