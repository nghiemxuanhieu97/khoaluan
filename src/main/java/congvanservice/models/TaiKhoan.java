package congvanservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="TaiKhoan")
@ApiModel(description = "Tài khoản")
public class TaiKhoan {
    @Id
    @Column(name = "MaTaiKhoan")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Mã tài khoản")
    Integer maTaiKhoan;

    @Column(name = "TenTaiKhoan", length = 30, nullable = false, unique = true)
    @ApiModelProperty(notes = "Tên tài khoản")

    String username;

    @Column(name = "MatKhau", length = 50)
    @ApiModelProperty(notes = "Tên tài khoản")
    String password;

    @Column(name = "HoTen", columnDefinition = "nvarchar", length = 50)
    @ApiModelProperty(notes = "Họ tên")
    String hoTen;

    @Column(name = "Email", length = 50)
    @ApiModelProperty(notes = "Email")
    String email;

    @Column(name = "DiaChi", length = 50)
    @ApiModelProperty(notes = "Địa chỉ")
    String diaChi;

    @Column(name = "PhanQuyen", length = 10)
    @ApiModelProperty(notes = "Phân quyền")
    String phanQuyen;

    @Column(name = "TrangThai")
    @ApiModelProperty(notes = "Trạng thái")
    Boolean trangThai;

    @Column(name = "SDT", length = 10)
    @ApiModelProperty(notes = "Số điện thoại")
    String sdt;

    @Column(name= "LastedToken")
    @ApiModelProperty(notes = "Token mới nhất")
    String token;

    public TaiKhoan(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public TaiKhoan() {
    }

    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(String phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}