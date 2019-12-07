package congvanservice.services;

import congvanservice.models.CustomUserDetails;
import congvanservice.models.TaiKhoan;
import congvanservice.repositories.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByUsername(username);
        if(taiKhoan == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(taiKhoan);
    }

    @Transactional
    public UserDetails loadUserById(Integer id) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByMaTaiKhoan(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id: " + id)
        );
        return new CustomUserDetails(taiKhoan);
    }
}
