
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "E:\\college\\SP\\SP_IMAGE\\SP_IMAGE\\src\\inFile1.txt";
        String outputFilePath = "E:\\college\\SP\\SP_IMAGE\\SP_IMAGE\\src\\outFile1.txt";

        try {
            List<String> imageURLs = readURLsFromFile(inputFilePath);
            writeURLsToFile(outputFilePath, imageURLs);
            downloadImageFiles(imageURLs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeURLsToFile(String filePath, List<String> imageURLs) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String url : imageURLs) {
                writer.write(url);
                writer.newLine();
            }
        }
    }
    private static String getFileNameFromURL(String url) {
        int lastSlashIndex = url.lastIndexOf('/');
        String fileName = url.substring(lastSlashIndex + 1);
        return fileName;
    }

    private static void downloadImageFiles(List<String> imageURLs) throws IOException {
        String downloadDirectory = "E:\\college\\SP\\SP_IMAGE\\SP_IMAGE\\src";
        for (String url : imageURLs) {
            String fileName = getFileNameFromURL(url);
            URL downloadURL = new URL(url);
            Path targetPath = new File(downloadDirectory, fileName).toPath();
            Files.copy(downloadURL.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static List<String> readURLsFromFile(String filePath) throws IOException {
        List<String> imageURLs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                imageURLs.add(line);
            }
        }

        return imageURLs;
    }
}