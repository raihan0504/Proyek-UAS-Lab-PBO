/**
 * Kelas Qris merupakan implementasi dari kelas abstrak MetodeBayar yang digunakan untuk melakukan pembayaran
 * menggunakan metode QRIS
 * Kelas ini menyediakan implementasi konkret dari metode bayar(), yang akan dipanggil saat pelanggan memilih
 * untuk melakukan pembayaran menggunakan QRIS.
 * Implementasi ini mencakup logika khusus pembayaran QRIS dan memberikan pesan bahwa pembayaran QRIS sedang diproses.
 * /
public class Qris extends MetodeBayar {

    @Override
    public void bayar() {
        // Implement the QRIS payment logic here
        System.out.println("Processing QRIS payment...");
        // Add any specific implementation details for QRIS payment
    }
}
