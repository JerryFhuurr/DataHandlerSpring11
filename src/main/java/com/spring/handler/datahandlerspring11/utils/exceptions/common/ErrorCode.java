package com.spring.handler.datahandlerspring11.utils.exceptions.common;

public class ErrorCode {
    /**
     * <b>User part</b>
     * <p>
     * Code starts from 100
     * </p>
     */
    public static class User {
        public static final String USER_NOT_FOUND = "100";
        public static final String USER_PERMISSION_INVALID = "101";
    }

    /**
     * <b>Company part</b>
     * <p>
     * Code starts from 200
     * </p>
     */
    public static class Company {
        public static final String COMPANY_DUPLICATED = "200";
    }

    /**
     * <b>Type part</b>
     * <p>
     * Code starts from 300
     * </p>
     */
    public static class Type {
        public static final String TYPE_DUPLICATED = "300";
        public static final String TYPE_PERMISSION_INVALID = "301";
        public static final String TYPE_NOT_FOUND = "302";
    }

    /**
     * <b>Bangumi movie part</b>
     * <p>
     * Code starts from 400
     * </p>
     */
    public static class BangumiMovie {
        public static final String BANGUMI_MOVIE_DUPLICATED = "400";
        public static final String BANGUMI_MOVIE_NOT_FOUND = "401";
        public static final String BANGUMI_MOVIE_REMOVE_OTHER = "402";
    }
}
