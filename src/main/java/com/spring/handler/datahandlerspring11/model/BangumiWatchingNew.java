package com.spring.handler.datahandlerspring11.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BangumiWatchingNew {
    private long bangumiWatchingNewId;
    private long bangumiId;
    private int companyId;
    private int typeId;
    private int bangumiWatchingEpisode;
    private Date bangumiWatchingStartDate;
    /**
     * Day of the week update
     * 周几更新
     */
    private int bangumiUpdateDay;
    /**
     * Time of update
     */
    private Time bangumiUpdateTime;
    private User addedUser;
}
