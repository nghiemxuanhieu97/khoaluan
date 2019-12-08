package congvanservice.services;

import congvanservice.models.CongVan_TuKhoa;
import java.util.List;
import java.util.Optional;

public interface CongVan_TuKhoa_Service {
    //CRUD
    Optional<CongVan_TuKhoa> findDongById(Integer id);
    CongVan_TuKhoa saveDong(CongVan_TuKhoa congVanTuKhoa);
    CongVan_TuKhoa updateDong(CongVan_TuKhoa congVanTuKhoa);
    void deleteCongVan_TuKhoa(Integer id);
    //Other
    List<CongVan_TuKhoa> findAll();
    void deleteCongVan_TuKhoaByIdCongVan(Integer id);
}
