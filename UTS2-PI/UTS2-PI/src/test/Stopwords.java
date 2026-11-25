package test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Daftar dan utilitas penyaringan stopword Bahasa Indonesia.
 * Menggunakan {@link HashSet} dengan pembungkus {@link Collections#unmodifiableSet}
 * agar pengecekan keanggotaan berlangsung dalam waktu rata-rata O(1) dan daftar tidak
 * dapat dimodifikasi dari luar kelas.
 */
public final class Stopwords {

    /** Himpunan stopword Bahasa Indonesia (kata hubung, preposisi, pronomina, partikel, dll.). */
    private static final Set<String> STOPWORDS;

    static {
        // Static initializer block: dijalankan sekali saat kelas dimuat ke JVM
        Set<String> words = new HashSet<String>();
        String[] list = {
            // Kata hubung & preposisi
            "dan", "atau", "serta", "maupun", "di", "ke", "dari", "daripada",
            "pada", "kepada", "terhadap", "untuk", "buat", "guna", "bagi", "demi",
            "dengan", "tanpa", "oleh", "tentang", "mengenai", "antara", "sekitar",
            "sepanjang", "selama", "sejak", "hingga", "sampai", "lewat", "melalui",
            "menuju", "atas", "bawah", "dalam", "luar", "depan", "belakang",
            "samping", "kecuali", "selain", "seperti", "bagai", "bagaikan",
            "ibarat", "selaku", "sebagai",
            // Konjungsi & penanda klausa
            "yang", "bahwa", "agar", "supaya", "untuk", "karena", "sebab",
            "lantaran", "akibat", "sehingga", "maka", "lalu", "kemudian",
            "selanjutnya", "sesudah", "setelah", "sebelum", "sebelumnya",
            "selepas", "ketika", "saat", "sewaktu", "tatkala", "sementara",
            "sedangkan", "sambil", "seraya", "jika", "kalau", "bila", "apabila",
            "jikalau", "andaikan", "seandainya", "namun", "tetapi", "tapi",
            "melainkan", "padahal", "meski", "meskipun", "walau", "walaupun",
            "kendati", "biarpun", "sekalipun", "seolah", "seakan", "yakni", "yaitu",
            // Kopula, modalitas, aspek, negasi
            "adalah", "ialah", "merupakan", "menjadi", "jadi", "ada", "adanya",
            "adapun", "akan", "telah", "sudah", "belum", "sedang", "masih",
            "pernah", "sempat", "bisa", "dapat", "mampu", "boleh", "harus",
            "mesti", "wajib", "perlu", "hendak", "ingin", "mau", "tidak", "tak",
            "bukan", "jangan", "tiada", "enggak", "nggak", "belumlah",
            // Pronomina & penunjuk
            "saya", "aku", "kami", "kita", "kamu", "engkau", "kau", "anda",
            "kalian", "dia", "ia", "beliau", "mereka", "ini", "itu", "tersebut",
            "begini", "begitu", "demikian", "sini", "situ", "sana", "nya", "ku", "mu",
            // Kata tanya
            "apa", "apakah", "apalagi", "siapa", "kapan", "mana", "dimana",
            "kemana", "darimana", "mengapa", "kenapa", "bagaimana", "berapa",
            // Kuantifier, partikel, adverbia umum
            "para", "semua", "semuanya", "seluruh", "seluruhnya", "sebagian",
            "sejumlah", "beberapa", "setiap", "tiap", "masing", "suatu", "sebuah",
            "seorang", "seseorang", "sesuatu", "segala", "segenap", "lain",
            "lainnya", "sendiri", "saling", "sama", "saja", "hanya", "cuma",
            "juga", "pula", "pun", "lagi", "sangat", "amat", "terlalu", "cukup",
            "agak", "kurang", "lebih", "paling", "makin", "semakin", "kian",
            "hampir", "nyaris", "memang", "tentu", "pasti", "mungkin", "barangkali",
            "sering", "kerap", "selalu", "senantiasa", "kadang", "terkadang",
            "jarang", "sekali", "secara", "bahkan", "justru", "malah", "malahan",
            "rupanya", "tampaknya", "ternyata", "sebenarnya", "sesungguhnya",
            "kira", "segera", "langsung", "terus", "tetap", "kembali", "hal",
            "lah", "kah", "tah", "toh", "kok", "kan", "si", "ya", "ah", "oh", "eh",
            // Kata kutipan berita yang sangat umum
            "kata", "katanya", "ujar", "ujarnya", "tutur", "tuturnya", "menurut",
            "lanjut", "tandas", "imbuh", "ungkap"
        };
        // Collections.addAll memindahkan seluruh isi array ke dalam HashSet secara ringkas
        Collections.addAll(words, list);
        // Membungkus Set menjadi read-only agar aman dari perubahan tidak disengaja
        STOPWORDS = Collections.unmodifiableSet(words);
    }

    /**
     * Konstruktor privat karena kelas ini hanya menyediakan method statis (utility class).
     */
    private Stopwords() {
    }

    /**
     * Mengecek apakah suatu token termasuk stopword atau kosong/null.
     *
     * @param token kata yang sudah dinormalisasi (huruf kecil)
     * @return {@code true} jika token adalah stopword, {@code null}, atau kosong
     */
    public static boolean isStopword(String token) {
        if (token == null || token.isEmpty()) {
            return true;
        }
        return STOPWORDS.contains(token);
    }

    /**
     * Mengembalikan himpunan seluruh stopword (bersifat read-only).
     *
     * @return Set stopword yang tidak dapat dimodifikasi
     */
    public static Set<String> getAll() {
        return STOPWORDS;
    }
}
