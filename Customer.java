import java.util.ArrayList;

public class Customer extends Akun {
    private String password;
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;

    // Konstruktor pertama
    public Customer() {
        super("user");
        this.password = "user123";
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    // Konstruktor kedua
    public Customer(String id) {
        super(id);
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    // Getter untuk password
    public String getPassword() {
        return password;
    }

    // Setter untuk password
    public void setPassword(String password) {
        this.password = password;
    }

    // Implementasi metode tambahTransaksi
    public void tambahTransaksi(Transaksi transaksi) {
        Invoice invoiceBaru = new Invoice();
        invoiceBaru.setTransaksi(transaksi);
        invoiceBaru.setCustomer(this);

        // Tambahkan invoice ke daftar invoice selesai
        invoiceSelesai.add(invoiceBaru);

        // Kosongkan keranjang setelah transaksi selesai
        keranjang.clearKeranjang();
    }

    // Override dari kelas Akun
    @Override
    public String getId() {
        return super.getId();
    }

    // Override dari kelas Akun
    @Override
    public void setId(String id) {
        super.setId(id);
    }

}
