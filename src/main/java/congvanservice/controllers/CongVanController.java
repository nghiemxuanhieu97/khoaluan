package congvanservice.controllers;

import congvanservice.dtos.CongVanDTO;
import congvanservice.exceptions.ResourceExistException;
import congvanservice.exceptions.ResourceNotFoundException;
import congvanservice.models.CongVan;
import congvanservice.models.CongVan_TuKhoa;
import congvanservice.models.TuKhoa;
import congvanservice.services.CongVanService;
import congvanservice.services.CongVan_TuKhoa_Service;
import congvanservice.services.TuKhoaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@Api(value = "Hệ thống quản lý công văn", tags = {"Hệ thống quản lý công văn"})
@SwaggerDefinition(tags = {@Tag(name = "Hệ thống quản lý công văn", description = "a")})
public class CongVanController {
    @Autowired
    private CongVanService congVanService;
    @Autowired
    private TuKhoaService tuKhoaService;
    @Autowired
    private CongVan_TuKhoa_Service congVanTuKhoaService;

    @ApiOperation(value = "Tìm kiếm công văn theo ID")
    @GetMapping(value="/congvan/{id}")
    public ResponseEntity<CongVan> getCongVan(@PathVariable(name="id") Integer id)
            throws ResourceNotFoundException{
        CongVan congVan = congVanService.findCongVanById(id)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy công văn với id = "+id));
        return ResponseEntity.ok().body(congVan);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Đã lấy dữ liệu thành công"),
            @ApiResponse(code = 401, message = "Bạn không được uỷ quyền để xem tài nguyên này"),
            @ApiResponse(code = 403, message = "Tài nguyên bạn đang cố truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy tài nguyên")
    })

    @ApiOperation(value = "Xem danh sách các công văn")
    @GetMapping(value="/congvan")
    public List<CongVan> getAllCongVan(){
        return congVanService.findAll();
    }

    @ApiOperation(value = "Thêm một công văn mới")
    @PostMapping(value = "/congvan")
    public CongVan createCongVan(@RequestBody CongVanDTO congVanDTO) throws ResourceExistException {
        System.out.println(congVanDTO);

        CongVan congVan = new CongVan().toMap(congVanDTO);
        Optional<CongVan> congVan1 = congVanService.findCongVanById(congVan.getId());

        System.out.println(congVan);
        if(congVan1.isPresent()) {
            throw new ResourceExistException("Công văn đã tồn tại.");
        }
        //Lấy danh sách từ khoá hiện có để so sánh
        List<TuKhoa> tuDien = tuKhoaService.findAll();
        List<String> noiDungCongVan = Arrays.asList(congVan.getTrichYeu().split(" "));
        for (TuKhoa keyword : tuDien ) {
            if(!noiDungCongVan.contains(keyword.getTuKhoa())) {
                TuKhoa tuMoi = new TuKhoa();
                tuMoi.setTuKhoa(keyword.getTuKhoa());
                tuKhoaService.saveTuKhoa(tuMoi);
                congVanTuKhoaService.saveDong(new CongVan_TuKhoa(congVan.getId(),tuMoi.getId()));
            } else {
                CongVan_TuKhoa value = new CongVan_TuKhoa(congVan.getId(),keyword.getId());
                congVanTuKhoaService.saveDong(value);
            }
        }

        return congVanService.saveCongVan(congVan);
    }

    @ApiOperation(value = "Cập nhật công văn")
    @PutMapping(value = "/congvan/{id}")
    public ResponseEntity<CongVan> updateCongVan(@PathVariable(name="id")Integer id,
                                                 @RequestBody CongVan congVan) throws ResourceNotFoundException {
        CongVan congVan1 = congVanService.findCongVanById(id).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy công văn với id = "+id));
        congVanService.updateCongVan(congVan);
        return ResponseEntity.ok(congVan);
    }

    @ApiOperation(value = "Xoá công văn")
    @DeleteMapping(value = "/congvan/{id}")
    public Map<String, Boolean> deleteCongVan(@PathVariable(name="id") Integer id) throws ResourceNotFoundException {
        CongVan congVan1 = congVanService.findCongVanById(id).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy công văn với id = "+id));
        congVanTuKhoaService.deleteCongVan_TuKhoaByIdCongVan(id);
        congVanService.deleteCongVan(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Xoá thành công", Boolean.TRUE);
        return response;

    }

    @ApiOperation(value = "Tìm công văn theo từ khoá")
    @GetMapping(value = "/congvan/{keyword}")
    public List<Integer> searchIDCongVanByKeyWord(@PathVariable(name="keyword") String keyword) {
        return congVanService.findAllIDCongVan(keyword);
    }
}
