package congvanservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity()
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
    public TuKhoa() { }
    public TuKhoa(String tuKhoa) {
        this.tuKhoa = tuKhoa;
    }

    public TuKhoa(Integer id, String tuKhoa) {
        this.id = id;
        this.tuKhoa = tuKhoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTuKhoa() {
        return tuKhoa;
    }

    public void setTuKhoa(String tuKhoa) {
        this.tuKhoa = tuKhoa;
    }
}
