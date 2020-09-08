package com.lijiesong.minitwitter.version2.auth;

import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetUserDetailsServices implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public TweetUserDetailsServices(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        if(null==user){
            throw new UsernameNotFoundException("can not find user name: " + email);
        }
        //System.out.println(user.getFirstName());
        List<AuthGroup> authGroups = this.authGroupRepository.findByUserEmail(email);
        return new TweetUserPrincipal(user, authGroups);
    }
}
