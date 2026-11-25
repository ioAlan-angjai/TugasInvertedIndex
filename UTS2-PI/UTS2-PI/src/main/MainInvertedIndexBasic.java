/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import test.InvertedIndexBasic;
import java.util.Scanner;
import test.Document;
import test.LinkedListOrderedUnique;
import test.ReadFileToken;
/**
 * Kelas utama (entry point) aplikasi Inverted Index dan pencarian dokumen.
 *
 * @author RACHEL RAMBU
 */
public class MainInvertedIndexBasic {
    /**
     * Titik masuk program: membaca koleksi dokumen, membangun inverted index,
     * menampilkannya ke konsol, lalu menerima input kata kunci pencarian dari pengguna.
     *
     * @param args argumen baris perintah (tidak digunakan)
     */
    public static void main(String[] args) {
        // Membaca dokumen dan membangun Inverted Index dari folder koleksi relatif proyek
        ReadFileToken reader = new ReadFileToken();
        InvertedIndexBasic cobaList = reader.readDocuments("..\\Koleksi");

        // Menampilkan seluruh isi Inverted Index (term => daftar dokumen)
        System.out.println("\n===== INVERTED INDEX =====");
        System.out.println(cobaList);

        // Searching: membaca satu baris keyword dari input standar (keyboard)
        Scanner search = new Scanner(System.in);

        System.out.println("\n===== SEARCHING =====");
        System.out.print("Masukkan keyword: ");

        String keyword = search.nextLine();
        // Memanggil searchTerm yang otomatis melakukan normalisasi, stopword removal, dan stemming
        LinkedListOrderedUnique<Document> hasil = cobaList.searchTerm(keyword);
        System.out.println("\nHasil pencarian:");
        if (hasil.isEmpty()) {
            System.out.println("Keyword tidak ditemukan !");
        } else {
            System.out.println(hasil);
        }
    }
}
