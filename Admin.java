public class Admin extends Akun {
    private String password;
    
    public Admin(){
       super("Admin");
       this.password = "admin";
    }

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
