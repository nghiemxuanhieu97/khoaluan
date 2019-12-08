package congvanservice.repositories;

import congvanservice.models.TuKhoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuKhoaRepository extends JpaRepository<TuKhoa, Integer> {

    @Query("SELECT tk.tuKhoa FROM TuKhoa tk")
    List<String> tuKhoaList();
}
