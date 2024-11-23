package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.models.Chapter;
import com.gorgeous.ringolift.models.Lesson;
import com.gorgeous.ringolift.models.LessonQuestion;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.repositories.ChapterRepository;
import com.gorgeous.ringolift.repositories.LessonQuestionRepository;
import com.gorgeous.ringolift.repositories.LessonRepository;
import com.gorgeous.ringolift.repositories.QuestionRepository;
import com.gorgeous.ringolift.requests.LessonRequest;
import com.gorgeous.ringolift.responses.LessonQuestionResponse;
import com.gorgeous.ringolift.responses.LessonResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ChapterRepository chapterRepository;
    private final LessonQuestionRepository lessonQuestionRepository;
    private final QuestionRepository questionRepository;


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
    public void deleteLesson(Long lessonId) throws DataNotFoundException {
        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find lesson with id " + lessonId));
        lessonRepository.deleteById(lessonId);
    }

    @Override
    public LessonQuestionResponse addQuestionToLesson(Long lessonId, Long questionId)
            throws DataNotFoundException, DuplicateDataException {
        // If lesson question already exists, throw exception
        if (lessonQuestionRepository.findByLessonIdAndQuestionId(lessonId, questionId).isPresent()) {
            throw new DuplicateDataException(
                    "Question with id " + questionId + " already exists in lesson with id "
                            + lessonId);
        }

        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find lesson with id " + lessonId));

        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question with id " + questionId));

        // Add question to lesson
        LessonQuestion newLessonQuestion = LessonQuestion.builder()
                .lesson(existingLesson)
                .question(existingQuestion)
                .build();
        return LessonQuestionResponse.fromLessonQuestion(
                lessonQuestionRepository.save(newLessonQuestion));
    }

    @Override
    public LessonQuestionResponse getLessonQuestionByLessonIdAndQuestionId(Long lessonId,
            Long questionId) throws DataNotFoundException {
        return lessonQuestionRepository.findByLessonIdAndQuestionId(lessonId, questionId)
                .map(LessonQuestionResponse::fromLessonQuestion)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find lesson question with lesson id " + lessonId + " and question id "
                                + questionId));
    }

//    @Override
//    public LessonQuestionResponse getLessonQuestionById(Long id) throws DataNotFoundException {
//        return lessonQuestionRepository.findById(id)
//                .map(LessonQuestionResponse::fromLessonQuestion)
//                .orElseThrow(
//                        () -> new DataNotFoundException(
//                                "Cannot find lesson question with id " + id));
//    }

    @Override
    public void removeQuestionFromLesson(Long lessonId, Long questionId)
            throws DataNotFoundException {
        LessonQuestion existingLessonQuestion = lessonQuestionRepository
                .findByLessonIdAndQuestionId(lessonId, questionId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find lesson question with lesson id " + lessonId + " and question id "
                                + questionId));
    }

    @Override
    @Transactional
    public void removeAllQuestionsFromLesson(Long lessonId) {
        lessonQuestionRepository.deleteByLessonId(lessonId);
    }

    @Override
    public List<LessonQuestionResponse> getQuestionsByLessonId(Long lessonId) {
        return LessonQuestionResponse.fromLessonQuestionList(
                lessonQuestionRepository.findByLessonId(lessonId));
    }
}
