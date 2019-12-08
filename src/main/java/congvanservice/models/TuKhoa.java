package congvanservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity()
@Data
@Table(name="TuKhoa")
@ApiModel(description = "Từ khoá")
public class TuKhoa {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID của từ khoá")
    Integer id;

    @Column(name="TuKhoa", columnDefinition = "nvarchar", length = 4000)
    @ApiModelProperty(notes = "Từ khoá")
    String tuKhoa;
}
