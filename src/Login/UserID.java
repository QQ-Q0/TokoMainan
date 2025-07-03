package Login;

public class UserID {
    private static String idKasir;
    private static String namaKasir;

    // menyimpan ID kasir saat login berhasil
    public static void setIdKasir(String idKasirDariDB) {
        idKasir = idKasirDariDB;
    }
    
    // mengambil ID kasir yang sedang login
    public static String getIdKasir() {
        return idKasir;
    }
    
    // menyimpan Nama kasir saat login berhasil
    public static void setNamaKasir(String namaKasirDariDB) {
        namaKasir = namaKasirDariDB;
    }
    
     // mengambil Nama kasir yang sedang login
    public static String getNamaKasir() {
        return namaKasir;
    }
}
