package org.zerock.z1.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.z1.movie.dto.MovieDTO;
import org.zerock.z1.movie.service.MovieService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;


    @PreAuthorize("permitAll()")
    @GetMapping("/rank")
    public ResponseEntity<List<MovieDTO>> getAll(Authentication authentication){

        log.info("get...................all..............movies:" +authentication);


        return ResponseEntity.ok(movieService.getAllMovies());

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/rankLevel")
    public ResponseEntity<List<MovieDTO>> getAll2(Authentication authentication){

        log.info("get...................all..............movies:" +authentication);

        log.info(authentication.getAuthorities());
        log.info(authentication.getPrincipal());

        log.info("=============================");

        return ResponseEntity.ok(movieService.getAllMovies());

    }

}
