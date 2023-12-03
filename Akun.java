/**
 * Kelas Akun merupakan kelas dasar yang merepresentasikan sebuah akun dalam sistem.
 * Setiap akun memiliki identifikasi unik yang disebut ID. Kelas ini menyediakan
 * fungsionalitas dasar untuk mendapatkan dan mengatur ID akun.
 */
class Akun {
    protected String id;

    public Akun(){

    }

    public Akun(String id){ //@param id ID yang akan diberikan kepada akun.
        this.id = id;
    }

    public String getId(){
        return id; //@return ID dari akun.
    }

    public void setId(String id) { //@param id ID yang akan diatur untuk akun.
        this.id = id;
    }
}
