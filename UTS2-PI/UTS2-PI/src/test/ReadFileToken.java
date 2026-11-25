package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Membaca koleksi dokumen teks dan memasukkannya ke Inverted Index.
 *
 * @author RACHEL RAMBU
 */
public class ReadFileToken {

    /** Membaca seluruh file UTF-8 di direktori {@code path} dan membangun index. */
    public InvertedIndexBasic readDocuments(String path) {
        File name = new File(path);
        InvertedIndexBasic invertedIndex = new InvertedIndexBasic();

        if (name.exists() && name.isDirectory()) {
            String directory[] = name.list();

            for (String directoryName : directory) {
                File file = new File(name.getAbsolutePath() + File.separator + directoryName);

                if (file.isFile()) {
                    try {
                        Scanner input = new Scanner(file, "UTF-8");

                        while (input.hasNextLine()) {
                            String sentence = input.nextLine();
                            // Pra-pemrosesan: lowercase, pisah token, stopword, stemming
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