package congvanservice.controllers;

import congvanservice.scanner.CongVanContent;
import congvanservice.scanner.ReadPDF;
import congvanservice.scanner.Scanner;
import congvanservice.scanner.UploadFileResponse;
import congvanservice.services.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value = "Hệ thống quản lý tài nguyên", tags = {"Hệ thống quản lý tài nguyên"})
@SwaggerDefinition(tags = {@Tag(name = "Hệ thống quản lý tài nguyên", description = "a")})
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value="/uploadFile")
    UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
            String fileName = fileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            try {
                String src = fileStorageService.getFileStorageLocation().toString()+"\\"+fileName;
                String dst = fileStorageService.getFileStorageLocation().toString()+"\\"+fileName.split("\\.")[0];
//              Scanner.imageToText(src.replace("\\","\\\\"), dst.replace("\\","\\\\"));
                Scanner.imageToPDF(src.replace("\\","\\\\"), dst.replace("\\","\\\\"));
                ReadPDF.readPDF(fileStorageService.getFileStorageLocation().toString()+"\\"+fileName.split("\\.")[0]+".pdf");
            } catch (IOException | InterruptedException e) {
                e.getStackTrace();
            }

            return new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file: files) {
            fileStorageService.storeFile(file);
        }
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Lấy tài nguyên lên
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Define content-type của tập tin
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        // Content-type mặc định
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/readFile")
    public ResponseEntity<CongVanContent> readFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws InterruptedException, IOException {
        String fileName = fileStorageService.storeFile(file);
        String content = "";
        String src = fileStorageService.getFileStorageLocation().toString() + "\\" + fileName;
        String dst = fileStorageService.getFileStorageLocation().toString() + "\\" + fileName.split("\\.")[0];
        //Lưu file PDF vào đường dẫn đã định
        Scanner.imageToPDF(src.replace("\\", "\\\\"), dst.replace("\\", "\\\\"));
        content = ReadPDF.readPDF(fileStorageService.getFileStorageLocation().toString() + "\\" + fileName.split("\\.")[0] + ".pdf");

        CongVanContent congVanContent = new CongVanContent(fileName.split("\\.")[0], content);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/json"))
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(congVanContent);
    }
}
