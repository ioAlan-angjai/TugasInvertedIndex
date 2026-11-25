/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
import java.util.ListIterator;

/**
 * Struktur data utama Inverted Index yang memetakan setiap {@link Term}
 * (dictionary terurut) ke daftar {@link Document} (posting list terurut),
 * serta menyediakan operasi pencarian boolean AND antar kata kunci.
 *
 * @author RACHEL RAMBU
 */
public class InvertedIndexBasic {

    /** Dictionary terurut berisi seluruh term unik dan posting list-nya. */
    private LinkedListOrderedUnique<Term> termList; // dictionary struktur datanya linkedlist 

    /**
     * Menginisialisasi Inverted Index kosong.
     */
    public InvertedIndexBasic() {
        termList = new LinkedListOrderedUnique<Term>();
    }

    /**
     * Mengambil daftar seluruh term (dictionary) di dalam index.
     *
     * @return LinkedListOrderedUnique berisi objek Term
     */
    public LinkedListOrderedUnique getTermList() {
        return termList;
    }

    /**
     * Menambahkan pasangan (term, nama dokumen) ke dalam Inverted Index.
     * Jika term belum ada di dictionary, simpul Term baru dibuat dan disisipkan terurut.
     * Jika sudah ada, nama dokumen ditambahkan ke posting list term tersebut.
     *
     * @param pTerm term hasil pra-pemrosesan
     * @param pDocument nama file dokumen asal
     */
    public void add(String pTerm, String pDocument) {//token dan doc dimasukkan kesini

        // cari pTerm dalam Term list menggunakan objek Term sementara sebagai kunci
        Term vTerm = termList.get(new Term(pTerm));//new Term karena yang dicari adalah kumpulan term 
        // Jika term belum ada
        if (vTerm == null) {
            // Buat simpul Term list
            Term oTerm = new Term(pTerm);
            // tambahkan simpul document ke documentList dari oTerm
            oTerm.addDocument(pDocument);
            // Tambahkan term object oTerm baru pertama ke termList;
            termList.addSort(oTerm);
        } // Jika term sudah ada
        else {
            // tambahkan simpul document ke documentList dari pTerm
            vTerm.addDocument(pDocument);
        }
    }
    
    /**
     * Melakukan irisan (boolean AND) terhadap dua posting list yang sudah terurut
     * menggunakan algoritma two-pointer melalui {@link ListIterator}.
     *
     * @param p1 posting list pertama (terurut naik)
     * @param p2 posting list kedua (terurut naik)
     * @return posting list baru berisi dokumen yang muncul di kedua list
     */
    //processing boolean queries (intersection) 
    private LinkedListOrderedUnique<Document> intersect(
            LinkedListOrderedUnique<Document> p1,
            LinkedListOrderedUnique<Document> p2) {

        LinkedListOrderedUnique<Document> answer  = new LinkedListOrderedUnique<Document>();
               
        ListIterator<Document> iterator1 = p1.listIterator();
        ListIterator<Document> iterator2 = p2.listIterator();

        Document doc1 = null;
        Document doc2 = null;

        // Ambil elemen awal dari masing-masing posting list
        if (iterator1.hasNext()) {
            doc1 = iterator1.next();
        }

        if (iterator2.hasNext()) {
            doc2 = iterator2.next();
        }

        // Bandingkan doc1 dan doc2 secara simultan (kompleksitas O(|p1| + |p2|))
        while (doc1 != null && doc2 != null) {
            int comparison = doc1.compareTo(doc2);
            if (comparison == 0) {
                // Sama: masukkan ke hasil irisan, lalu majukan kedua pointer
                answer.addSort(doc1);
                if (iterator1.hasNext()) {
                    doc1 = iterator1.next();
                } else {
                    doc1 = null;
                }

                if (iterator2.hasNext()) {
                    doc2 = iterator2.next();
                } else {
                    doc2 = null;
                }

            } else if (comparison < 0) {
                // doc1 lebih kecil: majukan pointer list 1 untuk mengejar doc2
                if (iterator1.hasNext()) {
                    doc1 = iterator1.next();
                } else {
                    doc1 = null;
                }

            } else {
                // doc2 lebih kecil: majukan pointer list 2 untuk mengejar doc1
                if (iterator2.hasNext()) {
                    doc2 = iterator2.next();
                } else {
                    doc2 = null;
                }
            }
        }

        return answer;
    }

    /**
     * Mencari dokumen yang mengandung semua kata kunci pada {@code keywords} (boolean AND).
     * Kata kunci terlebih dahulu diproses dengan {@link TextPreprocessor#preprocess(String)}
     * agar pemisahan token, penghapusan stopword, dan stemming konsisten dengan proses indexing.
     *
     * @param keywords string query dari pengguna (bisa satu atau lebih kata)
     * @return daftar dokumen terurut yang cocok dengan seluruh term query
     */
    public LinkedListOrderedUnique<Document> searchTerm(String keywords) {
        LinkedListOrderedUnique<Document> result = new LinkedListOrderedUnique<Document>(); //linkedlist yang digunakan untuk menampung dokumen 

        // Normalisasi, pemisahan token, filter stopword, dan stemming query
        List<String> keywordList = TextPreprocessor.preprocess(keywords);
        if (keywordList.isEmpty()) {
            return result;
        }

        // Ambil posting list dari keyword pertama sebagai himpunan kandidat awal
        Term firstTerm = termList.get(new Term(keywordList.get(0))); // mencari keyword pertama
        if (firstTerm == null) {
            return result;
        }

        for (Document doc : firstTerm.getDocumentList()) { // mengambil posting list keyword pertama
            result.addSort(doc);
        }

        // Lakukan irisan (intersect) berurutan untuk setiap keyword berikutnya
        for (int i = 1; i < keywordList.size(); i++) {
            Term nextTerm = termList.get(new Term(keywordList.get(i)));

            // Jika salah satu term tidak ditemukan di index, hasil AND pasti kosong
            if (nextTerm == null) {
                result.clear();
                return result;
            }
            result = intersect(result, nextTerm.getDocumentList());

            // Early exit: hentikan iterasi jika irisan sementara sudah kosong
            if (result.isEmpty()) {
                break;
            }
        }

        return result;
    }

    /**
     * Mengembalikan seluruh isi dictionary dan posting list sebagai string.
     *
     * @return representasi teks Inverted Index
     */
    @Override
    public String toString() {
        return termList.toString();
    }

}
//membangun dictionary, posting list, dan menjalankan pencarian