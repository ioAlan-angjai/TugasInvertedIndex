package test;

import java.util.List;
import java.util.ListIterator;

/**
 * Struktur data Inverted Index (dictionary Term & posting list Document) serta pencarian AND.
 *
 * @author RACHEL RAMBU
 */
public class InvertedIndexBasic {

    private LinkedListOrderedUnique<Term> termList; // Dictionary terurut

    public InvertedIndexBasic() {
        termList = new LinkedListOrderedUnique<Term>();
    }

    public LinkedListOrderedUnique getTermList() {
        return termList;
    }

    /** Menambahkan pasangan term dan dokumen ke index secara terurut & unik. */
    public void add(String pTerm, String pDocument) {
        Term vTerm = termList.get(new Term(pTerm));
        if (vTerm == null) {
            // Term baru: buat Term, tambah dokumen, lalu sisip terurut
            Term oTerm = new Term(pTerm);
            oTerm.addDocument(pDocument);
            termList.addSort(oTerm);
        } else {
            // Term sudah ada: tambah dokumen ke posting list
            vTerm.addDocument(pDocument);
        }
    }

    /** Irisan (boolean AND) dua posting list terurut dengan metode two-pointer. */
    private LinkedListOrderedUnique<Document> intersect(
            LinkedListOrderedUnique<Document> p1,
            LinkedListOrderedUnique<Document> p2) {

        LinkedListOrderedUnique<Document> answer = new LinkedListOrderedUnique<Document>();

        ListIterator<Document> iterator1 = p1.listIterator();
        ListIterator<Document> iterator2 = p2.listIterator();

        Document doc1 = null;
        Document doc2 = null;

        if (iterator1.hasNext()) {
            doc1 = iterator1.next();
        }
        if (iterator2.hasNext()) {
            doc2 = iterator2.next();
        }

        // Bandingkan dua pointer secara simultan sampai salah satu habis
        while (doc1 != null && doc2 != null) {
            int comparison = doc1.compareTo(doc2);
            if (comparison == 0) {
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
                if (iterator1.hasNext()) {
                    doc1 = iterator1.next();
                } else {
                    doc1 = null;
                }
            } else {
                if (iterator2.hasNext()) {
                    doc2 = iterator2.next();
                } else {
                    doc2 = null;
                }
            }
        }

        return answer;
    }

    /** Mencari dokumen yang memuat semua kata kunci query (boolean AND). */
    public LinkedListOrderedUnique<Document> searchTerm(String keywords) {
        LinkedListOrderedUnique<Document> result = new LinkedListOrderedUnique<Document>();

        // Terapkan preprocessing yang sama seperti saat indexing
        List<String> keywordList = TextPreprocessor.preprocess(keywords);
        if (keywordList.isEmpty()) {
            return result;
        }

        // Posting list keyword pertama sebagai kandidat awal
        Term firstTerm = termList.get(new Term(keywordList.get(0)));
        if (firstTerm == null) {
            return result;
        }

        for (Document doc : firstTerm.getDocumentList()) {
            result.addSort(doc);
        }

        // Iris berurutan dengan posting list keyword berikutnya
        for (int i = 1; i < keywordList.size(); i++) {
            Term nextTerm = termList.get(new Term(keywordList.get(i)));
            if (nextTerm == null) {
                result.clear();
                return result;
            }
            result = intersect(result, nextTerm.getDocumentList());
            if (result.isEmpty()) {
                break;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return termList.toString();
    }

}