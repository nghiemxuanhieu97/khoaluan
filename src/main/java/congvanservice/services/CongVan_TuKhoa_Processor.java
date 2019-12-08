package congvanservice.services;

import congvanservice.models.CongVan_TuKhoa;
import congvanservice.repositories.CongVan_TuKhoa_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongVan_TuKhoa_Processor implements CongVan_TuKhoa_Service {
    @Autowired
    private CongVan_TuKhoa_Repository congVanTuKhoaRepository;

    @Override
    public Optional<CongVan_TuKhoa> findDongById(Integer id) {
        return congVanTuKhoaRepository.findById(id);
    }

    @Override
    public CongVan_TuKhoa saveDong(CongVan_TuKhoa congVanTuKhoa) {
        System.out.println("Save successfully");
        return congVanTuKhoaRepository.save(congVanTuKhoa);
    }

    @Override
    public CongVan_TuKhoa updateDong(CongVan_TuKhoa congVanTuKhoa) {
        System.out.println("Update successfully");
        return congVanTuKhoaRepository.save(congVanTuKhoa);
    }

    @Override
    public void deleteCongVan_TuKhoa(Integer id) {
        System.out.println("Delete successfully");
        congVanTuKhoaRepository.deleteById(id);
    }

    @Override
    public List<CongVan_TuKhoa> findAll() {
        return congVanTuKhoaRepository.findAll();
    }

    @Override
    public void deleteCongVan_TuKhoaByIdCongVan(Integer id) {
        System.out.println("Delete successfully");
        congVanTuKhoaRepository.deleteCongVan_TuKhoaByIdCongVan(id);
    }
}
