package congvanservice.services;

import congvanservice.models.Dong;
import congvanservice.repositories.DongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DongProcessor implements DongService {
    @Autowired
    private DongRepository dongRepository;

    @Override
    public Optional<Dong> findDongById(Integer id) {
        return dongRepository.findById(id);
    }

    @Override
    public Dong saveDong(Dong dong) {
        System.out.println("Save successfully");
        return dongRepository.save(dong);
    }

    @Override
    public Dong updateDong(Dong dong) {
        System.out.println("Update successfully");
        return dongRepository.save(dong);
    }

    @Override
    public void deleteDong(Integer id) {
        System.out.println("Delete successfully");
        dongRepository.deleteById(id);
    }

    @Override
    public List<Dong> findAll() {
        return dongRepository.findAll();
    }
}
