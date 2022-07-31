package com.tim.utils;

import com.tim.entity.User;

import javax.validation.constraints.Size;
import java.lang.reflect.Field;

public class Messeage{
    public final static String m = "hi";
    public final static String getMessage() {
        try {
            Class userClass = User.class;
            Field[] fields = userClass.getDeclaredFields();

            String message = null;
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (field.isAnnotationPresent(Size.class)) {

                    Size size = field.getAnnotation(Size.class);
                    int max = size.max();

                    if (max > (int) field.get(userClass)) {
                        message = fieldName + " không quá " + max + " kí tự";
                        //throw CustomException(message)
                    }
                }
            }
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
