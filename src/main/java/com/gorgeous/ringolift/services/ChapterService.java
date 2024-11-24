package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.ChapterRequest;
import com.gorgeous.ringolift.responses.ChapterResponse;
import java.util.List;

public interface ChapterService {
    public ChapterResponse createChapter(ChapterRequest chapterRequest);
    public ChapterResponse getChapterById(Long id) throws DataNotFoundException;
    public List<ChapterResponse> getAllChapters();
    public ChapterResponse updateChapter(Long id, ChapterRequest chapterRequest)
            throws DataNotFoundException;
    public void deleteChapter(Long id) throws DataNotFoundException;
}
