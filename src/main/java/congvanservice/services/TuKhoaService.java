package congvanservice.services;

import congvanservice.models.TuKhoa;

import java.util.List;
import java.util.Optional;

public interface TuKhoaService {
    //CRUD
    Optional<TuKhoa> findTuKhoaById(Integer id);
    TuKhoa saveTuKhoa(TuKhoa tuKhoa);
    TuKhoa updateTuKhoa(TuKhoa tuKhoa);
    void deleteTuKhoa(Integer id);
    //Other
    List<TuKhoa> findAll();
    List<String> tuKhoaList();
}
