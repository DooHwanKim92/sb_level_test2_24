package com.board.basic.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void create(String username, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
    }

    public SiteUser findByusername(String username) {
        Optional<SiteUser> user = this.userRepository.findByusername(username);
        if(user.isEmpty()) {
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
        return user.get();
    }
}
