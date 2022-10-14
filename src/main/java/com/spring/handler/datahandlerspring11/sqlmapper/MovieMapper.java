package com.spring.handler.datahandlerspring11.sqlmapper;

import com.spring.handler.datahandlerspring11.model.BangumiMovie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {
    void addMovies(List<BangumiMovie> movies);

    void addSingleMovie(BangumiMovie movie);

    void removeMovies(List<String> ids);

    void removeSingleMovie(String id);

    BangumiMovie getSingleMovie(String id);

    List<BangumiMovie> getMovies();

    void updateMovie(BangumiMovie movie);
}
