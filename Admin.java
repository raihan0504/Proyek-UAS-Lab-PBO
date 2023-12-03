/**
 * Kelas Admin merupakan turunan dari kelas Akun yang merepresentasikan akun
 * administrator dalam sistem. Kelas ini memiliki akses ke informasi identitas
 * dan kata sandi akun administrator.
 */
public class Admin extends Akun {

    /**
     * Kata sandi default untuk akun administrator.
     */
    private String password;

    /**
     * Konstruktor default untuk kelas Admin. Menginisialisasi ID dengan "admin"
     * dan kata sandi dengan nilai default "admin123".
     */
    public Admin() {
        super("admin");
        this.password = "admin123";
    }

    /**
     * Konstruktor kelas Admin dengan parameter ID dan kata sandi.
     *
     * @param id       ID untuk akun administrator.
     * @param password Kata sandi untuk akun administrator.
     */
    public Admin(String id, String password) {
        super(id);
        this.password = password;
    }

    /**
     * Mengatur kata sandi baru untuk akun administrator.
     *
     * @param password Kata sandi baru.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Mendapatkan kata sandi akun administrator.
     *
     * @return Kata sandi akun administrator.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mengganti ID akun administrator.
     *
     * @param id ID baru untuk akun administrator.
     */
    @Override
    public void setId(String id) {
        super.setId(id);
    }

    /**
     * Mendapatkan ID akun administrator.
     *
     * @return ID akun administrator.
     */
    @Override
    public String getId() {
        return super.getId();
    }
}
