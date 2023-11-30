import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListBarang {
    protected ArrayList<Barang> barang;
    protected final String FILE_NAME = "Barang.txt";

    public ListBarang() {
        barang = new ArrayList<>();
    }

    public void editBarang() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan kode barang yang ingin di edit: ");
        String kode = scan.next();
        System.out.print("Masukkan nama baru: ");
        String nama = scan.next();
        System.out.print("Masukkan harga baru: ");
        int harga = scan.nextInt();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }

            List<String> barang = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                if (part[0].equals(kode)) {
                    line = kode + "," + nama + "," + harga;
                }
                barang.add(line);
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String l : barang) {
                bufferedWriter.write(l);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Barang berhasil diedit!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mengedit barang!");
            e.printStackTrace();
        }
    }

    public void hapusBarang() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan kode barang yang ingin dihapus: ");
        String kode = scan.next();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }

            List<String> barang = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                if (!part[0].equals(kode)) {
                    barang.add(line);
                }
            }
            reader.close();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String l : barang) {
                bufferedWriter.write(l);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Barang berhasil dihapus!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menghapus barang!");
            e.printStackTrace();
        }
    }

    public void tampilBarang() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] part = line.split(",");
                if (part.length >= 3) {
                    System.out.println("Kode: " + part[0]);
                    System.out.println("Nama: " + part[1]);
                    System.out.println("Harga: " + part[2]);
                    System.out.println(); // Menambahkan baris kosong sebagai pemisah
                } else {
                    System.out.println("Format tidak valid: " + line);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menampilkan barang!");
            e.printStackTrace();
        }
    }

    public void tambahBarang() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan kode barang: ");
        String kode = scan.nextLine();
        System.out.print("Masukkan nama barang: ");
        String nama = scan.nextLine();
        System.out.print("Masukkan harga barang: ");
        int harga = scan.nextInt();

        try {
            File file = new File(FILE_NAME);

            FileWriter tulisFile = new FileWriter(file, true);
            BufferedWriter bufferWriter = new BufferedWriter(tulisFile);
            bufferWriter.write(kode + "," + nama + "," + harga);
            bufferWriter.newLine();
            bufferWriter.close();
            System.out.println("Barang berhasi ditambahkan!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menambahkan barang!");
            e.printStackTrace();
        }
    }
}
