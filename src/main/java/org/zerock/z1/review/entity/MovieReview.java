package org.zerock.z1.review.entity;

import lombok.*;
import org.zerock.z1.common.entity.BaseEntity;
import org.zerock.z1.movie.entity.Movie;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "movie")
public class MovieReview{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @Column(nullable = false)
    private String reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Column(nullable = false)
    private String title;

    private String reviewText;



    private int score;

    @Builder.Default
    private LocalDateTime reviewDate = LocalDateTime.now();
}
