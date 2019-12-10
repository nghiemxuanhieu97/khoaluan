package congvanservice.repositories;

import congvanservice.models.CongVan_TuKhoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CongVan_TuKhoa_Repository extends JpaRepository<CongVan_TuKhoa, Integer> {
    @Query(value = "DELETE from CongVan_TuKhoa where CongVan_TuKhoa.IDCongVan = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteCongVan_TuKhoaByIdCongVan(Integer id);
}
