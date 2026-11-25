# Inverted_indexPI (UTS2-PI)

Aplikasi **Inverted Index** dan sistem temu kembali informasi (*Information Retrieval*) menggunakan **Apache Lucene** untuk koleksi 12 dokumen berita berbahasa Indonesia (`UTS2-PI/Koleksi/doc1.txt` s.d. `doc12.txt`).

Project ini sudah dilengkapi dengan pipeline pra-pemrosesan teks lengkap:
- **Case-folding & Pemisahan Token** (memecah tanda hubung dan tanda baca menjadi token terpisah)
- **Stopword Removal** Bahasa Indonesia menggunakan daftar bawaan **Apache Lucene** (`stopwords.txt`)
- **Stemming Bahasa Indonesia** menggunakan **Apache Lucene** (`IndonesianStemmer`)
- **Boolean AND Search** menggunakan algoritma *two-pointer intersection*

---

## Struktur Direktori

- `UTS2-PI/Koleksi/` — Koleksi dokumen teks (`doc1.txt` – `doc12.txt`)
- `UTS2-PI/UTS2-PI/lib/lucene-analyzers-common-8.11.2 (1).jar` — Library Apache Lucene
- `UTS2-PI/UTS2-PI/src/main/MainInvertedIndexBasic.java` — Entry point program (build index + pencarian interaktif)
- `UTS2-PI/UTS2-PI/src/test/ReadFileToken.java` — Pembaca file koleksi & builder index
- `UTS2-PI/UTS2-PI/src/test/TextPreprocessor.java` — Pipeline normalisasi, tokenisasi, stopword removal, dan stemming
- `UTS2-PI/UTS2-PI/src/test/Stopwords.java` — Daftar stopword Bahasa Indonesia bawaan Apache Lucene (`stopwords.txt`)
- `UTS2-PI/UTS2-PI/src/test/IndonesianStemmer.java` — Wrapper stemmer Apache Lucene (ECS)
- `UTS2-PI/UTS2-PI/src/test/InvertedIndexBasic.java` — Struktur data Inverted Index & query intersection
- `UTS2-PI/UTS2-PI/src/test/Term.java` — Entitas dictionary (`term` + posting list)
- `UTS2-PI/UTS2-PI/src/test/Document.java` — Entitas dokumen pada posting list
- `UTS2-PI/UTS2-PI/src/test/LinkedListOrderedUnique.java` — LinkedList terurut dan unik

---

## Cara Menjalankan

### 1. Melalui Command Line (JDK 23/24)
```powershell
$lib = "UTS2-PI\UTS2-PI\lib\lucene-analyzers-common-8.11.2 (1).jar"
$src = Get-ChildItem "UTS2-PI\UTS2-PI\src" -Recurse -Filter "*.java" | ForEach-Object { $_.FullName }
javac -encoding UTF-8 --release 23 -cp $lib -d "UTS2-PI\UTS2-PI\build\classes" @src

cd UTS2-PI\UTS2-PI
java -cp "build\classes;lib\lucene-analyzers-common-8.11.2 (1).jar" main.MainInvertedIndexBasic
```

### 2. Melalui NetBeans
Buka folder `UTS2-PI\UTS2-PI` sebagai NetBeans Project, lalu klik **Run Project** (`F6`). Library Lucene dan main class (`main.MainInvertedIndexBasic`) telah dikonfigurasi pada `nbproject/project.properties`.

---

## Dokumentasi Lengkap

Penjelasan rinci mengenai arsitektur, alasan pemilihan metode & algoritma (Inverted Index, `LinkedListOrderedUnique`, Two-Pointer Intersection, Stopword Removal, Stemming Lucene ECS, pemisahan token, hingga penjelasan sintaks Java) tersedia di [DOKUMENTASI.md](DOKUMENTASI.md).
