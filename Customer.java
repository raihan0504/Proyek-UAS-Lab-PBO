import java.util.ArrayList;

public class Customer extends Akun {
    private String password;
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;

    /**
     * Konstruktor pertama untuk kelas Customer.
     * Menginisialisasi ID dengan "user", password dengan "user123",
     * serta membuat objek Keranjang dan ArrayList Invoice.
     */
    public Customer() {
        super("user");
        this.password = "user123";
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    /**
     * Konstruktor kedua untuk kelas Customer.
     * Menginisialisasi ID dengan nilai yang diberikan,
     * serta membuat objek Keranjang dan ArrayList Invoice.
     *
     * @param id ID pelanggan yang akan diatur.
     */
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

    /**
     * Metode untuk menambahkan transaksi ke daftar invoice selesai.
     * Setelah transaksi selesai, keranjang dikosongkan.
     *
     * @param transaksi Transaksi yang akan ditambahkan ke daftar invoice.
     */
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
