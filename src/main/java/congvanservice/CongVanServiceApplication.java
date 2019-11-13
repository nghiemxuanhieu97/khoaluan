package congvanservice;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.File;

@SpringBootApplication
@EnableConfigurationProperties
public class CongVanServiceApplication {
    public static void main(String[] args) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setLanguage("vie");
            tesseract.setDatapath("G:\\Java\\Java Project\\CongVanService\\src\\main\\resources");

            String text = tesseract.doOCR(new File("C://Users//HieuNghiem//Desktop//img009.jpg"));
            System.out.print(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        SpringApplication.run(CongVanServiceApplication.class, args);
    }
}
