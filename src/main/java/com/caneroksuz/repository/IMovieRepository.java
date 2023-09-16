package com.caneroksuz.repository;

import com.caneroksuz.repository.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
public interface IMovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findAllByPremiredBefore(LocalDate date);
}
