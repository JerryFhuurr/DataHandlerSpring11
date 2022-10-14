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
public class BangumiFinished {
    private long bangumiFinishedId;
    private long bangumiId;
    private int companyId;
    private int typeId;
    private int finishedYear;
    private Date finishedDate;
    private User addedUser;
}
