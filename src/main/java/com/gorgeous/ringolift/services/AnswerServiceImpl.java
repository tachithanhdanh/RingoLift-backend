package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Answer;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.repositories.AnswerRepository;
import com.gorgeous.ringolift.repositories.QuestionRepository;
import com.gorgeous.ringolift.requests.AnswerRequest;
import com.gorgeous.ringolift.responses.AnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;

    private QuestionRepository questionRepository;

    @Override
    public AnswerResponse createAnswer(AnswerRequest request) throws DataNotFoundException {
        // Load Question from the database
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + request.getQuestionId()));

        // Create new Answer and set the fields
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(request.getContent());
        answer.setIsCorrect(request.getIsCorrect());

        // Save the answer and return the response
        Answer savedAnswer = answerRepository.save(answer);

        return mapToResponse(savedAnswer);
    }

    @Override
    public AnswerResponse updateAnswer(Long answerId, Long questionId, AnswerRequest request) throws DataNotFoundException {
        // Find the existing Answer by ID
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new DataNotFoundException("Answer not found with id: " + answerId));

        // Load the Question from the database based on the provided questionId
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + questionId));

        // Update the Answer fields
        answer.setQuestion(question);
        answer.setContent(request.getContent());
        answer.setIsCorrect(request.getIsCorrect());

        // Save the updated Answer
        Answer updatedAnswer = answerRepository.save(answer);

        return mapToResponse(updatedAnswer);
    }

    @Override
    public void deleteAnswer(Long questionId, Long answerId) throws DataNotFoundException {
        // Find the Question by ID
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + questionId));

        // Find the Answer by ID and ensure it is related to the given Question
        Answer answer = answerRepository.findByIdAndQuestionId(answerId, questionId)
                .orElseThrow(() -> new DataNotFoundException("Answer not found with id: " + answerId + " for Question ID: " + questionId));

        // Delete the Answer
        answerRepository.delete(answer);
    }


    @Override
    public List<AnswerResponse> getAnswersByQuestionId(Long questionId) {
        // Get Answers by Question ID
        List<Answer> answers = answerRepository.findByQuestionId(questionId);

        // Map each Answer to AnswerResponse and return
        return answers.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnswerResponse> getAnswersByQuestionIdAndStatus(Long questionId, boolean isCorrect) {
        // Get Answers by Question ID and Correctness Status
        List<Answer> answers = answerRepository.findByQuestionIdAndIsCorrect(questionId, isCorrect);

        // Map each Answer to AnswerResponse and return
        return answers.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Utility method to map Answer entity to AnswerResponse
    private AnswerResponse mapToResponse(Answer answer) {
        return AnswerResponse.fromAnswer(answer);
    }
}
