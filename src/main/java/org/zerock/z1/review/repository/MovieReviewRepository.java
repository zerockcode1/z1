package org.zerock.z1.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.zerock.z1.movie.entity.Movie;
import org.zerock.z1.review.entity.MovieReview;

import org.zerock.z1.review.repository.search.ReviewSearch;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long>, ReviewSearch {


    Page<MovieReview> findMovieReviewByMovie(Movie movie, Pageable pageable);

}
