package org.zerock.z1.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.z1.movie.dto.MovieDTO;
import org.zerock.z1.movie.service.MovieService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/rank")
    public ResponseEntity<List<MovieDTO>> getAll(){

        log.info("get...................all..............movies");

        return ResponseEntity.ok(movieService.getAllMovies());

    }

}
