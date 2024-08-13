package com.starforceps.starforceps.domain.user.application;

import com.starforceps.starforceps.domain.user.dao.UserRepository;
import com.starforceps.starforceps.domain.user.domain.User;
import com.starforceps.starforceps.domain.user.dto.UserInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUser(Long social_id){
        return userRepository.findBySocialId(social_id);
    };

    public User save(UserInfo userInfo) {
        User user = User.from(userInfo);
        userRepository.save(user);
        return user;
    }
}
