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
public class Bangumi {
    private String bangumiId;
    private int companyId;
    private int typeId;
    private String bangumiNameJp;
    private String bangumiNameCn;
    private String bangumiName2;
    /**
     * The total number of episodes for the bangumi (not include OVA or OAD)
     */
    private int bangumiEpisode;
    /**
     * The release year of bangumi
     */
    private int bangumiYear;
    /**
     * The release date of episode 1 in Japan
     */
    private Date bangumiDate;
    private User addedUser;
    private String bangumiRemark;
}
