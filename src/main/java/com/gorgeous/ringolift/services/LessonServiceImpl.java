package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Chapter;
import com.gorgeous.ringolift.models.Lesson;
import com.gorgeous.ringolift.repositories.ChapterRepository;
import com.gorgeous.ringolift.repositories.LessonRepository;
import com.gorgeous.ringolift.requests.LessonRequest;
import com.gorgeous.ringolift.responses.LessonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ChapterRepository chapterRepository;


    @Override
    public LessonResponse createLesson(LessonRequest lessonRequest) throws DataNotFoundException {
        // Check if chapter exists
        Chapter chapter = chapterRepository.findById(lessonRequest.getChapterId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find chapter with id " + lessonRequest.getChapterId()));

        // Create lesson
        Lesson newLesson = Lesson.builder()
                .title(lessonRequest.getTitle())
                .description(lessonRequest.getDescription())
                .chapter(chapter)
                .build();

        Lesson savedLesson = lessonRepository.save(newLesson);
        return LessonResponse.fromLesson(savedLesson);
    }

    @Override
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest)
            throws DataNotFoundException {
        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find lesson with id " + lessonId));

        // Check if chapter exists
        Chapter chapter = chapterRepository.findById(lessonRequest.getChapterId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find chapter with id " + lessonRequest.getChapterId()));

        existingLesson.setTitle(lessonRequest.getTitle());
        existingLesson.setDescription(lessonRequest.getDescription());
        existingLesson.setChapter(chapter);

        return LessonResponse.fromLesson(lessonRepository.save(existingLesson));
    }

    @Override
    public LessonResponse getLessonById(Long lessonId) throws DataNotFoundException {
        return lessonRepository.findById(lessonId)
                .map(LessonResponse::fromLesson)
                .orElseThrow(
                        () -> new DataNotFoundException("Cannot find lesson with id " + lessonId));
    }

    @Override
    public List<LessonResponse> getLessonsByChapterId(Long chapterId) {
        return lessonRepository
                .findByChapterId(chapterId)
                .stream()
                .map(LessonResponse::fromLesson)
                .toList();
    }

    @Override
    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
