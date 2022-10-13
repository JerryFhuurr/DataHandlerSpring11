package com.spring.handler.datahandlerspring11.model;

import com.spring.handler.datahandlerspring11.services.validateGroup.MovieValidate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BangumiMovie {
    private int movieId;
    @NotBlank (message = "The name cannot be empty !", groups = MovieValidate.class)
    private String movieNameJp;
    @NotBlank (message = "The name cannot be empty !", groups = MovieValidate.class)
    private String movieNameCn;
    @PastOrPresent(message = "The date should be today or before!", groups = MovieValidate.class)
    private Date movieAddDate;
    /**
     * The release year of movie in Japan
     */
    private int movieYear;
    /**
     * The release date of movie in Japan
     */
    @PastOrPresent(message = "The date should be today or before!", groups = MovieValidate.class)
    private Date movieStartDate;
    private User addedUser;
}
