abstract class pembayaran {
    abstract void prosesPembayaran(double totalPembelian);
}

class PembayaranQRIS extends Pembayaran {
    void prosesPembayaran(double totalPembelian) {
        // Logika pembayaran dengan QRIS
        System.out.println("Melakukan pembayaran sebesar " + totalPembelian + " dengan QRIS.");
    }
}

class PembayaranCOD extends Pembayaran {
    void prosesPembayaran(double totalPembelian) {
        // Logika pembayaran dengan Cash On Delivery (COD)
        System.out.println("Melakukan pembayaran sebesar " + totalPembelian + " dengan metode Cash On Delivery.");
    }
}

class PembayaranBank extends Pembayaran {
    private String namaBank;
    private String nomorRekening;

    PembayaranBank(String namaBank, String nomorRekening) {
        this.namaBank = namaBank;
        this.nomorRekening = nomorRekening;
    }

    void prosesPembayaran(double totalPembelian) {
        // Logika pembayaran dengan transfer bank
        System.out.println("Melakukan pembayaran sebesar " + totalPembelian +
                " via transfer bank " + namaBank + " ke rekening " + nomorRekening + ".");
    }
}
