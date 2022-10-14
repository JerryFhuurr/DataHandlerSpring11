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
public class BangumiNot {
    private long bangumiNotId;
    private long bangumiId;
    private int companyId;
    private int typeId;
    private Date bangumiAddDate;
    private User addedUser;
}
