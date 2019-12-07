package congvanservice.repositories;

import congvanservice.models.TaiKhoan;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    TaiKhoan findByUsername(String username);
    Optional<TaiKhoan> findByMaTaiKhoan(Integer maTaiKhoan);
}
