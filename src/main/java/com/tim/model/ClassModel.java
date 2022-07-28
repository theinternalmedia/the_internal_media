package com.tim.model;

import com.tim.dto.BaseDto;

public class ClassModel extends BaseDto {

    /**
     * thinhnguyen
     */
    private static final long serialVersionUID = -8648320837995659089L;

    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
