package org.zerock.z1.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.z1.movie.dto.MovieDTO;
import org.zerock.z1.movie.entity.Movie;
import org.zerock.z1.movie.service.MovieService;
import org.zerock.z1.review.dto.MovieReviewDTO;
import org.zerock.z1.review.dto.PageDTO;
import org.zerock.z1.review.dto.PageResponseDTO;
import org.zerock.z1.review.entity.MovieReview;
import org.zerock.z1.review.repository.MovieReviewRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class MovieReviewServiceImpl implements MovieReviewService {

    private final MovieReviewRepository reviewRepository;


    @Override
    public PageResponseDTO getMovieReviews(Long mno, PageDTO pageDTO) {

        Pageable pageable = PageRequest.of(pageDTO.getPage() -1, pageDTO.getSize());
        Movie movie = Movie.builder().mno(mno).build();

        Page<MovieReview> result = reviewRepository.findMovieReviewByMovie(movie, pageable);

        List<MovieReviewDTO> dtoList = result.getContent().stream().map(entity -> entityTODto(entity)).collect(Collectors.toList());

        PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                .page(result.getNumber())
                .size(result.getSize())
                .totalCount(result.getTotalElements())
                .reviewDTOList(dtoList)
                .build();

        return pageResponseDTO;
    }

    @Override
    public Long register(MovieReviewDTO movieReviewDTO) {

        MovieReview review = dtoToEntity(movieReviewDTO);

        reviewRepository.save(review);

        return review.getRno();

    }
}
