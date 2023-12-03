/**
 * Kelas Cod merupakan implementasi dari metode pembayaran "Cash On Delivery" (COD),
 * yang memungkinkan pembayaran dilakukan saat barang diterima oleh pembeli.
 * Kelas ini mengextends kelas MetodeBayar dan mengimplementasikan logika pembayaran COD.
 */
public class Cod extends MetodeBayar{

    /**
     * Implementasi metode pembayaran menggunakan Cash On Delivery (COD).
     * Menampilkan pesan yang menggambarkan proses pembayaran COD.
     */
    @Override
    public void bayar() {
        // Implement the Cash On Delivery payment logic here
        System.out.println("Processing Cash On Delivery payment...");
        // Add any specific implementation details for COD payment
    }
}
