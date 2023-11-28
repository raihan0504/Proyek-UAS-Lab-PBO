/**
 * Abstrak class driver yang menghubungkan login admin dan customer
 */
public abstract class Driver {
    protected CustomerDriver customerDriver;
    protected AdminDriver adminDriver;

    /**
     * Konstruktor untuk kelas driver
     * 
     * @param customerDriver
     * @param adminDriver
     */
    public Driver(CustomerDriver customerDriver, AdminDriver adminDriver){
        this.customerDriver = customerDriver;
        this.adminDriver = adminDriver;
    }

    /**
     * Metode abstrak untuk login customer
     * 
     * @param id    Id customer
     * @param password Password customer
     */
    public abstract void customerLogin(String id, String password);
    /**
     * Metode abstrak untuk login admin
     * 
     * @param id    Id admin
     * @param password  Password admin
     */
    public abstract void adminLogin(String id, String password);
} 
