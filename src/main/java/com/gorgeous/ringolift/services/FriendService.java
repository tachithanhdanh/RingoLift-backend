package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.responses.FriendResponse;

import java.util.List;

public interface FriendService {
    FriendResponse sendFriendRequest(Long senderId, Long receiverId) throws DataNotFoundException, DuplicateDataException;
    FriendResponse updateFriendStatus(Long friendId, String statusType) throws DataNotFoundException;
    List<FriendResponse> getFriendsByUserId(Long userId) throws DataNotFoundException;
    void deleteFriend(Long friendId) throws DataNotFoundException;
}
