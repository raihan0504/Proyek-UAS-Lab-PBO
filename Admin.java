public class Admin extends Akun {
    private String password;

    /**
     * Konstruktor default untuk objek Admin. Menginisialisasi ID dengan nilai
     * "admin" dan kata sandi dengan nilai "admin123".
     */
    public Admin(){
       super("admin");
       this.password = "admin123";
    }

     /**
     * Konstruktor parameter untuk objek Admin. Menginisialisasi ID dengan nilai
     * yang diberikan dan kata sandi dengan nilai yang diberikan.
     *
     * @param id       ID untuk akun administrator.
     * @param password Kata sandi untuk akun administrator.
     */
    public Admin(String id,String password){
        super(id);
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    
}
