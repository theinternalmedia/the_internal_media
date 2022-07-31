package com.tim.data;

public class ValidationInput {

    public static class User{
        public static final String ID_NOTBLANK = "ID người dùng không được để trống";
        public static final String ID_MAX_SIZE = "ID người dùng dài tối đa 20 ký tự";

        public static final String NAME_NOTBLANK = "Name người dùng không được để trống";
        public static final String NAME_MAX_SIZE = "Name người dùng dài tối đa 50 ký tự";

        public static final String EMAIL_NOTBLANK = "Email người dùng không được để trống";
        public static final String EMAIL_MAX_SIZE = "Email người dùng dài tối đa 50 ký tự";
        public static final String EMAIL_SYNTAX = "Email người dùng không đúng định dạng";

        public static final String PASSWORD_NOTBLANK = "Password người dùng không được để trống";
        public static final String PASSWORD_MAX_SIZE = "Password người dùng dài tối đa 100 ký tự";

        public static final String ADDRESS_MAX_SIZE = "Address người dùng dài tối đa 100 ký tự";
        public static final String PHONE_MAX_SIZE = "Phone number người dùng dài tối đa 10 ký tự";
        public static final String AVATAR_MAX_SIZE = "Avatar người dùng kích thước lớn";
    }

    public static class General{
        public static final String NAME_NOTBLANK = "Name không được để trống";
        public static final String NAME_MAX_SIZE = "Name không quá 50 kí tự";

        public static final String CODE_NOTBLANK = "Code không được để trống";
        public static final String CODE_MAX_SIZE = "Code không quá 50 kí tự";
    }

    public static class Mark{
        public static final String MARKS_MAX_SIZE = "Điểm người dùng không quá giới hạn 0-10";
    }

}
