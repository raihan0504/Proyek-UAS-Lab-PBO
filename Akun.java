class Akun {
    protected String id;
    /**
     * Konstruktor default kelas Akun.
     */
    public Akun(){

    }

    /**
     * Konstruktor kelas Akun dengan parameter ID.
     *
     * @param id ID yang akan diatur untuk akun.
     */
    public Akun(String id){
        this.id = id;
    }

    /**
     * Mendapatkan ID akun.
     *
     * @return String berisi ID akun.
     */
    public String getId(){
        return id;
    }

    /**
     * Mengatur ID baru untuk akun.
     *
     * @param id ID baru yang akan diatur untuk akun.
     */
    public void setId(String id) {
        this.id = id;
    }
}
