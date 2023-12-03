import java.util.ArrayList;

class Invoice {
    private Transaksi transaksi;
    private MetodeBayar metodeBayar; // Use the abstract class type
    private Customer customer;
    private ArrayList<Barang> barang;

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
