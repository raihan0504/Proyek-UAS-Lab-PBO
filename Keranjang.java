import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Keranjang{
    private ArrayList<Barang> listKeranjang;
    private ArrayList<Barang> listBarang;
    private Scanner scanner;

    public Keranjang(){
        this.listKeranjang = new ArrayList<Barang>();
        this.listBarang = new ArrayList<Barang>();
        this.scanner = new Scanner(System.in);
    }

    private void bacaDataKeranjang(){
        String pathDataKeranjang = "Keranjang.txt";
        BufferedReader keranjang = null;
        try{
            keranjang = new BufferedReader(new FileReader(pathDataKeranjang));
            String bacaLine;
            while((bacaLine = keranjang.readLine()) != null){
                String[] token = bacaLine.split(",");
                Barang barangKeranjang = new Barang(token[0], token[1], Integer.parseInt(token[2]), Integer.parseInt(token[3]));
                this.listKeranjang.add(barangKeranjang);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void tulisDataKeranjang(){
        String pathDataKeranjang = "Keranjang.txt";
        BufferedWriter keranjang = null;
        try{
            keranjang = new BufferedWriter(new FileWriter(pathDataKeranjang));
            for(Barang i : this.listKeranjang){
                keranjang.write(i.getKodeBarang()+",");
                keranjang.write(i.getNamaBarang()+",");
                keranjang.write(i.getHargaBarang()+",");
                keranjang.write(String.valueOf(i.getStokBarang()));
                keranjang.newLine();
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try{
                if(keranjang!=null){
                    keranjang.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    private void bacaDataBarang(){
        String pathDataBarang = "Barang.txt";
        BufferedReader databaseBarang = null;
        try {
            databaseBarang = new BufferedReader( new FileReader(pathDataBarang));
            String bacaLine;
            while((bacaLine = databaseBarang.readLine())!=null){
                String[] token = bacaLine.split(",");
                Barang barang = new Barang(token[0],token[1],Integer.parseInt(token[2]),Integer.parseInt(token[3]));
                this.listBarang.add(barang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tampilBarang(){
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

    public void lihatKeranjang(){
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

    public void tambahBarangKeKeranjang(){
        boolean cek = false;
        this.bacaDataBarang();
        this.bacaDataKeranjang();
        Barang barangKeranjang = new Barang();
        System.out.print("Masukkan nama barang : ");
        String namaBarang = this.scanner.nextLine();

        for(int i = 0;i<this.listBarang.size();i++){
            if(namaBarang.equals(this.listBarang.get(i).getNamaBarang())){
                System.out.println("Barang Berhasil dimasukkan keranjang");
                System.out.println("Kode barang : "+this.listBarang.get(i).getKodeBarang());
                System.out.println("Nama barang : "+this.listBarang.get(i).getNamaBarang());
                System.out.println("Harga barang : "+this.listBarang.get(i).getHargaBarang());
                System.out.println("stok : "+this.listBarang.get(i).getStokBarang());

                barangKeranjang.setKodeBarang(this.listBarang.get(i).getKodeBarang());
                barangKeranjang.setNamaBarang(this.listBarang.get(i).getNamaBarang());
                barangKeranjang.setHargaBarang(this.listBarang.get(i).getHargaBarang());
                barangKeranjang.setStokBarang(this.listBarang.get(i).getStokBarang());

                this.listKeranjang.add(barangKeranjang);
                this.tulisDataKeranjang();
                cek = true;

                break;
            }
            if(i==this.listBarang.size()-1 && cek==false){
                System.out.println("Barang tidak ditemukan");
            }
        }
        this.listBarang.clear();
        this.listKeranjang.clear();
        this.listBarang.clear();
    }

    public void hapusBarangDiKeranjang(){
        boolean cek = false;
        this.bacaDataKeranjang();
        System.out.print("Masukkan Nama Barang yang ingin dihapus dari Keranjang : ");
        String namaBarang = this.scanner.nextLine();
        for(int i = 0 ; i < this.listKeranjang.size() ; i++){
            if(namaBarang.equals(this.listKeranjang.get(i).getNamaBarang())){
                this.listKeranjang.remove(i);
                this.tulisDataKeranjang();
                cek = true;
                break;
            }
            if(i==this.listKeranjang.size()-1 && cek==false){
                System.out.println("Barang tidak ditemukan");
            }
        }

        this.listKeranjang.clear();
    }
}