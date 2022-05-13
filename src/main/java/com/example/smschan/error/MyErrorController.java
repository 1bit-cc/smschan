package com.example.smschan.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 * 出错页面控制器
 * Created by Binary Wang on 2018/8/25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Controller
public class MyErrorController implements ErrorController {

  // 将/error转到404页
  @GetMapping(value = "/error")
  public String errorPage() {
    return "404";
  }

  @GetMapping(value = "/error_404")
  public ResponseEntity<Object> error404() {
    return new ErrorPage().Page404("Not Found");
  }

  @GetMapping(value = "/error_403")
  public ResponseEntity<Object> error403(String errormsg) {
    return new ErrorPage().Page403("Forbidden");
  }

  @GetMapping(value = "/error_400")
  public ResponseEntity<Object> error400() {
    return new ErrorPage().Page400("Bad Request");
  }

  @GetMapping(value = "/error_500")
  public ResponseEntity<Object> error500() {
    return new ErrorPage().Page500("Service Unavailable");
  }

  @Override
  public String getErrorPath() {
    // TODO Auto-generated method stub
    return null;
  }

}
