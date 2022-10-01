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
public class bangumiWatchingOld {
    private int bangumiNotOldId;
    private int bangumiId;
    private int companyId;
    private int typeId;
    private Date bangumiAddDate;
    private int bangumiWatchedEpisode;
}