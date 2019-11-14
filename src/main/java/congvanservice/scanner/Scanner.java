package congvanservice.scanner;

import java.io.IOException;

public class Scanner {
    //Lưu File
    //Scanner
    public static void imageToText(String src, String dst) throws IOException {
//        Example command
//        cmd /c cmd.exe /K \"tesseract C:\\Users\\HieuNghiem\\Desktop\\sample.png C:\\Users\\HieuNghiem\\Desktop\\sample.txt -l vie
//        cmd /c cmd.exe /K tesseract "G:\\Java\\Java Project\\CongVanService\\hinh-cong-van\\sample.png" "G:\\Java\\Java Project\\CongVanService\\hinh-cong-van\\sample.txt"
//        -l vie
        String command = "cmd /c cmd.exe /K tesseract "+"\""+src+"\""+" "+"\""+dst+"\""+" -l vie";
        Runtime.getRuntime()
                .exec(command);
    }
    public static void imageToPDF(String src, String dst) throws IOException {
        String command = "cmd /c cmd.exe /K tesseract -l vie "+"\""+src+"\""+" "+"\""+dst+"\""+" pdf";
        Runtime.getRuntime()
                .exec(command);
    }

}
