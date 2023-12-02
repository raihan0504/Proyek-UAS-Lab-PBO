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

    public Barang(String kodeBarang, String namaBarang, int hargaBarang, int stokBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stokBarang = stokBarang;
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

    public static List<Barang> bacaDariFile(String namaFile) {
        List<Barang> daftarBarang = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(namaFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String kodeBarang = parts[0].trim();
                    String namaBarang = parts[1].trim();
                    int hargaBarang = Integer.parseInt(parts[2].trim());
                    int stokBarang = Integer.parseInt(parts[3].trim());

                    Barang barang = new Barang(kodeBarang, namaBarang, hargaBarang, stokBarang);
                    daftarBarang.add(barang);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();  // Ganti dengan penanganan kesalahan yang sesuai
        }

        return daftarBarang;
    }

    public static void simpanKeFile(List<Barang> daftarBarang, String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (Barang barang : daftarBarang) {
                // Format: Kode,Nama,Harga,Stok
                String line = String.format("%s,%s,%d,%d",
                        barang.getKodeBarang(),
                        barang.getNamaBarang(),
                        barang.getHargaBarang(),
                        barang.getStokBarang());

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();  // Ganti dengan penanganan kesalahan yang sesuai
        }
    }
}
