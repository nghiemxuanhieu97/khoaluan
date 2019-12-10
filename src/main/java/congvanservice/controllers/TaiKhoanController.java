package congvanservice.controllers;

import congvanservice.exceptions.ResourceExistException;
import congvanservice.exceptions.ResourceNotFoundException;
import congvanservice.exceptions.UserNotActiveException;
import congvanservice.jwt.JwtTokenProvider;
import congvanservice.models.CustomUserDetails;
import congvanservice.models.TaiKhoan;
import congvanservice.services.TaiKhoanService;
import io.swagger.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "Hệ thống quản lý tài khoản", tags = {"Hệ thống quản lý tài khoản"})
@SwaggerDefinition(tags = {@Tag(name = "Hệ thống quản lý tài khoản", description = "a")})
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation(value = "Tìm kiếm tài khoản theo mã tài khoản")
    @GetMapping(value="/taikhoan/{mataikhoan}")
    public ResponseEntity<TaiKhoan> getTaiKhoan(@PathVariable(name="mataikhoan") Integer maTaiKhoan) throws ResourceNotFoundException {
        TaiKhoan taiKhoan = taiKhoanService.findTaiKhoanById(maTaiKhoan)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy tài khoản với mã tài khoản = "+maTaiKhoan));
        return ResponseEntity.ok().body(taiKhoan);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Đã lấy dữ liệu thành công"),
            @ApiResponse(code = 401, message = "Bạn không được uỷ quyền để xem tài nguyên này"),
            @ApiResponse(code = 403, message = "Tài nguyên bạn đang cố truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy tài nguyên")
    })

    @ApiOperation(value = "Xem danh sách các tài khoản")
    @GetMapping(value="/taikhoan")
    public List<TaiKhoan> getAllTaiKhoan(){
        return taiKhoanService.findAll();
    }

    @ApiOperation(value = "Thêm một tài khoản mới")
    @PostMapping(value = "/taikhoan")
    public TaiKhoan createTaiKhoan(@RequestBody TaiKhoan taiKhoan) throws ResourceExistException {
        Optional<TaiKhoan> taiKhoan1 = taiKhoanService.findTaiKhoanById(taiKhoan.getMaTaiKhoan());
        if(taiKhoan1.isPresent()) {
            throw new ResourceExistException("Tài khoản đã tồn tại.");
        }
        taiKhoan.setPassword(DigestUtils.md5Hex(taiKhoan.getPassword()));
        return taiKhoanService.saveTaiKhoan(taiKhoan);
    }

    @ApiOperation(value = "Cập nhật tài khoản")
    @PutMapping(value = "/taikhoan/{mataikhoan}")
    public ResponseEntity<TaiKhoan> updateTaiKhoan(@PathVariable(name="mataikhoan")Integer maTaiKhoan,
                                                 @RequestBody TaiKhoan taiKhoan) throws ResourceNotFoundException {
        TaiKhoan taiKhoan1 = taiKhoanService.findTaiKhoanById(maTaiKhoan).orElseThrow(()->
                new ResourceNotFoundException("Không tìm thấy tài khoản với mã tài khoản = "+maTaiKhoan));
        taiKhoan.setPassword(DigestUtils.md5Hex(taiKhoan.getPassword()));
        taiKhoanService.updateTaiKhoan(taiKhoan);
        return ResponseEntity.ok(taiKhoan);
    }

    @ApiOperation(value = "Xoá tài khoản")
    @DeleteMapping(value = "/taikhoan/{mataikhoan}")
    public Map<String, Boolean> deleteTaiKhoan(@PathVariable(name="mataikhoan") Integer maTaiKhoan) throws ResourceNotFoundException {
        TaiKhoan taiKhoan1 = taiKhoanService.findTaiKhoanById(maTaiKhoan).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy tài khoản với mã tài khoản = "+maTaiKhoan));
        taiKhoanService.deleteTaiKhoan(maTaiKhoan);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Xoá thành công", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "Đăng nhập")
    @PostMapping(value = "/login")
    public ResponseEntity<TaiKhoan> authenticateUser(@Valid @RequestBody TaiKhoan taiKhoan) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        taiKhoan.getUsername(),
                        DigestUtils.md5Hex(taiKhoan.getPassword())
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TaiKhoan taiKhoan1 = taiKhoanService.findTaiKhoanByUsername(taiKhoan.getUsername());
        if(!taiKhoan1.getTrangThai()) {
            throw new UserNotActiveException("User not active!");
        }
        taiKhoan1.setToken(tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal()));
        taiKhoanService.saveTaiKhoan(taiKhoan1);
        return ResponseEntity.ok(taiKhoan1);
    }
}
