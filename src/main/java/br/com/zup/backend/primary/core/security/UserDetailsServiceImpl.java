package br.com.zup.backend.primary.core.security;

import br.com.zup.backend.primary.domain.User;
import br.com.zup.backend.primary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String tokenDecode) throws UsernameNotFoundException {

        User resource = repository.findByEmail(tokenDecode);

        if (resource == null) {
            throw  new UsernameNotFoundException(resource.getEmail());
        }

        return new UserSS(resource.getId(), resource.getEmail(), resource.getPassword(), resource.getRole());

    }
}

