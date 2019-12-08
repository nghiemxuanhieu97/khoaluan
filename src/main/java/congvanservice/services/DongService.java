package congvanservice.services;

import congvanservice.models.Dong;

import java.util.List;
import java.util.Optional;

public interface DongService {
    //CRUD
    Optional<Dong> findDongById(Integer id);
    Dong saveDong(Dong dong);
    Dong updateDong(Dong dong);
    void deleteDong(Integer id);
    //Other
    List<Dong> findAll();
}
