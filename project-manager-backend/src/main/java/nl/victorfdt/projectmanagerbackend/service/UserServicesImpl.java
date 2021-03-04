package nl.victorfdt.projectmanagerbackend.service;

import nl.victorfdt.projectmanagerbackend.data.entity.User;
import nl.victorfdt.projectmanagerbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServicesImpl implements UserService, UserDetailsService {

    UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUserName(String userName) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("The username '%s' is not found.", userName));
        }

        return user;
    }
}
