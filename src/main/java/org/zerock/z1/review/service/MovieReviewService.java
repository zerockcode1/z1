package org.zerock.z1.review.service;

import org.zerock.z1.movie.entity.Movie;
import org.zerock.z1.review.dto.MovieReviewDTO;
import org.zerock.z1.review.dto.PageDTO;
import org.zerock.z1.review.dto.PageResponseDTO;
import org.zerock.z1.review.entity.MovieReview;

import java.util.List;

public interface MovieReviewService {


    PageResponseDTO getMovieReviews(Long mno, PageDTO pageDTO);

    Long register(MovieReviewDTO movieReviewDTO);


    default MovieReview dtoToEntity(MovieReviewDTO dto){

        Movie movie = Movie.builder().mno(dto.getMno()).build();

        MovieReview movieReview = MovieReview.builder()
                .rno(dto.getRno())
                .reviewer(dto.getReviewer())
                .reviewText(dto.getReviewText())
                .score(dto.getScore())
                .title(dto.getTitle())
                .build();
        return movieReview;
    }

    default MovieReviewDTO entityTODto(MovieReview movieReview){

        MovieReviewDTO dto = MovieReviewDTO.builder()
                .rno(movieReview.getRno())
                .mno(movieReview.getMovie().getMno())
                .reviewer(movieReview.getReviewer())
                .reviewText(movieReview.getReviewText())
                .reviewDate(movieReview.getReviewDate())
                .score(movieReview.getScore())
                .title(movieReview.getTitle())
                .build();


        return dto;
    }

}
