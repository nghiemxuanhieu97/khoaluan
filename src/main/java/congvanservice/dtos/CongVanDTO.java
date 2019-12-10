package congvanservice.dtos;

import congvanservice.models.CongVan;
import javax.persistence.Column;
import java.util.List;

public class CongVanDTO  extends CongVan {

    private List<String> keyword;
    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

}
