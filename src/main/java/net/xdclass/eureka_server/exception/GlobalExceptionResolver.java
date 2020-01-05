package net.xdclass.eureka_server.exception;

import groovy.json.JsonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionResolver {
    @ExceptionHandler(Exception.class)
    public ModelAndView globalErrorHandler(HttpServletRequest request, HttpServletResponse response,Exception ex) {
        log.info("出现异常的请求地址: url={}" , request.getServletPath());
        log.info("出现异常的信息: " + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        modelAndView.setView(view);
        modelAndView.addAllObjects(getParams(ex,request,response));
        return modelAndView;
    }

    private Map<String,String> getParams(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        String errorCode = BusinessExceptionConstnts.CODE_500.getErrCode();
        String errorMsg = BusinessExceptionConstnts.CODE_500.getErrMsg();
        if (ex instanceof NoHandlerFoundException || ex instanceof HttpRequestMethodNotSupportedException) {
            errorCode = BusinessExceptionConstnts.CODE_403.getErrCode();
            errorMsg = BusinessExceptionConstnts.CODE_403.getErrMsg();
        } else if (ex instanceof HttpMessageNotReadableException || ex instanceof JsonException) {
            errorCode = BusinessExceptionConstnts.CODE_400.getErrCode();
            errorMsg = BusinessExceptionConstnts.CODE_400.getErrMsg();
        } else if (ex instanceof  BusinessException) {
            BusinessException be = (BusinessException)ex;
            errorCode = BusinessExceptionConstnts.CODE_400.getErrCode();
            errorMsg = ((BusinessException) ex).getErrMsg();
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            errorCode = BusinessExceptionConstnts.CODE_400.getErrCode();
            errorMsg = "请求头不合理!";
        }
        if (StringUtils.isNotBlank(errorCode)) {
            response.setStatus(Integer.parseInt(errorCode));
        } else {
            response.setStatus(200);
        }
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("errorCode",errorCode);
        resultMap.put("errorMsg",errorMsg);
        return  resultMap;
    }
}
