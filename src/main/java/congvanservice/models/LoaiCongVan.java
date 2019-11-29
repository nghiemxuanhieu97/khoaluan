package congvanservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LoaiCongVan")
@ApiModel(description = "Loại công văn")
public class LoaiCongVan {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Maloai")
    @ApiModelProperty(notes = "Mã loại")
    private Integer maLoai;

    @Column(name = "TenLoai", columnDefinition = "nvarchar", length = 50)
    @ApiModelProperty(notes = "Tên Loại")
    private String tenLoai;

    @Column(name = "Mota", columnDefinition = "nvarchar", length = 300)
    @ApiModelProperty(notes = "Mô tả")
    private String moTa;

}
