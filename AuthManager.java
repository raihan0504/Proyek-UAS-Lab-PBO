import java.util.Scanner;

public class AuthManager {
    private static boolean adminLoggedIn = false;

    /**
     * Metode untuk melakukan login sebagai admin.
     * Menggunakan Scanner untuk menerima input username dan password.
     */
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

    /**
     * Metode untuk melakukan logout sebagai admin.
     * Mengubah status login admin menjadi false.
     */
    public static void logoutAdmin() {
        adminLoggedIn = false;
        System.out.println("Logout berhasil dari akun admin.");
    }

    /**
     * Metode untuk memeriksa apakah admin sudah login.
     *
     * @return True jika admin sudah login, False jika belum.
     */
    public static boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }

  /**
     * Logika otentikasi admin 
     *
     * @param username Username yang dimasukkan untuk login.
     * @param password Password yang dimasukkan untuk login.
     * @return True jika otentikasi berhasil, False jika gagal.
     */
    private static boolean isValidAdmin(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }
}
