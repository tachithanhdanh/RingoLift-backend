package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.models.Friend;
import com.gorgeous.ringolift.models.FriendStatus;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.repositories.FriendRepository;
import com.gorgeous.ringolift.repositories.FriendStatusRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.responses.FriendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    private final FriendStatusRepository friendStatusRepository;
    private final UserRepository userRepository;

    @Override
    public FriendResponse sendFriendRequest(Long senderId, Long receiverId) throws DataNotFoundException, DuplicateDataException {
        if (senderId.equals(receiverId)) {
            throw new DuplicateDataException("Cannot send friend request to yourself.");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new DataNotFoundException("Sender not found with id " + senderId));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new DataNotFoundException("Receiver not found with id " + receiverId));

        // Kiểm tra nếu đã có yêu cầu kết bạn trước đó
        if (friendRepository.findBySenderAndReceiver(sender, receiver).isPresent()) {
            throw new DuplicateDataException("Friend request already sent.");
        }

        FriendStatus pendingStatus = friendStatusRepository.findByStatusType("PENDING")
                .orElseThrow(() -> new DataNotFoundException("PENDING status not found."));

        Friend friend = Friend.builder()
                .sender(sender)
                .receiver(receiver)
                .status(pendingStatus)
                .build();

        Friend savedFriend = friendRepository.save(friend);
        return FriendResponse.fromFriend(savedFriend);
    }

    @Override
    public FriendResponse updateFriendStatus(Long friendId, String statusType) throws DataNotFoundException {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new DataNotFoundException("Friend request not found with id " + friendId));

        FriendStatus status = friendStatusRepository.findByStatusType(statusType)
                .orElseThrow(() -> new DataNotFoundException("Status type not found: " + statusType));

        friend.setStatus(status);
        Friend updatedFriend = friendRepository.save(friend);
        return FriendResponse.fromFriend(updatedFriend);
    }

    @Override
    public List<FriendResponse> getFriendsByUserId(Long userId) throws DataNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found with id " + userId));

        List<Friend> sentFriends = friendRepository.findBySender(user);
        List<Friend> receivedFriends = friendRepository.findByReceiver(user);

        List<Friend> allFriends = sentFriends.stream()
                .filter(friend -> "ACCEPTED".equals(friend.getStatus().getStatusType()))
                .collect(Collectors.toList());

        allFriends.addAll(receivedFriends.stream()
                .filter(friend -> "ACCEPTED".equals(friend.getStatus().getStatusType()))
                .collect(Collectors.toList()));

        return allFriends.stream().map(FriendResponse::fromFriend).collect(Collectors.toList());
    }

    @Override
    public void deleteFriend(Long friendId) throws DataNotFoundException {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new DataNotFoundException("Friend not found with id " + friendId));
        friendRepository.delete(friend);
    }
}
