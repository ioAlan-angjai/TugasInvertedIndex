package test;

/**
 * Entri kata (term) pada dictionary beserta posting list dokumennya.
 *
 * @author RACHEL RAMBU
 */
public class Term implements Comparable<Term> {

    protected String term; // Kata dasar (dictionary)
    protected LinkedListOrderedUnique<Document> documentList; // Posting list terurut

    public Term(String inTerm) {
        term = inTerm;
        documentList = new LinkedListOrderedUnique<Document>();
    }

    /** Menambahkan dokumen ke posting list jika belum tercatat. */
    public void addDocument(String doc) {
        Document existingDoc = documentList.get(new Document(doc));
        if (existingDoc == null) {
            documentList.addSort(new Document(doc));
        }
    }

    public String getTerm() {
        return term;
    }

    public LinkedListOrderedUnique<Document> getDocumentList() {
        return documentList;
    }

    /** Membandingkan term secara alfabetis untuk pengurutan dictionary. */
    public int compareTo(Term o) {
        return term.compareTo(o.term);
    }

    @Override
    public String toString() {
        String temp = term + " => ";
        temp = temp + documentList.toString();
        return temp;
    }

}