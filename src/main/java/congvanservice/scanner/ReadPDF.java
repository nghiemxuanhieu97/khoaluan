package congvanservice.scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class ReadPDF {
    public static String readPDF(String src) {
        String pdfFileInText = "";
        try {
            PDDocument document = PDDocument.load(new File(src));
//            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                pdfFileInText = tStripper.getText(document);
//                System.out.println("Text:" + pdfFileInText);

                // split by whitespace
//                String[] lines = pdfFileInText.split("\\r?\\n");
//                for (String line : lines) {
//                    System.out.println(line);
//                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot upload pdf to read");
        }
        return pdfFileInText;
    }
}
