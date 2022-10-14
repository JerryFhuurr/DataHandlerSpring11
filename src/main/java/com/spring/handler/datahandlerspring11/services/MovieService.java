package com.spring.handler.datahandlerspring11.services;

import com.spring.handler.datahandlerspring11.model.BangumiMovie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    String addMovies(List<BangumiMovie> movies);

    String addSingleMovie(BangumiMovie movie);

    String removeMovies(List<String> ids);

    String removeSingleMovie(String id);

    String updateMovie(BangumiMovie movie);

    BangumiMovie getSingleMovie(String id);

    List<BangumiMovie> getMovies();


}
