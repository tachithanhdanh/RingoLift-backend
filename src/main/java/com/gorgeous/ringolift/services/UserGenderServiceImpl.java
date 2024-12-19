package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.UserGender;
import com.gorgeous.ringolift.repositories.UserGenderRepository;
import com.gorgeous.ringolift.responses.UserGenderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGenderServiceImpl implements
        UserGenderService {

    private final UserGenderRepository userGenderRepository;

    @Override
    public List<UserGenderResponse> getAllUserGenders() {
        List<UserGender> userGenders = userGenderRepository.findAll();
        return userGenders.stream()
                .map(UserGenderResponse::fromUserGender)
                .toList();
    }

    @Override
    public UserGenderResponse getUserGenderById(Long id) throws DataNotFoundException {
        return userGenderRepository.findById(id)
                .map(UserGenderResponse::fromUserGender)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user gender with id: " + id));
    }
}
