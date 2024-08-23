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

    public User findOneById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("사용자가 존재하지 않음")
        );
    }

    public Optional<User> findOneBySocialId(Long social_id){
        return userRepository.findBySocialId(social_id);
    };

    public User save(UserInfo userInfo) {
        User user = User.from(userInfo);
        userRepository.save(user);
        return user;
    }

    public User findBySocialId(Long social_id) {
        return userRepository.findBySocialId(social_id).orElseThrow(
                ()-> new RuntimeException("사용자가 존재 하지 않음")
        );
    }
}
