package test;

/**
 * Entitas dokumen pada posting list (terurut alfabetis).
 *
 * @author RACHEL RAMBU
 */
public class Document implements Comparable<Document> {

    protected String document; // Nama file dokumen

    public Document() {
    }

    public Document(String inDocument) {
        document = inDocument;
    }

    public String getDocument() {
        return document;
    }

    /** Membandingkan nama dokumen secara alfabetis untuk pengurutan dan intersect. */
    public int compareTo(Document o) {
        return document.compareTo(o.document);
    }

    public String toString() {
        return document;
    }
}