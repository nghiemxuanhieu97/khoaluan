package congvanservice;

import congvanservice.services.Scanner;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Date


@SpringBootApplication
@EnableConfigurationProperties
public class CongVanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CongVanServiceApplication.class, args);
        long time = System.currentTimeMillis();
        Scanner.scanner();
        long now = System.currentTimeMillis() - time;
        System.out.println(now);
    }

}
