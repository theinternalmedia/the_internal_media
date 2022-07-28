package com.tim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "class")
public class Class extends BaseEntity{

    /**
     * thinhnguyen
     */

    private static final long serialVersionUID = 4599295413576661341L;

    @Column(nullable = false)
    @Size(max = 50)
    private String name;

    @Column(unique = true, nullable = false)
    @Size(max = 50)
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
