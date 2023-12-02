import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.BranchElement;

public class Transaksi {
    private Customer akun;
    private ArrayList<Barang> barang;

    public Transaksi(Customer akun){
        this.akun = akun;     
        this.barang = new ArrayList<>();
    }

    public void tambahBarang(ListBarang listBarang){
        Scanner scan = new Scanner(System.in);

        listBarang.tambahBarang();

        System.out.print("Masukkan kode barang yang ingin ditambahkan ke keranjang: ");
        String kodeBarang = scan.next();

        Barang barangDitambahkan = listBarang.cariBarang(kodeBarang);

        if (barangDitambahkan != null){
            barang.add(barangDitambahkan);
            System.out.println("Barang berhasil ditambahkan ke keranjang!");
        } else {
            System.out.println("Barang dengan kode tersebut tidak ditemukan");
        }
    }

    public void Checkout(){
        if (barang.isEmpty()){
            System.out.println("keranjang belanja kosong");
        } else {
            System.out.println("Checkout berhasil!");
            System.out.println("Detail Transaksi:");
            System.out.println("Nama Pelanggan: " + akun.getNama());
            System.out.println("Total Barang: " + barang.size());

            int totalHarga = 0;
            for (Barang b : barang){
                System.out.println("Kode Barang: " + b.getKodeBarang());
                System.out.println("Nama Barang: " + b.getNamaBarang());
                System.out.println("Harga: " + b.getHargaBarang());
                totalHarga += b.getHargaBarang();
            }
            System.out.println("Total Harga: " + totalHarga);

            akun.tambahTransaksi(new Transaksi(akun, new ArrayList<>(barang)));

            barang.clear();
        }
    }
}
