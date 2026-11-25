package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** Daftar stopword Bahasa Indonesia bawaan Apache Lucene (lookup O(1)). */
public final class Stopwords {

    private static final String LUCENE_STOPWORDS = "/org/apache/lucene/analysis/id/stopwords.txt";
    private static final Set<String> STOPWORDS;

    static {
        // Muat daftar stopword sekali dari dalam JAR Lucene
        Set<String> words = new HashSet<String>();
        try (InputStream is = org.apache.lucene.analysis.id.IndonesianStemmer.class.getResourceAsStream(LUCENE_STOPWORDS)) {
            if (is == null) {
                throw new IllegalStateException("Resource tidak ditemukan: " + LUCENE_STOPWORDS);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String word = line.trim();
                    // Abaikan baris kosong dan baris komentar (#)
                    if (!word.isEmpty() && !word.startsWith("#")) {
                        words.add(word);
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Gagal memuat stopword Lucene", e);
        }
        STOPWORDS = Collections.unmodifiableSet(words); // Read-only agar tidak bisa diubah
    }

    private Stopwords() {
    }

    /** Mengembalikan true jika token merupakan stopword, null, atau kosong. */
    public static boolean isStopword(String token) {
        if (token == null || token.isEmpty()) {
            return true;
        }
        return STOPWORDS.contains(token);
    }

    /** Mengembalikan seluruh stopword (read-only). */
    public static Set<String> getAll() {
        return STOPWORDS;
    }
}
