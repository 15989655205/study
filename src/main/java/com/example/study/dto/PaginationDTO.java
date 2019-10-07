package com.example.study.dto;

import java.util.List;

public class PaginationDTO {
    private List<QuestionDTO> list;
    private  boolean showPrevious;
    private  boolean showFirstPage;
    private  boolean showNext;
    private  boolean showEndPage;

    private Integer page;

    private List<Integer> pages;
}
