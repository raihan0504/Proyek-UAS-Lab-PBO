/**
 * Kelas abstrak MetodeBayar menyediakan metode abstrak untuk melakukan proses pembayaran.
 * Kelas ini dirancang untuk menjadi dasar bagi berbagai jenis metode pembayaran yang ada.
 * Setiap implementasi MetodeBayar yang konkret harus menyediakan implementasi khusus dari metode bayar().
 * Sebagai kelas abstrak, tidak dapat diinstansiasi secara langsung.
 */
public abstract class MetodeBayar {

    public abstract void bayar();
}
