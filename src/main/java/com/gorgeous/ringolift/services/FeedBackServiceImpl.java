package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.FeedBack;
import com.gorgeous.ringolift.models.Lesson;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.repositories.FeedBackRepository;
import com.gorgeous.ringolift.repositories.LessonRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.FeedBackRequest;
import com.gorgeous.ringolift.responses.FeedBackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public FeedBackResponse createFeedback(FeedBackRequest request) throws DataNotFoundException {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with ID: " + request.getUserId()));
        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new DataNotFoundException("Lesson not found with ID: " + request.getLessonId()));

        FeedBack feedback = new FeedBack();
        feedback.setUser(user);
        feedback.setLesson(lesson);
        feedback.setStars(request.getStars());
        feedback.setComment(request.getComment());

        FeedBack savedFeedback = feedbackRepository.save(feedback);

        return mapToResponse(savedFeedback);
    }

    @Override
    public FeedBackResponse getFeedback(Long id) throws DataNotFoundException {
        FeedBack feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Feedback not found with ID: " + id));

        return mapToResponse(feedback);
    }

    @Override
    public FeedBackResponse updateFeedback(Long feedbackId, FeedBackRequest request) throws DataNotFoundException {
        FeedBack feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new DataNotFoundException("Feedback not found with ID: " + feedbackId));

        // Ensure the user is authorized to update this feedback
        if (!feedback.getUser().getId().equals(request.getUserId())) {
            throw new IllegalArgumentException("You are not authorized to update this feedback.");
        }

        // Ensure the lesson matches
        if (!feedback.getLesson().getId().equals(request.getLessonId())) {
            throw new IllegalArgumentException("Feedback does not belong to the specified lesson.");
        }

        feedback.setStars(request.getStars());
        feedback.setComment(request.getComment());

        FeedBack updatedFeedback = feedbackRepository.save(feedback);

        return mapToResponse(updatedFeedback);
    }


    @Override
    public void deleteFeedback(Long feedbackId, Long userId, Long lessonId) throws DataNotFoundException {
        FeedBack feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new DataNotFoundException("Feedback not found with ID: " + feedbackId));

        // Ensure the user is authorized to delete this feedback
        if (!feedback.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You are not authorized to delete this feedback.");
        }

        // Ensure the lesson matches
        if (!feedback.getLesson().getId().equals(lessonId)) {
            throw new IllegalArgumentException("Feedback does not belong to the specified lesson.");
        }

        feedbackRepository.delete(feedback);
    }

    @Override
    public List<FeedBackResponse> getFeedbacksByUserAndLesson(Long userId, Long lessonId) throws DataNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found with ID: " + userId));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException("Lesson not found with ID: " + lessonId));

        List<FeedBack> feedbacks = feedbackRepository.findByUserIdAndLessonId(userId, lessonId);
        return feedbacks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private FeedBackResponse mapToResponse(FeedBack feedback) {
        return FeedBackResponse.fromFeedBack(feedback);
    }
}
