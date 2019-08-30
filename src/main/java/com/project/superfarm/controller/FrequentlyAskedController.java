package com.project.superfarm.controller;


import com.project.superfarm.entity.board.FrequentlyAskedQuestionBoard;
import com.project.superfarm.model.ResultItems;
import com.project.superfarm.service.FrequentlyAskedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faqboard")
public class FrequentlyAskedController {

    @Autowired
    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;

    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultItems<FrequentlyAskedQuestionBoard> loadAllFrequentlyAskedQuestion(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "7", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<FrequentlyAskedQuestionBoard> frequentlyAskedQuestionBoards =
                frequentlyAskedQuestionService.loadAllFrequentlyAskedQuestionBoard(pageable);
        System.out.println(frequentlyAskedQuestionBoards.toString());
        return new ResultItems<FrequentlyAskedQuestionBoard>(frequentlyAskedQuestionBoards.getContent(), page, size, frequentlyAskedQuestionBoards.getTotalElements(),
                frequentlyAskedQuestionBoards.getTotalPages(), frequentlyAskedQuestionBoards.hasNext());
    }

}