import java.util.ArrayList;

public class Customer extends Akun {
    private Keranjang keranjang;
    private ArrayList<invoice> invoiceSelesai;
    
    public Customer(){
        super("lijen");
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<invoice>();
    }

    public Customer(String id){
        super(id);
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<invoice>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
