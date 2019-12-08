package congvanservice.repositories;

import congvanservice.models.CongVan_TuKhoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CongVan_TuKhoa_Repository extends JpaRepository<CongVan_TuKhoa, Integer> {
    @Query(value = "DELETE FROM CongVan_TuKhoa s WHERE s.idCongVan = ?1")
    void deleteCongVan_TuKhoaByIdCongVan(Integer id);
}
