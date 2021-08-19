package org.zerock.z1.review.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.z1.movie.entity.QMovie;
import org.zerock.z1.review.entity.MovieReview;

import java.util.Arrays;

@Log4j2
public class ReviewSearchImpl extends QuerydslRepositorySupport implements ReviewSearch {

    public ReviewSearchImpl() {
        super(MovieReview.class);
    }

    @Override
    public MovieReview search() {

        log.info("--------------------------------------");
        log.info("search.............................");


        return null;
    }
}
