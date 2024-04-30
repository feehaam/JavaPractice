package com.feeham.playground.utils;

import com.feeham.playground.exceptions.CustomException;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SuppressWarnings("unchecked")
public interface MapExtractor {
    @SuppressWarnings("unchecked")
    public default  <T> T read(Map<String, Object> mappedObject, String key, Class<T> expectedClass,
                                          String errorMessage) {
        Object object = mappedObject.getOrDefault(key, null);
        if (object == null) {
            throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
        }
        if (!expectedClass.isInstance(object)) {
            return null;
        }
        return (T) object;
    }

    public default <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        try {
            T object = clazz.newInstance();
            // For each of the properties of the expected class, try to assign value.
            for (Field field : clazz.getDeclaredFields()) {
                setValue(map, clazz, field, object);
            }
            return object;
        }
        catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to create object from map", e);
        }
    }

    private <T> void setValue(Map<String, Object> map, Class<T> clazz, Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        String fieldName = field.getName();
        Object value = map.get(fieldName);

        // Set the value based on its type, ignore if the filed doesn't exist in JSON
        if (!map.containsKey(fieldName)) {
            return;
        }
        else if (field.getType().isPrimitive()) {
            setPrimitiveValue(field, object, value);
        }
        else if (field.getType().isEnum()) {
            setEnumValue(field, object, value);
        }
        else if (value instanceof Map) {
            setMapValue(value, field, object);
        }
        else if (value instanceof List<?>) {
            setListValue(value, field, object);
        }
        else {
            field.set(object, value);
        }
    }

    // Recursively calls the parent method to set up the nested object first.
    private <T> void setMapValue(Object value, Field field, Object object) throws IllegalAccessException {
        Object nestedObject = mapToObject((Map) value, field.getType());
        field.set(object, nestedObject);
    }

    // Checks whether the items inside the list are objects of another class or not, and sets them accordingly.
    private <T> void setListValue(Object value, Field field, Object object) throws IllegalAccessException {
        List<?> listValue = (List<?>) value;
        List<Object> mappedList = new ArrayList<>();
        for (Object listItem : listValue) {
            if (listItem instanceof Map) {
                mappedList.add(mapToObject((Map) listItem, field.getType().getComponentType()));
            } else {
                mappedList.add(listItem);
            }
        }
        field.set(object, mappedList);
    }

    // Responsible for putting the enum values.
    private <T> void setEnumValue(Field field, T object, Object value) throws IllegalAccessException {
        if (value instanceof String) {
            Enum<?> enumValue = Enum.valueOf((Class<Enum>) field.getType(), (String) value);
            field.set(object, enumValue);
        }
    }

    // Responsible for putting the primitive values.
    private <T> void setPrimitiveValue(Field field, T object, Object value) throws IllegalAccessException {
        if (field.getType().equals(int.class)) {
            field.setInt(object, (Integer) value);
        } else if (field.getType().equals(double.class)) {
            field.setDouble(object, (Double) value);
        } else if (field.getType().equals(boolean.class)) {
            field.setBoolean(object, (Boolean) value);
        } else if (field.getType().equals(char.class)) {
            field.setChar(object, (Character) value);
        }
    }
}
