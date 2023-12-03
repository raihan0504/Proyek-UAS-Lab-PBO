import java.util.ArrayList;

public class Customer extends Akun {
    /**
     * Kata sandi untuk akun pelanggan.
     */
    private String password;

    /**
     * Objek Keranjang untuk menyimpan barang yang akan dibeli oleh pelanggan.
     */
    private Keranjang keranjang;

    /**
     * Daftar invoice yang telah selesai atau transaksi yang telah dilakukan.
     */
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
        this.invoiceSelesai = new ArrayList<>();
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
        this.invoiceSelesai = new ArrayList<>();
    }

    /**
     * Mendapatkan kata sandi akun pelanggan.
     *
     * @return Kata sandi akun pelanggan.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mengatur kata sandi baru untuk akun pelanggan.
     *
     * @param password Kata sandi baru.
     */
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

    /**
     * Override dari metode getId() di kelas Akun.
     *
     * @return ID pelanggan.
     */
    @Override
    public String getId() {
        return super.getId();
    }

    /**
     * Override dari metode setId() di kelas Akun.
     *
     * @param id ID baru untuk pelanggan.
     */
    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
