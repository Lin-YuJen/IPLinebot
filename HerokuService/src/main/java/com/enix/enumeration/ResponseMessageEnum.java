package com.enix.enumeration;

public enum ResponseMessageEnum {

    SUCCESS("0000", "操作成功"),
    FAIL("1000", "操作失敗"),
    MISSING_PARAMETERS("1002", "缺少參數"),
    DATE_TIME_PARSE_FAILURE("1003", "日期時間轉換錯誤"),
    FORBIDDEN("1001", "認證失敗"),
    EXCEPTION("9999", "未知錯誤");

    private final String responseCode;
    private final String responseMessage;

    ResponseMessageEnum(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
