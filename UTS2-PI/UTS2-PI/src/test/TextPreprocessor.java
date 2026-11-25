package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilitas pra-pemrosesan teks (case-folding, pemisahan token,
 * penghapusan stopword, dan stemming Bahasa Indonesia) yang dipakai
 * secara konsisten pada saat indexing maupun searching.
 */
public final class TextPreprocessor {

    /**
     * Konstruktor privat agar kelas utilitas ini tidak diinstansiasi.
     */
    private TextPreprocessor() {
    }

    /**
     * Memecah teks menjadi daftar token terproses melalui empat tahap berurutan:
     * <ol>
     *   <li>Case-folding ke huruf kecil dengan {@code toLowerCase()}.</li>
     *   <li>Pemisahan token menggunakan regex {@code [^a-z0-9]+} agar tanda hubung
     *       (mis. {@code jalan-jalan}) dan tanda baca memisahkan kata menjadi token tersendiri.</li>
     *   <li>Stopword removal dengan {@link Stopwords#isStopword(String)}.</li>
     *   <li>Stemming Bahasa Indonesia menggunakan {@link IndonesianStemmer#stem(String)}.</li>
     * </ol>
     *
     * @param text baris teks dokumen atau string query dari pengguna
     * @return daftar token bersih hasil stemming (kosong jika tidak ada token valid)
     */
    public static List<String> preprocess(String text) {
        List<String> result = new ArrayList<String>();
        if (text == null || text.trim().isEmpty()) {
            return result;
        }

        // Ganti seluruh karakter selain huruf a-z dan angka 0-9 menjadi spasi, lalu rapikan tepi
        String normalized = text.toLowerCase().replaceAll("[^a-z0-9]+", " ").trim();
        if (normalized.isEmpty()) {
            return result;
        }

        // "\\s+" memecah string berdasarkan satu atau lebih karakter spasi/whitespace
        String[] rawTokens = normalized.split("\\s+");
        for (String token : rawTokens) {
            // Lewati token kosong atau kata yang langsung terdeteksi sebagai stopword
            if (token.isEmpty() || Stopwords.isStopword(token)) {
                continue;
            }
            // Ubah kata berimbuhan menjadi kata dasar
            String stemmed = IndonesianStemmer.stem(token);
            // Cek ulang stopword setelah stemming (mis. "semuanya" -> "semua")
            if (stemmed.isEmpty() || Stopwords.isStopword(stemmed)) {
                continue;
            }
            result.add(stemmed);
        }

        return result;
    }
}
