package congvanservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity()
@Data
@Table(name="CongVan_TuKhoa", schema = "dbo")
@ApiModel(description = "Công văn - Từ khoá")
public class CongVan_TuKhoa {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID của dòng")
    Integer id;

    @Column(name="IDCongVan")
    @ApiModelProperty(notes = "Cho biết công văn có chứa từ khoá")
    Integer idCongVan;

    @Column(name="IDTuKhoa")
    @ApiModelProperty(notes = "Cho biết từ khoá thuộc công văn")
    Integer idTuKhoa;

    public CongVan_TuKhoa(Integer idCongVan, Integer idTuKhoa) {
        this.idCongVan = idCongVan;
        this.idTuKhoa = idTuKhoa;
    }
}
