package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.models.Word;
import com.gorgeous.ringolift.models.WordProgress;
import com.gorgeous.ringolift.models.WordStatus;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.repositories.WordProgressRepository;
import com.gorgeous.ringolift.repositories.WordRepository;
import com.gorgeous.ringolift.repositories.WordStatusRepository;
import com.gorgeous.ringolift.requests.WordProgressRequest;
import com.gorgeous.ringolift.responses.WordProgressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordProgressServiceImpl implements WordProgressService {

    private final WordProgressRepository wordProgressRepository;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final WordStatusRepository wordStatusRepository;

    @Override
    public WordProgressResponse createWordProgress(WordProgressRequest wordProgressRequest) throws DataNotFoundException {
        // Get user
        User user = userRepository.findById(wordProgressRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // Get word
        Word word = wordRepository.findById(wordProgressRequest.getWordId())
                .orElseThrow(() -> new DataNotFoundException("Word not found"));

        // Get word status
        WordStatus wordStatus = wordStatusRepository.findById(wordProgressRequest.getStatusId())
                .orElseThrow(() -> new DataNotFoundException("Word status not found"));

        // Create word progress
        WordProgress wordProgress = WordProgress.builder()
                .user(user)
                .word(word)
                .status(wordStatus)
                .note(wordProgressRequest.getNote())
                .build();

        return WordProgressResponse.fromWordProgress(wordProgressRepository.save(wordProgress));
    }

    @Override
    public WordProgressResponse getWordProgressById(Long wordProgressId) throws DataNotFoundException {
        return wordProgressRepository.findById(wordProgressId)
                .map(WordProgressResponse::fromWordProgress)
                .orElseThrow(() -> new DataNotFoundException("Word not found"));
    }

    @Override
    public List<WordProgressResponse> getAllWordProgress() {
        return wordProgressRepository.findAll().stream().map(WordProgressResponse::fromWordProgress).toList();
    }

    @Override
    public WordProgressResponse updateWordProgress(Long wordProgressId, WordProgressRequest wordProgressRequest) throws DataNotFoundException {
        WordProgress wordProgress = wordProgressRepository.findById(wordProgressId)
                .orElseThrow(() -> new DataNotFoundException("Word not found"));

        User user = userRepository.findById(wordProgressRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        Word word = wordRepository.findById(wordProgressRequest.getWordId())
                .orElseThrow(() -> new DataNotFoundException("Word not found"));

        WordStatus wordStatus = wordStatusRepository.findById(wordProgressRequest.getStatusId())
                .orElseThrow(() -> new DataNotFoundException("Word status not found"));

        wordProgress.setUser(user);
        wordProgress.setWord(word);
        wordProgress.setStatus(wordStatus);
        wordProgress.setNote(wordProgressRequest.getNote());

        return WordProgressResponse.fromWordProgress(wordProgressRepository.save(wordProgress));
    }

    @Override
    public void deleteWordProgress(Long wordProgressId) throws DataNotFoundException {
        if (!wordProgressRepository.existsById(wordProgressId)) {
            throw new DataNotFoundException("Word not found");
        }
        wordProgressRepository.deleteById(wordProgressId);
    }
}
