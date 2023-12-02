import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ListBarang {
    private List<Barang> daftarBarang;

    public ListBarang(){

    }

    public ListBarang(List<Barang> daftarBarang) {
        this.daftarBarang = daftarBarang;
    }

    public List<Barang> getDaftarBarang() {
        return daftarBarang;
    }


    public void tambahBarang() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukkan kode barang: ");
        String kode = scan.nextLine();
        System.out.print("Masukkan nama barang: ");
        String nama = scan.nextLine();
        System.out.print("Masukkan harga barang: ");
        int harga = scan.nextInt();
        System.out.print("Masukkan stok barang: ");
        int stok = scan.nextInt();
        // Buat objek Barang baru
        Barang barangBaru = new Barang(kode, nama, harga, stok);
        // Tambahkan objek Barang baru ke dalam daftarBarang
        daftarBarang.add(barangBaru);

        System.out.println("Barang berhasil ditambahkan!");
    }
    // Metode untuk menyimpan perubahan ke dalam file
    public void simpanKeFile(String namaFile) {
        Barang.simpanKeFile(daftarBarang, namaFile);
        System.out.println("Daftar barang berhasil disimpan ke file: " + namaFile);
    }

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
                return;
            }
        }

        System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
    }

    public void editBarang() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukkan kode barang yang akan diedit: ");
        String kode = scan.nextLine();

        Iterator<Barang> iterator = daftarBarang.iterator();
        while (iterator.hasNext()) {
            Barang barang = iterator.next();
            if (barang.getKodeBarang().equals(kode)) {
                System.out.println("Data barang yang akan diubah:");
                System.out.println("Nama Barang: " + barang.getNamaBarang());
                System.out.println("Harga Barang: " + barang.getHargaBarang());
                System.out.println("Stok Barang: " + barang.getStokBarang());

                System.out.print("Masukkan nama barang baru: ");
                String namaBaru = scan.nextLine();
                System.out.print("Masukkan harga barang baru: ");
                int hargaBaru = scan.nextInt();
                System.out.print("Masukkan stok barang baru: ");
                int stokBaru = scan.nextInt();

                // Update data barang
                barang.setNamaBarang(namaBaru);
                barang.setHargaBarang(hargaBaru);
                barang.setStokBarang(stokBaru);

                System.out.println("Barang berhasil diubah!");
                return;
            }
        }

        System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
    }

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
}
