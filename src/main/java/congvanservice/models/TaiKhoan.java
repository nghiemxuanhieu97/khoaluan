package congvanservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="TaiKhoan")
@ApiModel(description = "Tài khoản")
public class TaiKhoan {
    @Id
    @Column(name = "MaTaiKhoan")
    @ApiModelProperty(notes = "Mã tài khoản")
    Integer maTaiKhoan;


    @Column(name = "TenTaiKhoan", length = 30, nullable = false, unique = true)
    @ApiModelProperty(notes = "Tên tài khoản")
    String tenTaiKhoan;

    @Column(name = "MatKhau", length = 50)
    @ApiModelProperty(notes = "Tên tài khoản")
    String matKhau;

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

    public TaiKhoan(String tenTaiKhoan, String matKhau) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }
}