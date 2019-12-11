package congvanservice.controllers;

import congvanservice.exceptions.ResourceExistException;
import congvanservice.exceptions.ResourceNotFoundException;
import congvanservice.models.LinhVuc;
import congvanservice.services.LinhVucService;
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
@Api(value = "Hệ thống quản lý lĩnh vực", tags = {"Hệ thống quản lý lĩnh vực"})
@SwaggerDefinition(tags = {@Tag(name = "Hệ thống quản lý lĩnh vực", description = "a")})
public class LinhVucController {
    @Autowired
    private LinhVucService linhVucService;

    @ApiOperation(value = "Tìm kiếm lĩnh vực theo mã lĩnh vực")
    @GetMapping(value="/linhvuc/{malinhvuc}")
    public ResponseEntity<LinhVuc> getLinhVuc(@PathVariable(name="malinhvuc") Integer maLinhVuc)
            throws ResourceNotFoundException {
        LinhVuc linhVuc = linhVucService.findLinhVucById(maLinhVuc)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy lĩnh vực với mã lĩnh vực = "+maLinhVuc));
        return ResponseEntity.ok().body(linhVuc);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Đã lấy dữ liệu thành công"),
            @ApiResponse(code = 401, message = "Bạn không được uỷ quyền để xem tài nguyên này"),
            @ApiResponse(code = 403, message = "Tài nguyên bạn đang cố truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy tài nguyên")
    })

    @ApiOperation(value = "Xem danh sách các lĩnh vực")
    @GetMapping(value="/linhvuc")
    public List<LinhVuc> getAllLinhVuc(@RequestParam(name="limit", required = false) Integer limit,
                                       @RequestParam(name="offset", required = false) Integer offset) throws ResourceNotFoundException {
        List<LinhVuc> linhVucList = linhVucService.findAll();
        offset = offset == null? 0 : offset;
        limit = limit == null? linhVucList.size() : limit;
        if(limit > linhVucList.size() && offset == 0) {
            return linhVucList.subList(0, linhVucList.size());
        }
        if(offset >= ((linhVucList.size()/limit)+(linhVucList.size()%limit))) {
            throw new ResourceNotFoundException("Không tìm thấy tài nguyên ở offset thứ: " + offset);
        }
        return linhVucList.subList(offset * limit, Math.min((offset * limit + limit), linhVucList.size()));
    }

    @ApiOperation(value = "Thêm một lĩnh vực mới")
    @PostMapping(value = "/linhvuc")
    public LinhVuc createLinhVuc(@RequestBody LinhVuc linhVuc) throws ResourceExistException {
        Optional<LinhVuc> linhvuc1 = linhVucService.findLinhVucById(linhVuc.getMaLinhVuc());
        if(linhvuc1.isPresent()) {
            throw new ResourceExistException("Lĩnh vực đã tồn tại.");
        }
        return linhVucService.saveLinhVuc(linhVuc);
    }

    @ApiOperation(value = "Cập nhật lĩnh vực")
    @PutMapping(value = "/linhvuc/{malinhvuc}")
    public ResponseEntity<LinhVuc> updateLinhVuc(@PathVariable(name="malinhvuc")Integer maLinhVuc,
                                                 @RequestBody LinhVuc linhVuc) throws ResourceNotFoundException {
        LinhVuc linhVuc1 = linhVucService.findLinhVucById(maLinhVuc).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy lĩnh vực với mã lĩnh vực = "+maLinhVuc));
        linhVucService.updateLinhVuc(linhVuc);
        return ResponseEntity.ok(linhVuc);
    }

    @ApiOperation(value = "Xoá lĩnh vực")
    @DeleteMapping(value = "/linhvuc/{malinhvuc}")
    public Map<String, Boolean> deleteLinhVuc(@PathVariable(name="malinhvuc") Integer maLinhVuc) throws ResourceNotFoundException {
        LinhVuc linhVuc1 = linhVucService.findLinhVucById(maLinhVuc).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy lĩnh vực với mã lĩnh vực = "+maLinhVuc));
        linhVucService.deleteLinhVuc(maLinhVuc);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Xoá thành công", Boolean.TRUE);
        return response;

    }
}
