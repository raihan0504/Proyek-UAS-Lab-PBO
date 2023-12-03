import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Invoice {
    /**
     * Objek Transaksi yang diasosiasikan dengan invoice.
     */
    private Transaksi transaksi;

    /**
     * Objek MetodeBayar yang digunakan dalam transaksi.
     * Menggunakan tipe kelas abstrak untuk mendukung berbagai metode pembayaran.
     */
    private MetodeBayar metodeBayar;

    /**
     * Objek Customer yang melakukan transaksi dan akan menerima invoice.
     */
    private Customer customer;

    /**
     * Daftar barang yang dibeli dalam transaksi.
     */
    private ArrayList<Barang> barang;

    // Getter and Setter methods

    /**
     * Mendapatkan objek Transaksi yang terkait dengan invoice.
     *
     * @return Objek Transaksi.
     */
    public Transaksi getTransaksi() {
        return transaksi;
    }

    /**
     * Mengatur objek Transaksi untuk invoice.
     *
     * @param transaksi Objek Transaksi yang akan diatur.
     */
    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    /**
     * Mendapatkan objek MetodeBayar yang digunakan dalam transaksi.
     *
     * @return Objek MetodeBayar.
     */
    public MetodeBayar getPembayaran() {
        return metodeBayar;
    }

    /**
     * Mengatur objek MetodeBayar untuk invoice.
     *
     * @param metodeBayar Objek MetodeBayar yang akan diatur.
     */
    public void setPembayaran(MetodeBayar metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    /**
     * Mendapatkan objek Customer yang melakukan transaksi.
     *
     * @return Objek Customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Mengatur objek Customer untuk invoice.
     *
     * @param customer Objek Customer yang akan diatur.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Mendapatkan daftar barang yang dibeli dalam transaksi.
     *
     * @return Daftar barang yang dibeli.
     */
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    /**
     * Mengatur daftar barang untuk invoice.
     *
     * @param barang Daftar barang yang akan diatur.
     */
    public void setBarang(ArrayList<Barang> barang) {
        this.barang = barang;
    }

    /**
     * Metode untuk mencetak invoice ke file "invoice.txt".
     *
     * @param transaksi Objek Transaksi yang akan dicetak dalam invoice.
     */
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
