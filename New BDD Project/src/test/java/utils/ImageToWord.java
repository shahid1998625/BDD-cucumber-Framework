package utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.util.Units;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageToWord {

    public static void main(String[] args) {
        // Sample base64 encoded images in an ArrayList
        ArrayList<String> base64Images = new ArrayList<>();
        base64Images.add("..."); // Add your base64 strings here

        String outputPath = "output.docx"; // Specify the output path

        try {
            appendImagesToWord(base64Images, outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendImagesToWord(ArrayList<String> base64Images, String outputPath) throws IOException {
        XWPFDocument document = new XWPFDocument();

        for (String base64Image : base64Images) {
            // Decode base64 string to byte array
            byte[] imageBytes = Base64.decodeBase64(base64Image);

            // Convert byte array to InputStream
            try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
                // Add image to the document
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.addBreak();
                run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, "image1.jpg", Units.toEMU(500), Units.toEMU(300)); // Adjust the size as needed
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Save the document to the specified output path
        try (FileOutputStream out = new FileOutputStream(new File(outputPath))) {
            document.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the document
            document.close();
        }
    }
}
