import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

     public static void printInvoice(Transaksi transaksi) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("invoice.txt"))) {
            writer.println("===== Invoice =====");
            writer.println("Transaction ID: " + transaksi.getTransactionID());
            writer.println("Date: " + transaksi.getDate());
            writer.println("Approved: " + (transaksi.isApproved() ? "Yes" : "No"));
            writer.println("===== Items =====");

            for (Barang barang : transaksi.getBarang()) {
                writer.println("Kode Barang: " + barang.getKodeBarang());
                writer.println("Nama Barang: " + barang.getNamaBarang());
                writer.println("Harga: " + barang.getHargaBarang());
                writer.println("Jumlah: " + barang.getStokBarang());
                writer.println("------------------------");
            }

            writer.println("Total Harga: " + transaksi.hitungTotalHarga());
            writer.println("===================");

            System.out.println("Invoice has been generated: invoice.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
