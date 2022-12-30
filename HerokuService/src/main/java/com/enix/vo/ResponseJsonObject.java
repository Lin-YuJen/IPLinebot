package com.enix.vo;

import com.enix.enumeration.ResponseMessageEnum;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ResponseJsonObject {

    private final JsonObject jsonResponse;
    private final JsonObject jsonData;

    public ResponseJsonObject() {
        this.jsonData = new JsonObject();
        this.jsonResponse = new JsonObject();
    }

    public ResponseJsonObject(ResponseMessageEnum message){
        this();
        this.setResponseMessage(message);
    }

    public ResponseJsonObject setResponseMessage(ResponseMessageEnum message) {
        jsonResponse.addProperty("code", message.getResponseCode());
        jsonResponse.addProperty("message", message.getResponseMessage());
        return this;
    }

    public JsonObject build() {
        jsonResponse.add("data", jsonData);
        return jsonResponse;
    }

    /**
     * @see com.google.gson.JsonObject
     */
    public ResponseJsonObject addProperty(String property, Object value) {

        if (value instanceof String) jsonData.addProperty(property, (String) value);
        else if (value instanceof Number) jsonData.addProperty(property, (Number) value);
        else if (value instanceof Boolean) jsonData.addProperty(property, (Boolean) value);
        else if (value instanceof Character) jsonData.addProperty(property, (Character) value);
        else throw new RuntimeException("不支援此種 value");
        return this;
    }

    /**
     * @see com.google.gson.JsonObject#add(String, JsonElement)
     */
    public ResponseJsonObject add(String property, JsonElement value) {
        jsonData.add(property, value);
        return this;
    }

    public Object invokeData(String methodName, Object... args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = JsonObject.class.getMethod(methodName);
        return method.invoke(jsonData, args);
    }
}
