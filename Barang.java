import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas Barang merepresentasikan informasi mengenai suatu barang, termasuk
 * kode barang, nama barang, harga barang, dan stok barang. Kelas ini juga
 * menyediakan metode untuk membaca dan menyimpan data barang ke file.
 */
public class Barang {
    private String kodeBarang;
    private String namaBarang;
    private int hargaBarang;
    private int stokBarang;

    /**
     * Konstruktor untuk membuat objek Barang dengan informasi lengkap.
     *
     * @param kodeBarang  Kode unik untuk mengidentifikasi barang.
     * @param namaBarang  Nama dari barang.
     * @param hargaBarang Harga dari barang.
     * @param stokBarang  Jumlah stok barang yang tersedia.
     */
    public Barang(String kodeBarang, String namaBarang, int hargaBarang, int stokBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stokBarang = stokBarang;
    }

    // Tambahkan konstruktor kosong jika diperlukan
    public Barang() {
    }

    public String getKodeBarang() {
        return kodeBarang; //@return Kode barang.
    }

    public String getNamaBarang() {
        return namaBarang; //@return Nama barang.
    }

    public int getHargaBarang() {
        return hargaBarang; //@return Harga barang.
    }

    public int getStokBarang() {
        return stokBarang; //@return Stok barang.
    }

    public void setHargaBarang(int hargaBarang) { //@param hargaBarang Harga baru untuk barang.
        this.hargaBarang = hargaBarang;
    }

    public void setKodeBarang(String kodeBarang) { //@param kodeBarang Kode baru untuk barang.
        this.kodeBarang = kodeBarang;
    }

    public void setNamaBarang(String namaBarang) { //@param namaBarang Nama baru untuk barang.
        this.namaBarang = namaBarang;
    }

    public void setStokBarang(int stokBarang) { //@param stokBarang Jumlah stok baru untuk barang.
        this.stokBarang = stokBarang;
    }

    public void kurangiStok(int jumlah) { //@param jumlah Jumlah yang akan dikurangkan dari stok barang.
        stokBarang -= jumlah;
    }

    /**
     * Membaca daftar barang dari file dengan format tertentu.
     *
     * @param namaFile Nama file yang berisi data barang.
     * @return Daftar barang yang dibaca dari file.
     */
    public static List<Barang> bacaDariFile(String namaFile) {
        List<Barang> daftarBarang = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(namaFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String kodeBarang = parts[0].trim();
                    String namaBarang = parts[1].trim();

                    // Validasi harga dan stok
                    int hargaBarang;
                    int stokBarang;
                    try {
                        hargaBarang = Integer.parseInt(parts[2].trim());
                        stokBarang = Integer.parseInt(parts[3].trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Format angka tidak valid pada file: " + namaFile);
                        continue;
                    }

                    Barang barang = new Barang(kodeBarang, namaBarang, hargaBarang, stokBarang);
                    daftarBarang.add(barang);
                } else {
                    System.err.println("Format tidak valid pada file: " + namaFile);
                }
            }
        } catch (IOException e) {
            System.err.println("Kesalahan saat membaca file: " + namaFile);
            e.printStackTrace();
        }

        return daftarBarang;
    }

    /**
     * Menyimpan daftar barang ke dalam file dengan format tertentu.
     *
     * @param daftarBarang Daftar barang yang akan disimpan.
     * @param namaFile     Nama file tujuan penyimpanan.
     */
    public static void simpanKeFile(List<Barang> daftarBarang, String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (Barang barang : daftarBarang) {
                // Menggunakan text block untuk memudahkan pembacaan
                String line = String.format(
                        "%s,%s,%d,%d",
                        barang.getKodeBarang(),
                        barang.getNamaBarang(),
                        barang.getHargaBarang(),
                        barang.getStokBarang());

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Kesalahan saat menulis ke file: " + namaFile);
            e.printStackTrace();
        }
    }
}
