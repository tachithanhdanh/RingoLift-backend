package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Chapter;
import com.gorgeous.ringolift.repositories.ChapterRepository;
import com.gorgeous.ringolift.requests.ChapterRequest;
import com.gorgeous.ringolift.responses.ChapterResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;

    @Override
    public ChapterResponse createChapter(ChapterRequest chapterRequest) {
        // Create a new chapter
        Chapter chapter = Chapter.builder()
                .name(chapterRequest.getName())
                .coverImage(chapterRequest.getCoverImage())
                .description(chapterRequest.getDescription())
                .build();
        // Return the chapter response
        return ChapterResponse.fromChapter(chapterRepository.save(chapter));
    }

    @Override
    public ChapterResponse getChapterById(Long id) throws DataNotFoundException {
        return ChapterResponse.fromChapter(chapterRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find chapter with id " + id)));
    }

    @Override
    public List<ChapterResponse> getAllChapters() {
        return chapterRepository
                .findAll()
                .stream()
                .map(ChapterResponse::fromChapter).toList();
    }

    @Override
    public ChapterResponse updateChapter(Long id, ChapterRequest chapterRequest)
            throws DataNotFoundException {
        Chapter existingChapter = chapterRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find chapter with id " + id));
        existingChapter.setName(chapterRequest.getName());
        existingChapter.setCoverImage(chapterRequest.getCoverImage());
        existingChapter.setDescription(chapterRequest.getDescription());
        return ChapterResponse.fromChapter(chapterRepository.save(existingChapter));
    }

    @Override
    public void deleteChapter(Long id) throws DataNotFoundException {
        // In the meantime, we will just delete the chapter
        // When we have added other table that reference to this chapter, we will need to delete those references first
        // Or we can implement soft delete
        Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find chapter with id " + id));
        chapterRepository.deleteById(id);
    }
}
