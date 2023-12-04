import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Barang {
    private String kodeBarang;
    private String namaBarang;
    private int hargaBarang;
    private int stokBarang;

    /**
     * Konstruktor kelas Barang dengan parameter.
     *
     * @param kodeBarang  Kode barang.
     * @param namaBarang  Nama barang.
     * @param hargaBarang Harga barang.
     * @param stokBarang  Stok barang.
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
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public int getStokBarang() {
        return stokBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }

    public void kurangiStok(int jumlah) {
        stokBarang -= jumlah;
    }

    /**
     * Metode untuk membaca daftar barang dari file.
     *
     * @param namaFile Nama file yang akan dibaca.
     * @return Daftar barang yang berhasil dibaca dari file.
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
     * Metode untuk menyimpan daftar barang ke dalam file.
     *
     * @param daftarBarang Daftar barang yang akan disimpan.
     * @param namaFile    Nama file tempat penyimpanan.
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
