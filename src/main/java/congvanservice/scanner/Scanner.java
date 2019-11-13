package congvanservice.scanner;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;


public class Scanner {
    //Lưu File
    //Scanner
    public static void scanner() {
        File imageFile = new File("D:\\Java Project\\khoaluan\\src\\main\\resources\\sample.png");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setLanguage("vie");
        instance.setDatapath("D:\\Java Project\\khoaluan\\src\\main\\resources"); // path to tessdata directory
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

}
