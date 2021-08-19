package org.zerock.z1.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.z1.movie.entity.Movie;

public interface MovieRepository  extends JpaRepository<Movie, Long> {

}
