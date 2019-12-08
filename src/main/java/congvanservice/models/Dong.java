package congvanservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity()
@Data
@Table(name="Dong")
@ApiModel(description = "Dòng")
public class Dong {
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

    public Dong(Integer idCongVan, Integer idTuKhoa) {
        this.idCongVan = idCongVan;
        this.idTuKhoa = idTuKhoa;
    }
}
