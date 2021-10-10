package icu.junyao.common.handler;


import icu.junyao.common.entity.R;
import icu.junyao.common.enums.ResultCode;
import icu.junyao.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Set;

/**
 * <p>全局异常处理器</p>
 *
 * @author johnson
 * @date 2021-10-02
 */
@Slf4j
@Component
@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public R<String> globalException(HttpServletRequest request, BaseException ex) {

        String errorCode = StringUtils.isBlank(ex.getResponseEnum().getCode()) ? ResultCode.BIZ_ERROR.getCode() : ex.getResponseEnum().getCode();
        log.error("触发请求:[{}]时系统出现异常，异常类型：{}，异常信息：{}", request.getRequestURI(),
                ResultCode.BIZ_ERROR.getMessage(), ex.getMessage());
        return R.fail(errorCode, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public R<String> runtimeException(HttpServletRequest request, Throwable ex) {
        log.error("触发请求:[{}]时系统出现异常，异常类型：{}，异常信息：{}", request.getRequestURI(),
                ResultCode.SYSTEM_ERROR.getMessage(), ExceptionUtils.getStackTrace(ex));

        return R.fail(ResultCode.SYSTEM_ERROR.getCode(), "系统异常");
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R<String> handleInvalidGrantException(AuthenticationException e) {
        log.error("拒绝授权异常信息 ex={}", e.getMessage());
        return R.fail(e.getMessage());
    }

    /**
     * AccessDeniedException
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R<String> handleAccessDeniedException(AccessDeniedException e) {
        log.error("拒绝授权异常信息 ex={}", e.getMessage());
        return R.fail("权限不足，不允许访问");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R<String> handleBindException(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.error("异常类型：{}，异常信息：{}",
                ResultCode.PARAM_ERROR.getMessage(), ExceptionUtils.getStackTrace(ex));
        assert fieldError != null;
        return R.fail(ResultCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
    }

    @ExceptionHandler({BindException.class})
    public R<String> handleBindExceptionNoRequestBody(BindException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.error("异常类型：{}，异常信息：{}",
                ResultCode.PARAM_ERROR.getMessage(), ExceptionUtils.getStackTrace(ex));
        assert fieldError != null;
        return R.fail(ResultCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public R<String> handleBindException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        String message = ResultCode.SYSTEM_ERROR.getMessage();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            message = constraintViolation.getMessage();
            break;
        }
        log.error("异常类型：{}，异常信息：{}",
                ResultCode.PARAM_ERROR.getMessage(), ExceptionUtils.getStackTrace(ex));
        return R.fail(ResultCode.PARAM_ERROR.getCode(), message);
    }
}
