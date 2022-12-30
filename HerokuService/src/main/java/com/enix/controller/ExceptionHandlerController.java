package com.enix.controller;

import com.enix.vo.ResponseJsonObject;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeParseException;

import static com.enix.enumeration.ResponseMessageEnum.DATE_TIME_PARSE_FAILURE;

/**
 * 自訂一個專門處理錯誤的 Controller，就跟一般 Controller 寫法一樣
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 時間轉換錯誤
     */
    @ExceptionHandler({DateTimeParseException.class})
    @ResponseBody
    public String dateTimeParseException(DateTimeParseException exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseJsonObject(DATE_TIME_PARSE_FAILURE).build().toString();
    }
}
