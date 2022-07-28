package com.tim.converter;


import org.springframework.stereotype.Component;

@Component
public abstract class AbstractConverter<T1,T2> {
    T1 toDto(T2 entity){
        return null;
    }
    T2 toEntity(T1 dto){
        return null;
    }
    T2 toEntity(T1 dto, T2 oldEntity){
        return null;
    }
}
