package org.zerock.z1.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieReviewDTO {

    private Long rno;

    private String reviewer, title, reviewText;

    private LocalDateTime reviewDate;

    private Long mno;

    private int score;

}
