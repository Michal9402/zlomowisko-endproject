package endproject.service;



import endproject.model.MyUserDetails;
import endproject.model.User;
import endproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserService implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userAppOptional = userRepository.getUserByUsername(s);
        // return new CustomUserDetails(userAppOptional.get()); tak jest podstawowo, ale praktykuje sie inaczej

        return userAppOptional
                .map(userApp -> new MyUserDetails(userApp))
                .orElseThrow(() ->new UsernameNotFoundException("User not found"));
    }
}

