import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kelas Keranjang mengelola operasi yang terkait dengan keranjang belanja,
 * seperti menampilkan barang, menambahkan dan menghapus barang dari keranjang.
 * Data keranjang disimpan dalam file "Keranjang.txt" dan data barang dalam file "Barang.txt".
 * Kelas ini memiliki fungsionalitas untuk membersihkan keranjang belanja.
 */
public class Keranjang {
    private ArrayList<Barang> listKeranjang;
    private ArrayList<Barang> listBarang;
    private Scanner scanner;

    /**
     * Konstruktor untuk membuat instansi Keranjang.
     * Inisialisasi ArrayList untuk keranjang dan barang, serta Scanner untuk input.
     */
    public Keranjang() {
        this.listKeranjang = new ArrayList<Barang>();
        this.listBarang = new ArrayList<Barang>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Mengosongkan keranjang belanja dengan menghapus semua barang dari ArrayList.
     */
    public void clearKeranjang() {
        this.listKeranjang.clear();
    }

    /**
     * Membaca data keranjang dari file "Keranjang.txt".
     * Setiap baris dalam file mewakili satu barang dalam keranjang.
     */
    private void bacaDataKeranjang() {
        String pathDataKeranjang = "Keranjang.txt";
        try (BufferedReader keranjang = new BufferedReader(new FileReader(pathDataKeranjang))) {
            String bacaLine;
            while ((bacaLine = keranjang.readLine()) != null) {
                String[] token = bacaLine.split(",");
                Barang barangKeranjang = new Barang(token[0], token[1], Integer.parseInt(token[2]), Integer.parseInt(token[3]));
                this.listKeranjang.add(barangKeranjang);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Menulis data keranjang ke file "Keranjang.txt".
     * Setiap baris dalam file mewakili satu barang dalam keranjang.
     */
    private void tulisDataKeranjang() {
        String pathDataKeranjang = "Keranjang.txt";
        try (BufferedWriter keranjang = new BufferedWriter(new FileWriter(pathDataKeranjang))) {
            for (Barang i : this.listKeranjang) {
                keranjang.write(i.getKodeBarang() + ",");
                keranjang.write(i.getNamaBarang() + ",");
                keranjang.write(i.getHargaBarang() + ",");
                keranjang.write(String.valueOf(i.getStokBarang()));
                keranjang.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Membaca data barang dari file "Barang.txt".
     * Setiap baris dalam file mewakili satu barang yang dapat ditambahkan ke keranjang.
     */
    private void bacaDataBarang() {
        String pathDataBarang = "Barang.txt";
        try (BufferedReader databaseBarang = new BufferedReader(new FileReader(pathDataBarang))) {
            String bacaLine;
            while ((bacaLine = databaseBarang.readLine()) != null) {
                String[] token = bacaLine.split(",");
                Barang barang = new Barang(token[0], token[1], Integer.parseInt(token[2]), Integer.parseInt(token[3]));
                this.listBarang.add(barang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Menampilkan daftar barang yang tersedia untuk dibeli.
     * Daftar barang dibaca dari file "Barang.txt".
     */
    public void tampilBarang() {
        this.bacaDataBarang();
        this.listBarang.forEach((barang) -> {
            System.out.println(barang.getKodeBarang());
            System.out.println(barang.getNamaBarang());
            System.out.println(barang.getHargaBarang());
            System.out.println(barang.getStokBarang());
            System.out.println("\n");
        });
        this.listBarang.clear();
    }

    /**
     * Menampilkan daftar barang yang ada dalam keranjang belanja.
     * Daftar barang dibaca dari file "Keranjang.txt".
     */
    public void lihatKeranjang() {
        this.bacaDataKeranjang();
        this.listKeranjang.forEach((barang) -> {
            System.out.println(barang.getKodeBarang());
            System.out.println(barang.getNamaBarang());
            System.out.println(barang.getHargaBarang());
            System.out.println(barang.getStokBarang());
            System.out.println("\n");
        });
        this.listKeranjang.clear();
    }

    /**
     * Menambahkan barang ke dalam keranjang belanja.
     * Pengguna diminta untuk memasukkan nama barang yang ingin ditambahkan ke keranjang.
     * Jika barang ditemukan, informasi barang ditampilkan dan barang ditambahkan ke keranjang.
     */
    public void tambahBarangKeKeranjang() {
        boolean cek = false;
        this.bacaDataBarang();
        this.bacaDataKeranjang();
        System.out.print("Masukkan nama barang : ");
        String namaBarang = this.scanner.nextLine();

        for (int i = 0; i < this.listBarang.size(); i++) {
            if (namaBarang.equals(this.listBarang.get(i).getNamaBarang())) {
                System.out.println("Barang Berhasil dimasukkan keranjang");
                System.out.println("Kode barang : " + this.listBarang.get(i).getKodeBarang());
                System.out.println("Nama barang : " + this.listBarang.get(i).getNamaBarang());
                System.out.println("Harga barang : " + this.listBarang.get(i).getHargaBarang());
                System.out.println("stok : " + this.listBarang.get(i).getStokBarang());

                Barang barangKeranjang = new Barang();
                barangKeranjang.setKodeBarang(this.listBarang.get(i).getKodeBarang());
                barangKeranjang.setNamaBarang(this.listBarang.get(i).getNamaBarang());
                barangKeranjang.setHargaBarang(this.listBarang.get(i).getHargaBarang());
                barangKeranjang.setStokBarang(this.listBarang.get(i).getStokBarang());

                this.listKeranjang.add(barangKeranjang);
                this.tulisDataKeranjang();
                cek = true;
                break;
            }
            if (i == this.listBarang.size() - 1 && cek == false) {
                System.out.println("Barang tidak ditemukan");
            }
        }
    }

    /**
     * Menghapus barang dari keranjang belanja berdasarkan nama barang.
     * Pengguna diminta untuk memasukkan nama barang yang ingin dihapus dari keranjang.
     * Jika barang ditemukan, barang dihapus dari keranjang.
     */
    public void hapusBarangDiKeranjang() {
        boolean cek = false;
        this.bacaDataKeranjang();
        System.out.print("Masukkan Nama Barang yang ingin dihapus dari Keranjang : ");
        String namaBarang = this.scanner.nextLine();
        for (int i = 0; i < this.listKeranjang.size(); i++) {
            if (namaBarang.equals(this.listKeranjang.get(i).getNamaBarang())) {
                this.listKeranjang.remove(i);
                this.tulisDataKeranjang();
                cek = true;
                break;
            }
            if (i == this.listKeranjang.size() - 1 && cek == false) {
                System.out.println("Barang tidak ditemukan");
            }
        }
    }
}
