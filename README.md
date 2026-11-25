# Inverted_indexPI (UTS2-PI)

Aplikasi **Inverted Index** dan sistem temu kembali informasi (*Information Retrieval*) berbasis **Java murni** untuk koleksi 12 dokumen berita berbahasa Indonesia (`UTS2-PI/Koleksi/doc1.txt` s.d. `doc12.txt`).

Project ini sudah dilengkapi dengan pipeline pra-pemrosesan teks lengkap:
- **Case-folding & Pemisahan Token** (memecah tanda hubung dan tanda baca menjadi token terpisah)
- **Stopword Removal** Bahasa Indonesia
- **Stemming Bahasa Indonesia** menggunakan algoritma **Nazief–Adriani** (*Enhanced Confix Stripping*)
- **Boolean AND Search** menggunakan algoritma *two-pointer intersection*

---

## Struktur Direktori

- `UTS2-PI/Koleksi/` — Koleksi dokumen teks (`doc1.txt` – `doc12.txt`)
- `UTS2-PI/UTS2-PI/src/main/MainInvertedIndexBasic.java` — Entry point program (build index + pencarian interaktif)
- `UTS2-PI/UTS2-PI/src/test/ReadFileToken.java` — Pembaca file koleksi & builder index
- `UTS2-PI/UTS2-PI/src/test/TextPreprocessor.java` — Pipeline normalisasi, tokenisasi, stopword removal, dan stemming
- `UTS2-PI/UTS2-PI/src/test/Stopwords.java` — Daftar dan pengecekan stopword Bahasa Indonesia
- `UTS2-PI/UTS2-PI/src/test/IndonesianStemmer.java` — Stemmer Nazief–Adriani Java murni
- `UTS2-PI/UTS2-PI/src/test/InvertedIndexBasic.java` — Struktur data Inverted Index & query intersection
- `UTS2-PI/UTS2-PI/src/test/Term.java` — Entitas dictionary (`term` + posting list)
- `UTS2-PI/UTS2-PI/src/test/Document.java` — Entitas dokumen pada posting list
- `UTS2-PI/UTS2-PI/src/test/LinkedListOrderedUnique.java` — LinkedList terurut dan unik

---

## Cara Menjalankan

### 1. Melalui Command Line (JDK 23/24)
```powershell
javac -encoding UTF-8 --release 23 -d "UTS2-PI\UTS2-PI\build\classes" (Get-ChildItem "UTS2-PI\UTS2-PI\src\*.java" -Recurse | ForEach-Object { $_.FullName })
cd UTS2-PI\UTS2-PI
java -cp "build\classes" main.MainInvertedIndexBasic
```

### 2. Melalui NetBeans
Buka folder `UTS2-PI\UTS2-PI` sebagai NetBeans Project, lalu klik **Run Project** (`F6`). Main class telah dikonfigurasi ke `main.MainInvertedIndexBasic` pada `nbproject/project.properties`.

---

## Dokumentasi Lengkap

Penjelasan rinci mengenai arsitektur, alasan pemilihan metode & algoritma (Inverted Index, `LinkedListOrderedUnique`, Two-Pointer Intersection, Stopword Removal, Stemming Nazief–Adriani, pemisahan token, hingga penjelasan sintaks Java) tersedia di [DOKUMENTASI.md](DOKUMENTASI.md).
