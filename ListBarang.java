import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

**
 * Kelas ListBarang menyediakan fungsionalitas untuk mengelola daftar barang, termasuk menambah, menghapus,
 * dan mengedit barang. Data barang disimpan dalam file "Barang.txt".
 * Kelas ini juga dapat digunakan untuk mencari barang berdasarkan kode.
 * Daftar barang diinisialisasi dari file saat objek ListBarang dibuat.
 */
public class ListBarang {
    private List<Barang> daftarBarang; //Daftar barang yang dikelola oleh objek ListBarang.

    public ListBarang() {
        this.daftarBarang = new ArrayList<>();
        this.daftarBarang = Barang.bacaDariFile("Barang.txt");
    }

    public ListBarang(List<Barang> daftarBarang) { //@param daftarBarang Daftar barang yang akan diatur.
        this.daftarBarang = daftarBarang;
    }

    public List<Barang> getDaftarBarang() {
        return daftarBarang; //@return Daftar barang.
    }

    /**
     * Menambahkan barang baru ke daftar barang.
     * Pengguna diminta untuk memasukkan informasi barang seperti kode, nama, harga, dan stok.
     * Data barang kemudian ditambahkan ke daftarBarang dan disimpan ke file "Barang.txt".
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

    // Metode untuk menyimpan perubahan ke dalam file
    public void simpanKeFile(String namaFile) { //@param namaFile Nama file tempat data barang akan disimpan.
        Barang.simpanKeFile(daftarBarang, namaFile);
        System.out.println("Daftar barang berhasil disimpan ke file: " + namaFile);
    }

    /**
     * Menghapus barang dari daftar berdasarkan kode barang.
     * Pengguna diminta untuk memasukkan kode barang yang akan dihapus.
     * Jika barang ditemukan, data barang dihapus dari daftarBarang dan perubahan disimpan ke file "Barang.txt".
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
     * Mengedit barang dalam daftar berdasarkan kode barang.
     * Pengguna diminta untuk memasukkan kode barang yang akan diedit.
     * Jika barang ditemukan, pengguna dapat mengubah informasi seperti nama, harga, dan stok.
     * Perubahan data barang disimpan ke file "Barang.txt".
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
     * Menampilkan daftar barang yang ada.
     * Jika daftarBarang kosong, pesan "Daftar Barang kosong" ditampilkan.
     * Jika tidak, informasi setiap barang ditampilkan, termasuk kode, nama, harga, dan stok.
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
     * Mencari barang dalam daftar berdasarkan kode barang.
     *
     * @param kodeBarang Kode barang yang dicari.
     * @return Objek Barang jika ditemukan, atau null jika tidak ditemukan.
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
