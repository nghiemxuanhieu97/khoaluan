package congvanservice.repositories;

import congvanservice.models.CongVan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CongVanRepository extends JpaRepository<CongVan, Integer> {
    @Query( value= "Select line.IDCongVan " +
                    "from CongVan_TuKhoa line join TuKhoa keyword  " +
                    "ON (line.IDTuKhoa = keyword.ID ) " +
                    "where keyword.TuKhoa like :key%", nativeQuery = true)
    List<Integer> findAllIDCongVan(@Param("key")String keyWord);
}
