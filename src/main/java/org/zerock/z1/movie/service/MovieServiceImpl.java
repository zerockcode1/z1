package org.zerock.z1.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.z1.movie.dto.MovieDTO;
import org.zerock.z1.movie.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDTO> getAllMovies() {

        return movieRepository.findAll().stream().map((movie) ->entityToDTO(movie)).collect(Collectors.toList());
    }
}
