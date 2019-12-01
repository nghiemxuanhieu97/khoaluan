package congvanservice.services;

import congvanservice.configs.FileStorageConfig;
import congvanservice.exceptions.FileStorageException;
import congvanservice.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
    import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Không thể tạo thư mục để lưu trữ tập tin upload.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // abc.png
        final String DATE_FORMATTER= "yyyyMMddHHmmssSSSSSS"; // example: 20190516231122568486
        LocalDateTime localDateTime = LocalDateTime.now(); // Lấy thời gian hiện tại
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formatFileName = localDateTime.format(formatter)+"."+fileName.split("\\.")[1];

        try {
            // Kiểm tra tên file xem có chứa ký tự không hợp không
            if(formatFileName.contains("..")) {
                throw new FileStorageException("Rất tiếc! Tên tập tin chưa ký tự không hợp lệ " + formatFileName);
            }
            // Sao chép tập tin vào thư mục đích (Overwrite nếu file tồn tại)
            Path targetLocation = this.fileStorageLocation.resolve(formatFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return formatFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Không thể lưu tập tin " + formatFileName + ". Xin hãy thử lại!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("Không tìm thấy tập tin " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("Không tìm thấy tập tin " + fileName, ex);
        }
    }

    public Path getFileStorageLocation() {
        return fileStorageLocation;
    }
}
