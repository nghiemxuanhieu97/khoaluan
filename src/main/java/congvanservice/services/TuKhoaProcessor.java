package congvanservice.services;

import congvanservice.models.TuKhoa;
import congvanservice.repositories.TuKhoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TuKhoaProcessor implements TuKhoaService {
    @Autowired
    private TuKhoaRepository tuKhoaRepository;

    @Override
    public Optional<TuKhoa> findTuKhoaById(Integer id) {
        return tuKhoaRepository.findById(id);
    }

    @Override
    public TuKhoa saveTuKhoa(TuKhoa tuKhoa) {
        System.out.println("Save successfully");
        return tuKhoaRepository.save(tuKhoa);
    }

    @Override
    public TuKhoa updateTuKhoa(TuKhoa tuKhoa) {
        System.out.println("Update successfully");
        return tuKhoaRepository.save(tuKhoa);
    }

    @Override
    public void deleteTuKhoa(Integer id) {
        System.out.println("Delete successfully");
        tuKhoaRepository.deleteById(id);
    }

    @Override
    public List<TuKhoa> findAll() {
        return tuKhoaRepository.findAll();
    }

    @Override
    public List<String> tuKhoaList() {
        return tuKhoaRepository.tuKhoaList();
    }
}
