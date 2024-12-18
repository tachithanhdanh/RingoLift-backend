package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.responses.UserGenderResponse;
import java.util.List;

public interface UserGenderService {
    List<UserGenderResponse> getAllUserGenders();

    UserGenderResponse getUserGenderById(Long id) throws DataNotFoundException;
}
