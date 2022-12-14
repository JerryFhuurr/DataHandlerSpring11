package com.spring.handler.datahandlerspring11.services.impl;

import com.spring.handler.datahandlerspring11.model.BangumiMovie;
import com.spring.handler.datahandlerspring11.services.MovieService;
import com.spring.handler.datahandlerspring11.services.validateGroup.MovieValidate;
import com.spring.handler.datahandlerspring11.sqlmapper.MovieMapper;
import com.spring.handler.datahandlerspring11.utils.exceptions.ReqExceptions;
import com.spring.handler.datahandlerspring11.utils.exceptions.common.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
        result = (movieGet != null) ? "ERROR: The movie is already existed." : "ADD:";
        if (result.contains("ERROR")) {
            throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_DUPLICATED, "The movie is already existed");
        } else {
            Date movieAddedDate = movie.getMovieAddDate();
            Date movieStartDate = movie.getMovieStartDate();
            LocalDate currentDate = LocalDate.now();
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = currentDate.atStartOfDay().atZone(zone).toInstant();
            Date da = Date.from(instant);
            if (movieAddedDate.before(da) || movieAddedDate.equals(da)) {
                if (movieStartDate.before(da) || movieStartDate.equals(da)) {
                    result += addMovie(movie);
                    return result;
                } else {
                    throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_START_DATE_INVALID, "The release date is invalid");
                }
            } else {
                throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_ADDED_DATE_INVALID, "The added date is invalid");
            }
        }
    }

    private String addMovie(BangumiMovie movie) {
        mapper.addSingleMovie(movie);
        return movie.getMovieId() + " is added";
    }

    @Override
    public String removeMovies(List<String> ids) {
        List<BangumiMovie> moviesGet = getMovies();
        List<String> matchedIds = new ArrayList<>(ids.size());
        List<String> notMatchedIds = new ArrayList<>(ids.size());
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
    public String removeSingleMovie(String id) {
        if (isMovieExisted(id)) {
            mapper.removeSingleMovie(id);
            return "Movie " + id + " removed";
        } else {
            throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_REMOVE_OTHER, "An error happened, open logs to see details");
        }
    }

    private boolean isMovieExisted(String id) {
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
        if (isMovieExisted(movie.getMovieId())) {
            mapper.updateMovie(movie);
        }
        return "Movie " + movie.getMovieId() + " is updated";
    }

    @Override
    public BangumiMovie getSingleMovie(String id) {
        return mapper.getSingleMovie(id);
    }

    @Override
    public List<BangumiMovie> getMovies() {
        return mapper.getMovies();
    }

}
