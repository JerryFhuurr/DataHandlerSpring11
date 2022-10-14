package com.spring.handler.datahandlerspring11.controller;

import com.spring.handler.datahandlerspring11.model.BangumiMovie;
import com.spring.handler.datahandlerspring11.model.MovieParamList;
import com.spring.handler.datahandlerspring11.model.MovieParamSingle;
import com.spring.handler.datahandlerspring11.model.User;
import com.spring.handler.datahandlerspring11.services.MovieService;
import com.spring.handler.datahandlerspring11.services.validateGroup.MovieValidate;
import com.spring.handler.datahandlerspring11.services.validateGroup.UserValidate;
import com.spring.handler.datahandlerspring11.utils.exceptions.ReqExceptions;
import com.spring.handler.datahandlerspring11.utils.exceptions.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bangumi/controller/movie/")
public class BangumiMovieController {
    @Autowired
    MovieService movieServices;

    @PostMapping("add/more")
    String addMovies(@RequestBody MovieParamList paramList) {
        List<BangumiMovie> movies = paramList.getMovies();
        User currentUser = paramList.getCurrentUser();
        for (var movie :
                movies) {
            movie.setAddedUser(currentUser);
        }
        return movieServices.addMovies(movies);
    }

    @PostMapping("add/single")
    String addSingleMovie(@RequestBody MovieParamSingle paramSingle) {
        BangumiMovie movie = paramSingle.getMovie();
        User currentUser = paramSingle.getCurrentUser();
        System.out.println("user "+currentUser);
        movie.setAddedUser(currentUser);
        return movieServices.addSingleMovie(movie);
    }

    @DeleteMapping("remove/more")
    String removeMovies(@RequestBody List<String> ids) {
        return movieServices.removeMovies(ids);
    }

    @DeleteMapping("remove/single")
    String removeSingleMovie(String id) {
        return movieServices.removeSingleMovie(id);
    }

    @PutMapping("update")
    String updateMovie(@RequestBody @Validated(MovieValidate.class) BangumiMovie movie, @RequestBody @Validated(UserValidate.class) User currentUser) {
        BangumiMovie movieGet;
        try {
            movieGet = movieServices.getSingleMovie(movie.getMovieId());
            if (movieGet.getAddedUser().equals(currentUser)) {
                return movieServices.updateMovie(movie);
            } else {
                throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_UPDATE_USER_INVALID, "You cannot delete item that does not belong to you");
            }
        } catch (NullPointerException ex) {
            throw new ReqExceptions(ErrorCode.BangumiMovie.BANGUMI_MOVIE_NOT_FOUND, "The movie cannot found");
        }
    }

    @GetMapping("get/single")
    BangumiMovie getSingleMovie(String id) {
        return movieServices.getSingleMovie(id);
    }

    @GetMapping("get/more")
    List<BangumiMovie> getMovies() {
        return movieServices.getMovies();
    }
}
