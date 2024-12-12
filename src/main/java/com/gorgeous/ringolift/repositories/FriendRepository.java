package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Friend;
import com.gorgeous.ringolift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findBySender(User sender);
    List<Friend> findByReceiver(User receiver);
    Optional<Friend> findBySenderAndReceiver(User sender, User receiver);
}
