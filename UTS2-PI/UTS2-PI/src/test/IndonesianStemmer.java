package test;

/**
 * Stemmer Bahasa Indonesia menggunakan Apache Lucene (algoritma ECS).
 *
 * @author RACHEL RAMBU
 */
public final class IndonesianStemmer {

    // ThreadLocal agar tiap thread punya instance stemmer sendiri
    private static final ThreadLocal<org.apache.lucene.analysis.id.IndonesianStemmer> stemmerLocal =
        ThreadLocal.withInitial(org.apache.lucene.analysis.id.IndonesianStemmer::new);

    private IndonesianStemmer() {}

    /** Mengubah kata berimbuhan menjadi kata dasar menggunakan Lucene. */
    public static String stem(String word) {
        if (word == null || word.isEmpty()) return word;
        // stem() memodifikasi char[] in-place dan mengembalikan panjang barunya
        char[] chars = word.toCharArray();
        org.apache.lucene.analysis.id.IndonesianStemmer s = stemmerLocal.get();
        int newLen = s.stem(chars, chars.length, true); // true = hapus imbuhan derivasional
        return new String(chars, 0, newLen);
    }
}
