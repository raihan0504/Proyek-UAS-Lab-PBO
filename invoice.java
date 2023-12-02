class invoice extends Pembayaran {
    public Transaksi transaksi;
    public Pembayaran pembayaran;
    public Customer customer;
    public ArrayList<Barang> Barang;
}

abstract class Pembayaran {
    public String id;
}

class Qris extends Pembayaran {
}

class Bank extends Pembayaran {
}

class COD extends Pembayaran {
}