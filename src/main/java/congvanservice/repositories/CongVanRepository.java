package congvanservice.repositories;

import congvanservice.models.CongVan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongVanRepository extends JpaRepository<CongVan, Integer> {
    @Query("select line.idCongVan from CongVan_TuKhoa line, TuKhoa keyword where line.idTuKhoa = keyword.id and keyword.tuKhoa like N'%?1%'")
    List<Integer> findAllIDCongVan(String keyword);
}
