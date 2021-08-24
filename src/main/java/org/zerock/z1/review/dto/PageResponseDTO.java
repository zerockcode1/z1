package org.zerock.z1.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO {

    private int page;

    private int size;

    private long totalCount;

    private List<MovieReviewDTO> reviewDTOList;


}
