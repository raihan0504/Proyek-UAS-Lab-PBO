import java.util.ArrayList;

/**
 * Kelas Customer merupakan representasi dari pelanggan dalam sistem, yang dapat
 * melakukan berbagai tindakan seperti menambahkan transaksi, mengelola keranjang,
 * dan melihat riwayat transaksi selesai.
 */
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
    public Customer(String id) { //@param id ID pelanggan yang akan ditetapkan.
        super(id);
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    // Getter untuk password
    public String getPassword() {
        return password; //@return Kata sandi pelanggan.
    }

    // Setter untuk password
    public void setPassword(String password) { //@param password Kata sandi baru untuk pelanggan.
        this.password = password;
    }

    // Implementasi metode tambahTransaksi
    public void tambahTransaksi(Transaksi transaksi) { //@param transaksi Transaksi yang akan ditambahkan ke daftar invoice selesai
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
        return super.getId(); //@return ID pelanggan.
    }

    // Override dari kelas Akun
    @Override
    public void setId(String id) { //@param id ID baru untuk pelanggan.
        super.setId(id);
    }

}
