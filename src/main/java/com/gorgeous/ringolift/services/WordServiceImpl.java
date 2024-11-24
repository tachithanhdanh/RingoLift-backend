package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.PartOfSpeech;
import com.gorgeous.ringolift.models.Word;
import com.gorgeous.ringolift.repositories.PartOfSpeechRepository;
import com.gorgeous.ringolift.repositories.WordRepository;
import com.gorgeous.ringolift.requests.WordRequest;
import com.gorgeous.ringolift.responses.WordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final PartOfSpeechRepository partOfSpeechRepository;

    @Override
    public WordResponse createWord(WordRequest wordRequest) throws DataNotFoundException {
        // Get part of speech
        PartOfSpeech partOfSpeech = null;
        if (wordRequest.getPartOfSpeechId() != null) {
            partOfSpeech = partOfSpeechRepository.findById(wordRequest.getPartOfSpeechId())
                    .orElseThrow(() -> new DataNotFoundException("PartOfSpeech not found"));
        }

        // Create word
        Word word = Word.builder()
                .word(wordRequest.getWord())
                .meaning(wordRequest.getMeaning())
                .topic(wordRequest.getTopic())
                .partOfSpeech(partOfSpeech)
                .pronunciation(wordRequest.getPronunciation())
                .audioUrl(wordRequest.getAudioUrl())
                .exampleSentence(wordRequest.getExampleSentence())
                .build();

        return WordResponse.fromWord(wordRepository.save(word));
    }

    @Override
    public WordResponse getWordById(Long wordId) throws DataNotFoundException {
        return wordRepository.findById(wordId)
                .map(WordResponse::fromWord)
                .orElseThrow(() -> new DataNotFoundException("Word not found"));
    }

    @Override
    public List<WordResponse> getAllWords() {
        return wordRepository.findAll().stream().map(WordResponse::fromWord).toList();
    }

    @Override
    public WordResponse updateWord(Long wordId, WordRequest wordRequest) throws DataNotFoundException {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new DataNotFoundException("Word not found"));
        PartOfSpeech partOfSpeech = partOfSpeechRepository.findById(wordRequest.getPartOfSpeechId())
                .orElseThrow(() -> new DataNotFoundException("PartOfSpeech not found"));

        word.setWord(wordRequest.getWord());
        word.setMeaning(wordRequest.getMeaning());
        word.setTopic(wordRequest.getTopic());
        word.setPartOfSpeech(partOfSpeech);
        word.setPronunciation(wordRequest.getPronunciation());
        word.setAudioUrl(wordRequest.getAudioUrl());
        word.setExampleSentence(wordRequest.getExampleSentence());

        return WordResponse.fromWord(wordRepository.save(word));
    }

    @Override
    @Transactional
    public void deleteWord(Long wordId) throws DataNotFoundException {
        if (!wordRepository.existsById(wordId)) {
            throw new DataNotFoundException("Word not found");
        }
        wordRepository.deleteById(wordId);
    }
}
