/**
 * Kelas abstrak MetodeBayar menyediakan metode abstrak untuk melakukan proses pembayaran.
 * Kelas ini dirancang untuk menjadi dasar bagi berbagai jenis metode pembayaran yang ada.
 * Setiap implementasi MetodeBayar yang konkret harus menyediakan implementasi khusus dari metode bayar().
 * Sebagai kelas abstrak, tidak dapat diinstansiasi secara langsung.
 */
public abstract class MetodeBayar {

    /**
     * Metode abstrak yang harus diimplementasikan oleh setiap jenis metode pembayaran.
     * Metode ini akan digunakan untuk melakukan proses pembayaran sesuai dengan aturan yang berlaku.
     */
    public abstract void bayar();
}
