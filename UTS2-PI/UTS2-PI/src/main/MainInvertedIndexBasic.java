package main;
import test.InvertedIndexBasic;
import java.util.Scanner;
import test.Document;
import test.LinkedListOrderedUnique;
import test.ReadFileToken;

/**
 * Entry point aplikasi Inverted Index dan pencarian dokumen.
 *
 * @author RACHEL RAMBU
 */
public class MainInvertedIndexBasic {

    /** Membaca koleksi, membangun index, lalu mencari keyword dari input pengguna. */
    public static void main(String[] args) {
        // Baca dokumen dan bangun Inverted Index
        ReadFileToken reader = new ReadFileToken();
        InvertedIndexBasic cobaList = reader.readDocuments("..\\Koleksi");

        System.out.println("\n===== INVERTED INDEX =====");
        System.out.println(cobaList);

        // Input keyword pencarian dari konsol
        Scanner search = new Scanner(System.in);
        System.out.println("\n===== SEARCHING =====");
        System.out.print("Masukkan keyword: ");

        String keyword = search.nextLine();
        LinkedListOrderedUnique<Document> hasil = cobaList.searchTerm(keyword);
        System.out.println("\nHasil pencarian:");
        if (hasil.isEmpty()) {
            System.out.println("Keyword tidak ditemukan !");
        } else {
            System.out.println(hasil);
        }
    }
}
