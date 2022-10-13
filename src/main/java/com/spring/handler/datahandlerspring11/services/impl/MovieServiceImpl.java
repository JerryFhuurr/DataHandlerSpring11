package com.spring.handler.datahandlerspring11.services.impl;

import com.spring.handler.datahandlerspring11.model.BangumiMovie;
import com.spring.handler.datahandlerspring11.services.MovieService;
import com.spring.handler.datahandlerspring11.sqlmapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieServices")
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper mapper;

    //TODO add a controller advisor in the project (try one in collect from company)
    @Override
    public String addMovies(List<BangumiMovie> movies) {
        return null;
    }

    @Override
    public String addSingleMovie(BangumiMovie movie) {
        return null;
    }

    @Override
    public String removeMovies(List<Integer> ids) {
        return null;
    }

    @Override
    public String removeSingleMovie(int id) {
        return null;
    }

    @Override
    public String updateMovie(BangumiMovie movie) {
        return null;
    }

    @Override
    public BangumiMovie getSingleMovie(int id) {
        return null;
    }

    @Override
    public List<BangumiMovie> getMovies() {
        return null;
    }
}
