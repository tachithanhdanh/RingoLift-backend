package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.requests.FriendRequest;
import com.gorgeous.ringolift.responses.FriendResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${api.prefix}/friends")
@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // Gửi yêu cầu kết bạn
    @PostMapping("/request")
    public ResponseEntity<ResponseObject> sendFriendRequest(
            @Valid @RequestBody FriendRequest friendRequest
    ) throws DataNotFoundException, DuplicateDataException {
        FriendResponse friendResponse = friendService.sendFriendRequest(friendRequest.getSenderId(), friendRequest.getReceiverId());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Friend request sent successfully")
                        .status(HttpStatus.CREATED)
                        .data(friendResponse)
                        .build()
        );
    }

    // Cập nhật trạng thái kết bạn
    @PutMapping("/status/{friendId}")
    public ResponseEntity<ResponseObject> updateFriendStatus(
            @PathVariable Long friendId,
            @RequestParam String statusType
    ) throws DataNotFoundException {
        FriendResponse friendResponse = friendService.updateFriendStatus(friendId, statusType);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Friend status updated successfully")
                        .status(HttpStatus.OK)
                        .data(friendResponse)
                        .build()
        );
    }

    // Lấy danh sách bạn bè của người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseObject> getFriendsByUserId(@PathVariable Long userId) throws DataNotFoundException {
        List<FriendResponse> friends = friendService.getFriendsByUserId(userId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get friends successfully")
                        .status(HttpStatus.OK)
                        .data(friends)
                        .build()
        );
    }

    // Xóa bạn bè
    @DeleteMapping("/{friendId}")
    public ResponseEntity<ResponseObject> deleteFriend(@PathVariable Long friendId) throws DataNotFoundException {
        friendService.deleteFriend(friendId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Friend deleted successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }
}
