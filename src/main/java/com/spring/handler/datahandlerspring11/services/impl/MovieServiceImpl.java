package com.spring.handler.datahandlerspring11.services.impl;

import com.spring.handler.datahandlerspring11.model.BangumiMovie;
import com.spring.handler.datahandlerspring11.services.MovieService;
import com.spring.handler.datahandlerspring11.sqlmapper.MovieMapper;
import com.spring.handler.datahandlerspring11.utils.exceptions.ReqExceptions;
import com.spring.handler.datahandlerspring11.utils.exceptions.common.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("movieServices")
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper mapper;

    @Override
    public String addMovies(List<BangumiMovie> movies) {
        String verifyResult = verifyListDup(movies);
        if (verifyResult.equals("null")) {
            mapper.addMovies(movies);
            return movies.size() + " movies added.";
        }
        return verifyResult;
    }

    private String verifyListDup(List<BangumiMovie> movies) {
        List<BangumiMovie> moviesGet = mapper.getMovies();
        String errorFront = "The movie id ";
        int errorCount = 0;
        for (var movie :
                movies) {
            for (var movieGet :
                    moviesGet) {
                if (movieGet == movieGet) {
                    errorFront += movie.getMovieId();
                    errorFront += ",";
                    errorCount++;
                }
            }
        }
        if (errorCount == 0) {
            errorFront = "null";
            return errorFront;
        } else {
            errorFront += "(is) are duplicated";
            throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_DUPLICATED, errorFront);
        }
    }

    @Override
    public String addSingleMovie(BangumiMovie movie) {
        BangumiMovie movieGet = mapper.getSingleMovie(movie.getMovieId());
        String result = "";
        result = (movieGet != null) ? "ERROR: The movie is already existed." : addMovie(movie);
        if (result.contains("ERROR")) {
            throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_DUPLICATED, "The movie is already existed");
        } else {
            return result;
        }
    }

    private String addMovie(BangumiMovie movie) {
        mapper.addSingleMovie(movie);
        return movie.getMovieId() + " is added";
    }

    @Override
    public String removeMovies(List<Integer> ids) {
        List<BangumiMovie> moviesGet = getMovies();
        List<Integer> matchedIds = new ArrayList<>(ids.size());
        List<Integer> notMatchedIds = new ArrayList<>(ids.size());
        String errorMsg = "The movie id ";
        String checkMsg = "The movie id ";
        for (var id :
                ids) {
            for (var movieGet :
                    moviesGet) {
                if (id == movieGet.getMovieId()) {
                    checkMsg += String.valueOf(id);
                    errorMsg += ',';
                    matchedIds.add(id);
                } else {
                    notMatchedIds.add(id);
                }
            }
        }
        if (matchedIds.size() == ids.size()) {
            checkMsg += " are(is) removed";
            mapper.removeMovies(matchedIds);
            return checkMsg;
        } else {
            for (var idNotMatched :
                    notMatchedIds) {
                errorMsg += idNotMatched;
                errorMsg += ',';
            }
            errorMsg += " are not existed";
            throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_REMOVE_OTHER, errorMsg);
        }
    }

    @Override
    public String removeSingleMovie(int id) {
        if (isMovieExisted(id)) {
            mapper.removeSingleMovie(id);
            return "Movie " + id + " removed";
        } else {
            throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_REMOVE_OTHER, "An error happened, open logs to see details");
        }
    }

    private boolean isMovieExisted(int id) {
        BangumiMovie movieGet;
        try {
            movieGet = getSingleMovie(id);
            return true;
        } catch (NullPointerException ex) {
            throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_NOT_FOUND, "The movie cannot found");
        }
    }

    @Override
    public String updateMovie(BangumiMovie movie) {
        return null;
    }

    @Override
    public BangumiMovie getSingleMovie(int id) {
        return mapper.getSingleMovie(id);
    }

    @Override
    public List<BangumiMovie> getMovies() {
        return mapper.getMovies();
    }
}
