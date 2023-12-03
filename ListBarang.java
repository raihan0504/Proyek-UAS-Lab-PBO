import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ListBarang {
    private List<Barang> daftarBarang;

    /**
     * Konstruktor untuk membuat objek ListBarang dengan menginisialisasi daftarBarang
     * dari data yang dibaca dari file "Barang.txt".
     */
    public ListBarang() {
        this.daftarBarang = new ArrayList<>();
        this.daftarBarang = Barang.bacaDariFile("Barang.txt");
    }

    /**
     * Konstruktor alternatif untuk membuat objek ListBarang dengan daftarBarang yang
     * diberikan sebagai parameter.
     *
     * @param daftarBarang Daftar barang yang akan digunakan.
     */
    public ListBarang(List<Barang> daftarBarang) {
        this.daftarBarang = daftarBarang;
    }

    /**
     * Mendapatkan daftar barang.
     *
     * @return Daftar barang.
     */
    public List<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    /**
     * Menambahkan barang baru ke daftarBarang.
     */
    public void tambahBarang() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukkan kode barang: ");
        String kode = scan.nextLine();
        System.out.print("Masukkan nama barang: ");
        String nama = scan.nextLine();
        System.out.print("Masukkan harga barang: ");
        int harga = scan.nextInt();
        scan.nextLine(); // Consume the newline character
        System.out.print("Masukkan stok barang: ");
        int stok = scan.nextInt();
        scan.nextLine(); // Consume the newline character

        // Buat objek Barang baru
        Barang barangBaru = new Barang(kode, nama, harga, stok);
        // Tambahkan objek Barang baru ke dalam daftarBarang
        daftarBarang.add(barangBaru);

        System.out.println("Barang berhasil ditambahkan!");

        // Save the changes to the file
        simpanKeFile("Barang.txt");
    }

    /**
     * Menyimpan perubahan daftarBarang ke file.
     *
     * @param namaFile Nama file tempat menyimpan data barang.
     */
    public void simpanKeFile(String namaFile) {
        Barang.simpanKeFile(daftarBarang, namaFile);
        System.out.println("Daftar barang berhasil disimpan ke file: " + namaFile);
    }

    /**
     * Menghapus barang dari daftarBarang berdasarkan kode barang.
     */
    public void hapusBarang() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukkan kode barang yang akan dihapus: ");
        String kode = scan.nextLine();

        Iterator<Barang> iterator = daftarBarang.iterator();
        while (iterator.hasNext()) {
            Barang barang = iterator.next();
            if (barang.getKodeBarang().equals(kode)) {
                iterator.remove();
                System.out.println("Barang berhasil dihapus!");
                simpanKeFile("Barang.txt");
                return;
            }
        }

        System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
    }

    /**
     * Mengedit data barang dalam daftarBarang berdasarkan kode barang.
     */
    public void editBarang() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukkan kode barang yang akan diedit: ");
        String kode = scan.nextLine();

        for (Barang barang : daftarBarang) {
            if (barang.getKodeBarang().equals(kode)) {
                System.out.println("Data barang yang akan diubah:");
                System.out.println("Nama Barang: " + barang.getNamaBarang());
                System.out.println("Harga Barang: " + barang.getHargaBarang());
                System.out.println("Stok Barang: " + barang.getStokBarang());

                System.out.print("Masukkan nama barang baru: ");
                String namaBaru = scan.nextLine();
                System.out.print("Masukkan harga barang baru: ");
                int hargaBaru = Integer.parseInt(scan.nextLine());
                System.out.print("Masukkan stok barang baru: ");
                int stokBaru = Integer.parseInt(scan.nextLine());

                barang.setNamaBarang(namaBaru);
                barang.setHargaBarang(hargaBaru);
                barang.setStokBarang(stokBaru);

                System.out.println("Barang berhasil diubah!");
                simpanKeFile("Barang.txt");
                return;
            }
        }

        System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
    }

    /**
     * Menampilkan informasi tentang semua barang dalam daftarBarang.
     */
    public void tampilBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Daftar Barang kosong.");
        } else {
            System.out.println("Daftar Barang:");
            for (Barang barang : daftarBarang) {
                System.out.println("Kode: " + barang.getKodeBarang() +
                        ", Nama: " + barang.getNamaBarang() +
                        ", Harga: " + barang.getHargaBarang() +
                        ", Stok: " + barang.getStokBarang());
            }
        }
    }

    /**
     * Mencari barang dalam daftarBarang berdasarkan kode barang.
     *
     * @param kodeBarang Kode barang yang akan dicari.
     * @return Objek Barang yang ditemukan, atau null jika tidak ditemukan.
     */
    public Barang cariBarang(String kodeBarang) {
        for (Barang barang : daftarBarang) {
            if (barang.getKodeBarang().equals(kodeBarang)) {
                return barang;
            }
        }
        return null;
    }
}
