import java.util.ArrayList;

/**
 * Kelas Invoice merepresentasikan faktur pembelian yang terkait dengan suatu transaksi.
 * Faktur ini mencakup informasi tentang transaksi, metode pembayaran, pelanggan, dan daftar barang yang dibeli.
 */
class Invoice {
    private Transaksi transaksi;
    private MetodeBayar metodeBayar; //Metode pembayaran yang digunakan untuk transaksi ini
    private Customer customer;
    private ArrayList<Barang> barang; //Daftar barang yang terdapat dalam transaksi ini.

    // Getter and Setter methods

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public MetodeBayar getPembayaran() {
        return metodeBayar;
    }

    public void setPembayaran(MetodeBayar metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public void setBarang(ArrayList<Barang> barang) {
        this.barang = barang;
    }
}
