package treeserver.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import treeserver.bean.HttpResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cao on 7/27/16.
 */
@ControllerAdvice
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Resource
    protected HttpServletRequest httpServletRequest;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResult exceptionHandler(Exception e) {
        logger.error("错误", e);
        return HttpResult.error( "未知错误",500);
    }
}
