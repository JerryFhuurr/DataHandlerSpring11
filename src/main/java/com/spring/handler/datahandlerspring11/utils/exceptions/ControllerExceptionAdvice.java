package com.spring.handler.datahandlerspring11.utils.exceptions;

import com.spring.handler.datahandlerspring11.utils.exceptions.common.ResponseConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMeg = ResponseConstant.ERROR_OTHER;

        if (bindingResult.hasErrors()) {
            // Get all error messages
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                FieldError fieldError = (FieldError) allErrors.get(0);
                errorMeg = fieldError.getDefaultMessage();
            }
        }

        Map<String, Object> exceptionResp = new HashMap<>(4);
        exceptionResp.put(ResponseConstant.ERROR_CODE, ReqExceptions.DEFAULT_ERROR_CODE);
        exceptionResp.put(ResponseConstant.ERROR_MESSAGE, errorMeg);
        return exceptionResp;
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> methodArgumentNotValidException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMeg = ResponseConstant.ERROR_OTHER;

        if (bindingResult.hasErrors()) {
            // Get all error messages
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                FieldError fieldError = (FieldError) allErrors.get(0);
                errorMeg = fieldError.getDefaultMessage();
            }
        }

        Map<String, Object> exceptionResp = new HashMap<>(4);
        exceptionResp.put(ResponseConstant.ERROR_CODE, ReqExceptions.DEFAULT_ERROR_CODE);
        exceptionResp.put(ResponseConstant.ERROR_MESSAGE, errorMeg);
        return exceptionResp;
    }

    /**
     * Other exceptions handler
     * @param ex exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException(Exception ex) {
        Map<String, Object> exceptionResp = new HashMap<>(4);
        exceptionResp.put(ResponseConstant.ERROR_CODE, ReqExceptions.DEFAULT_ERROR_CODE);
        exceptionResp.put(ResponseConstant.ERROR_MESSAGE, ResponseConstant.ERROR_OTHER);
        return exceptionResp;
    }

    /**
     * For internal error
     * @param ex exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ReqExceptions.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleReqException(Exception ex) {
        Map<String, Object> exceptionResp = new HashMap<>(4);
        String errorMsg = ex.getMessage();
        String errorCode = ((ReqExceptions) ex).getCode();
        exceptionResp.put(ResponseConstant.ERROR_CODE, errorCode);
        exceptionResp.put(ResponseConstant.ERROR_MESSAGE, errorMsg);
        return exceptionResp;
    }
}
