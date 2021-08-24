package org.zerock.z1.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.z1.review.dto.MovieReviewDTO;
import org.zerock.z1.review.dto.PageDTO;
import org.zerock.z1.review.dto.PageResponseDTO;
import org.zerock.z1.review.service.MovieReviewService;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/movies/reviews")
@RequiredArgsConstructor
public class MovieReviewController {

    private final MovieReviewService movieReviewService;

    @GetMapping("/{mno}")
    public PageResponseDTO getReviewsByMovie(@PathVariable("mno") Long mno, PageDTO pageDTO) {

        log.info("mno:" + mno +" " + pageDTO);

        return movieReviewService.getMovieReviews(mno,pageDTO);

    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody MovieReviewDTO movieReviewDTO) {

        Long rno = movieReviewService.register(movieReviewDTO);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("RNO", rno);

        return resultMap;

    }


}
