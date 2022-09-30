package com.spring.handler.datahandlerspring11.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BangumiMovie {
    private int movieId;
    private String movieNameJp;
    private String movieNameCn;
    private Date movieAddDate;
    /**
     * The release year of movie in Japan
     */
    private int movieYear;
    /**
     * The release date of movie in Japan
     */
    private Date movieStartDate;
}
