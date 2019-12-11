package congvanservice.controllers;

import congvanservice.exceptions.ResourceExistException;
import congvanservice.exceptions.ResourceNotFoundException;
import congvanservice.models.LoaiCongVan;
import congvanservice.services.LoaiCongVanService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "Hệ thống quản lý loại công văn", tags = {"Hệ thống quản lý loại công văn"})
@SwaggerDefinition(tags = {@Tag(name = "Hệ thống quản lý loại công văn", description = "a")})
public class LoaiCongVanController {
    @Autowired
    private LoaiCongVanService loaiCongVanService;

    @ApiOperation(value = "Tìm kiếm loại công văn theo mã loại")
    @GetMapping(value="/loaicongvan/{maloai}")
    public ResponseEntity<LoaiCongVan> getLoaiCongVan(@PathVariable(name="maloai") Integer maLoai) throws ResourceNotFoundException {
        LoaiCongVan loaiCongVan = loaiCongVanService.findLoaiCongVanById(maLoai)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy loại công văn với mã loại = "+maLoai));
        return ResponseEntity.ok().body(loaiCongVan);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Đã lấy dữ liệu thành công"),
            @ApiResponse(code = 401, message = "Bạn không được uỷ quyền để xem tài nguyên này"),
            @ApiResponse(code = 403, message = "Tài nguyên bạn đang cố truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy tài nguyên")
    })

    @ApiOperation(value = "Xem danh sách các loại công văn")
    @GetMapping(value="/loaicongvan")
    public List<LoaiCongVan> getAllLoaiCongVan(@RequestParam(name="limit", required = false) Integer limit,
                                               @RequestParam(name="offset", required = false) Integer offset) throws ResourceNotFoundException {
        List<LoaiCongVan> loaicongVanList = loaiCongVanService.findAll();
        offset = offset == null? 0 : offset;
        limit = limit == null? loaicongVanList.size() : limit;
        if(limit > loaicongVanList.size() && offset == 0) {
            return loaicongVanList.subList(0, loaicongVanList.size());
        }
        if(offset >= ((loaicongVanList.size()/limit)+(loaicongVanList.size()%limit))) {
            throw new ResourceNotFoundException("Không tìm thấy tài nguyên ở offset thứ: " + offset);
        }
        return loaicongVanList.subList(offset * limit, Math.min((offset * limit + limit), loaicongVanList.size()));
    }

    @ApiOperation(value = "Thêm một loại công văn mới")
    @PostMapping(value = "/loaicongvan")
    public LoaiCongVan createLoaiCongVan(@RequestBody LoaiCongVan loaiCongVan) throws ResourceExistException {
        Optional<LoaiCongVan> loaiCongVan1 = loaiCongVanService.findLoaiCongVanById(loaiCongVan.getMaLoai());
        if(loaiCongVan1.isPresent()) {
            throw new ResourceExistException("Loại công văn đã tồn tại.");
        }
        return loaiCongVanService.saveLoaiCongVan(loaiCongVan);
    }

    @ApiOperation(value = "Cập nhật loại công văn")
    @PutMapping(value = "/loaicongvan/{maloai}")
    public ResponseEntity<LoaiCongVan> updateLoaiCongVan(@PathVariable(name="maloai")Integer maLoai,
                                                 @RequestBody LoaiCongVan loaiCongVan) throws ResourceNotFoundException {
        LoaiCongVan loaiCongVan1 = loaiCongVanService.findLoaiCongVanById(maLoai).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy loại công văn với mã loại = "+maLoai));
        loaiCongVanService.updateLoaiCongVan(loaiCongVan);
        return ResponseEntity.ok(loaiCongVan);
    }

    @ApiOperation(value = "Xoá loại công văn")
    @DeleteMapping(value = "/loaicongvan/{maloai}")
    public Map<String, Boolean> deleteLoaiCongVan(@PathVariable(name="maloai") Integer maLoai) throws ResourceNotFoundException {
        LoaiCongVan loaiCongVan1 = loaiCongVanService.findLoaiCongVanById(maLoai).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy loại công văn với mã loại = "+maLoai));
        loaiCongVanService.deleteLoaiCongVan(maLoai);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Xoá thành công", Boolean.TRUE);
        return response;

    }
}
