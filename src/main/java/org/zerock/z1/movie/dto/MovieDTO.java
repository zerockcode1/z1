package org.zerock.z1.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long mno;

    private String title;

    private double bookRate;

    private String poster;
}
