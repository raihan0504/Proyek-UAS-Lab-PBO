public class Barang {
    private String kodeBarang;
    private String namaBarang;
    private int hargaBarang;
    private int stokBarang;

    public Barang(String kodeBarang, String namaBarang, int hargaBarang, int stokBarang){
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stokBarang = stokBarang;
    }
    public String getKodeBarang() {
        return kodeBarang;
    }
    
    public int getHargaBarang() {
        return hargaBarang;
    }
    
    public String getNamaBarang() {
        return namaBarang;
    }
    
    public int getStokBarang() {
        return stokBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }
}