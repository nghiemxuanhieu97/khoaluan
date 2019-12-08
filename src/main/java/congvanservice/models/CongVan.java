package congvanservice.models;

import congvanservice.dtos.CongVanDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Entity()
@Data
@Table(name="CongVan")
@ApiModel(description = "Công văn")
public class CongVan {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID của công văn")
    Integer id;

    @Column(name="SoKyHieu", length = 15)
    @ApiModelProperty(notes = "Số ký hiệu")
    String soKyHieu;

    @Column(name="MucDo")
    @ApiModelProperty(notes = "Mức độ")
    Integer mucDo;

    @Column(name="TrichYeu", columnDefinition = "nvarchar", length = 4000)
    @ApiModelProperty(notes = "Trích yếu")
    String trichYeu;

    @Column(name="TapTin", length = 100)
    @ApiModelProperty(notes = "Tập tin")
    String tapTin;

    @Column(name="NguoiKy", columnDefinition = "nvarchar", length = 50)
    @ApiModelProperty(notes = "Người ký")
    String nguoiKy;

    @Column(name="CoQuanBanHanh", columnDefinition = "nvarchar", length = 50)
    @ApiModelProperty(notes = "Cơ quan ban hành")
    String coQuanBanHanh;

    @Column(name="NgayBanHanh")
    @ApiModelProperty(notes = "Ngày ban hành")
    Date ngayBanHanh;

    @Column(name="NgayCoHieuLuc")
    @ApiModelProperty(notes = "Ngày có hiệu lực")
    Date ngayCoHieuLuc;

    @Column(name="NoiNhan", columnDefinition = "nvarchar", length = 300)
    @ApiModelProperty(notes = "Nơi nhận")
    String noiNhan;

    @Column(name="MaLinhVuc")
    @ApiModelProperty(notes = "Mã lĩnh vực")
    Integer maLinhVuc;

    @Column(name="MaLoai")
    @ApiModelProperty(notes = "Mã loại")
    Integer maLoai;

    @Column(name="NoiDung", columnDefinition = "nvarchar", length = 4000)
    @ApiModelProperty(notes = "Nội dung")
    String noiDung;

    @Column(name="TimDong", columnDefinition = "nvarchar", length = 4000)
    @ApiModelProperty(notes = "Tìm dòng")
    String timDong;

     public CongVan toMap(CongVanDTO congVanDTO) {
        CongVan congVan = new CongVan();
        congVan.setId(congVanDTO.getId());
        congVan.setSoKyHieu(congVanDTO.getSoKyHieu());
        congVan.setMucDo(congVanDTO.getMucDo());
        congVan.setTrichYeu(congVanDTO.getTrichYeu());
        congVan.setTapTin(congVanDTO.getTapTin());
        congVan.setNguoiKy(congVanDTO.getNguoiKy());
        congVan.setCoQuanBanHanh(congVanDTO.getCoQuanBanHanh());
        congVan.setNgayCoHieuLuc(congVanDTO.getNgayCoHieuLuc());
        congVan.setNoiNhan(congVanDTO.getNoiNhan());
        congVan.setMaLinhVuc(congVanDTO.getMaLinhVuc());
        congVan.setMaLoai(congVanDTO.getMaLoai());
        congVan.setNoiDung(congVanDTO.getNoiDung());
        congVan.setTimDong(congVanDTO.getTimDong());
        return congVan;

    }
}
