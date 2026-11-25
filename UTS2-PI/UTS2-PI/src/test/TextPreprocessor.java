package test;

import java.util.ArrayList;
import java.util.List;

/** Pra-pemrosesan teks: lowercase, tokenisasi, hapus stopword, dan stemming. */
public final class TextPreprocessor {

    private TextPreprocessor() {
    }

    /** Memecah teks menjadi token kata dasar yang bebas stopword. */
    public static List<String> preprocess(String text) {
        List<String> result = new ArrayList<String>();
        if (text == null || text.trim().isEmpty()) {
            return result;
        }

        // Ganti karakter non-alfanumerik jadi spasi agar kata berpenghubung terpisah
        String normalized = text.toLowerCase().replaceAll("[^a-z0-9]+", " ").trim();
        if (normalized.isEmpty()) {
            return result;
        }

        // Pecah per kata, saring stopword, lalu stem dengan Lucene
        String[] rawTokens = normalized.split("\\s+");
        for (String token : rawTokens) {
            if (token.isEmpty() || Stopwords.isStopword(token)) {
                continue;
            }
            String stemmed = IndonesianStemmer.stem(token);
            // Cek ulang stopword setelah imbuhan dilepas
            if (stemmed.isEmpty() || Stopwords.isStopword(stemmed)) {
                continue;
            }
            result.add(stemmed);
        }

        return result;
    }
}
