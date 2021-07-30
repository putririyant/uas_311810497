package id.ac.pelitabangsa.ericofernandy_uas.Activity;

class DataFilter {

    private String Nama;
    private String NIM;
    private String Jurusan;
    private String JenisKelamin;
    private String Alamat;
    private String TanggalLahir;

    DataFilter(String NIM, String Nama, String Jurusan, String JenisKelamin,
               String Alamat, String TanggalLahir ) {
        this.Nama = Nama;
        this.NIM = NIM;
        this.Jurusan = Jurusan;
        this.JenisKelamin = JenisKelamin;
        this.Alamat = Alamat;
        this.TanggalLahir = TanggalLahir;
    }

    String getNama() {
        return Nama;
    }

    public String getNIM() {
        return NIM;
    }

    public String getJurusan() {
        return Jurusan;
    }

    public String getJenisKelamin() { return JenisKelamin; }

    public String getAlamat() { return Alamat; }

    public String getTanggalLahir() { return TanggalLahir; }


}