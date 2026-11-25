/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Pembaca koleksi dokumen dari direktori untuk memecah teks menjadi token
 * terproses (bebas stopword dan sudah di-stem) lalu membangun Inverted Index.
 *
 * @author RACHEL RAMBU
 */
public class ReadFileToken {

    /**
     * Membaca seluruh file di dalam direktori {@code path}, menjalankan
     * {@link TextPreprocessor#preprocess(String)} pada setiap baris teks,
     * dan memasukkan setiap term hasil ke dalam {@link InvertedIndexBasic}.
     *
     * @param path path direktori yang berisi file-file teks koleksi
     * @return objek {@link InvertedIndexBasic} yang sudah terisi seluruh term dan posting list
     */
    public InvertedIndexBasic readDocuments(String path) {

        File name = new File(path);

        InvertedIndexBasic invertedIndex = new InvertedIndexBasic();

        // Pastikan path ada dan merupakan direktori (folder)
        if (name.exists() && name.isDirectory()) {

            // Ambil daftar seluruh nama file/subfolder di dalam direktori
            String directory[] = name.list();

            for (String directoryName : directory) {

                // Bentuk objek File untuk setiap item menggunakan pemisah path OS (File.separator)
                File file = new File(name.getAbsolutePath() + File.separator + directoryName);

                // Proses hanya jika item adalah file biasa (bukan subfolder)
                if (file.isFile()) {

                    try {
                        // Gunakan encoding UTF-8 agar pembacaan karakter konsisten di Windows/Linux
                        Scanner input = new Scanner(file, "UTF-8");

                        while (input.hasNextLine()) {

                            String sentence = input.nextLine();
                            // Pra-pemrosesan: lowercase, pisah token non-alfanumerik, buang stopword, lalu stem
                            List<String> tokens = TextPreprocessor.preprocess(sentence);

                            for (String token : tokens) {
                                System.out.println(directoryName + " " + token);
                                invertedIndex.add(token, directoryName);
                            }
                        }

                        input.close();

                    } catch (FileNotFoundException e) {
                        System.err.println("Error opening file.");
                    }
                }
            }

        } else {
            System.out.println(path + " does not exist.");
        }

        return invertedIndex;
    }
}
//membaca dokumen, memisahkan teks menjadi token lalu memasukkan dlm inverted index.