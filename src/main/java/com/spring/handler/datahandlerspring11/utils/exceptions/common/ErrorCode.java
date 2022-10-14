package com.spring.handler.datahandlerspring11.utils.exceptions.common;

public class ErrorCode {
    /**
     * <b>User part</b>
     * <p>
     * Code starts from 1000
     * </p>
     */
    public static class User {
        public static final String USER_NOT_FOUND = "1000";
        public static final String USER_PERMISSION_INVALID = "1001";
    }

    /**
     * <b>Company part</b>
     * <p>
     * Code starts from 2000
     * </p>
     */
    public static class Company {
        public static final String COMPANY_DUPLICATED = "2000";
    }

    /**
     * <b>Type part</b>
     * <p>
     * Code starts from 3000
     * </p>
     */
    public static class Type {
        public static final String TYPE_DUPLICATED = "3000";
        public static final String TYPE_PERMISSION_INVALID = "3001";
        public static final String TYPE_NOT_FOUND = "3002";
    }

    /**
     * <b>Bangumi movie part</b>
     * <p>
     * Code starts from 4000
     * </p>
     */
    public static class BangumiMovie {
        public static final String BANGUMI_MOVIE_DUPLICATED = "4000";
        public static final String BANGUMI_MOVIE_NOT_FOUND = "4001";
        public static final String BANGUMI_MOVIE_REMOVE_OTHER = "4002";
        public static final String BANGUMI_MOVIE_UPDATE_USER_INVALID = "4003";
        public static final String BANGUMI_MOVIE_ADDED_DATE_INVALID = "4004";
        public static final String BANGUMI_MOVIE_START_DATE_INVALID = "4005";
    }
}
