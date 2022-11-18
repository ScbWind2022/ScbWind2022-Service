package com.example.userservice.service.impl;

import com.example.userservice.dto.CheckDto;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Check;
import com.example.userservice.model.User;
import com.example.userservice.repository.CheckRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.CheckService;
import com.example.userservice.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final UserRepository userRepository;
    private final DtoUtils dtoUtils;
    @Override
    public void createCheckWithUser(Long user_id) {
        final Check check = Check.builder()
                .checkToken(UUID.randomUUID().toString()).build();
        checkRepository.save(check);
        checkRepository.updateUserAndCheck(user_id,check.getId());
    }

    @Override
    public CheckDto openNewCheckWithUser(String userEmail) {
        final User user = userRepository.getUserByEmail(userEmail);
        if(user == null){
            throw new UserNotFoundException();
        }
        final Check check = Check.builder()
                .checkToken(UUID.randomUUID().toString()).build();
        check.setUser(user);
        checkRepository.save(check);
        return dtoUtils.checkToCheckDto(check);
    }
}
