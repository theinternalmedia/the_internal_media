package com.tim.utils;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;

public class Messeage {
    public static String getMessage(Class tClass) {

        //tạo message cho dto
        String message = createMessage(tClass);

        // nếu dto là student, teacher thì gọi đến lớp cha vì nó extends userDto
        Class<?> parentClass = tClass.getSuperclass();
        if (parentClass != null) {
            message = createMessage(parentClass);
        }

        return message;
    }

    private static String createMessage(Class tClass) {
        try {
            String message = null;
            Field[] fields = tClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (field.isAnnotationPresent(Size.class)) {

                    Size size = field.getAnnotation(Size.class);
                    int max = size.max();
                    int mix = size.min();

                    if (max > (int) field.get(tClass)) {
                        message = fieldName + " không quá " + max + " kí tự";
                    }
                    if (mix < (int) field.get(tClass)) {
                        message = fieldName + " không nhỏ hơn 0";
                    }
                } else if (field.isAnnotationPresent(NotBlank.class)) {
                    message = fieldName + " không được để trống";
                }
            }
            return message;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
