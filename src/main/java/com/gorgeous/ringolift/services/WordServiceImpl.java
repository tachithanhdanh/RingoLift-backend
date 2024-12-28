package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.PartOfSpeech;
import com.gorgeous.ringolift.models.Topic;
import com.gorgeous.ringolift.models.Word;
import com.gorgeous.ringolift.repositories.PartOfSpeechRepository;
import com.gorgeous.ringolift.repositories.TopicRepository;
import com.gorgeous.ringolift.repositories.WordRepository;
import com.gorgeous.ringolift.requests.WordRequest;
import com.gorgeous.ringolift.responses.WordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final PartOfSpeechRepository partOfSpeechRepository;
    private final TopicRepository topicRepository;

    @Override
    public WordResponse createWord(WordRequest wordRequest) throws DataNotFoundException {
        PartOfSpeech partOfSpeech = null;
        Topic topic = null;

        if (wordRequest.getPartOfSpeechId() != null) {
            partOfSpeech = partOfSpeechRepository.findById(wordRequest.getPartOfSpeechId())
                    .orElseThrow(() -> new DataNotFoundException("PartOfSpeech not found"));
        }

        if (wordRequest.getTopicId() != null) {
            topic = topicRepository.findById(wordRequest.getTopicId())
                    .orElseThrow(() -> new DataNotFoundException("Topic not found"));
        }

        Word word = Word.builder()
                .word(wordRequest.getWord())
                .meaning(wordRequest.getMeaning())
                .pronunciation(wordRequest.getPronunciation())
                .audioUrl(wordRequest.getAudioUrl())
                .exampleSentence(wordRequest.getExampleSentence())
                .partOfSpeech(partOfSpeech)
                .topic(topic)
                .build();

        word.setCreatedAt(LocalDateTime.now());
        word.setUpdatedAt(LocalDateTime.now());

        word = wordRepository.save(word);
        return WordResponse.fromWord(word);
    }

    @Override
    public WordResponse getWordById(Long wordId) throws DataNotFoundException {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new DataNotFoundException("Word not found"));
        return WordResponse.fromWord(word);
    }

    @Override
    public List<WordResponse> getAllWords() {
        return wordRepository.findAll().stream().map(WordResponse::fromWord).toList();
    }

    @Override
    public WordResponse updateWord(Long wordId, WordRequest wordRequest) throws DataNotFoundException {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new DataNotFoundException("Word not found"));

        PartOfSpeech partOfSpeech = null;
        Topic topic = null;

        if (wordRequest.getPartOfSpeechId() != null) {
            partOfSpeech = partOfSpeechRepository.findById(wordRequest.getPartOfSpeechId())
                    .orElseThrow(() -> new DataNotFoundException("PartOfSpeech not found"));
        }

        if (wordRequest.getTopicId() != null) {
            topic = topicRepository.findById(wordRequest.getTopicId())
                    .orElseThrow(() -> new DataNotFoundException("Topic not found"));
        }

        word.setWord(wordRequest.getWord());
        word.setMeaning(wordRequest.getMeaning());
        word.setPronunciation(wordRequest.getPronunciation());
        word.setAudioUrl(wordRequest.getAudioUrl());
        word.setExampleSentence(wordRequest.getExampleSentence());
        word.setPartOfSpeech(partOfSpeech);
        word.setTopic(topic);
        word.setUpdatedAt(LocalDateTime.now());

        word = wordRepository.save(word);
        return WordResponse.fromWord(word);
    }

    @Override
    public void deleteWord(Long wordId) throws DataNotFoundException {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new DataNotFoundException("Word not found"));
        wordRepository.delete(word);
    }

    @Override
    public List<WordResponse> getWordsByTopicId(Long topicId) throws DataNotFoundException {
        // Kiểm tra topicId có tồn tại hay không
        topicRepository.findById(topicId).orElseThrow(() -> new DataNotFoundException("Topic not found"));

        List<Word> words = wordRepository.findByTopicId(topicId);
        return words.stream().map(WordResponse::fromWord).toList();
    }
}
