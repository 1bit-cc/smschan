package com.example.smschan;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import com.example.smschan.chanapi.WeCom_API;
import com.example.smschan.config.Config;
import com.example.smschan.error.ErrorPage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class SmachanController {
    // 这个是Maven测试用的
    @RequestMapping("/springtest")
    public String springbootTest() {
        return "OK";
    }

    @RequestMapping("/")
    public String index() {
        return "SMS Chan API";
    }

    @RequestMapping("/qwt")
    public ResponseEntity<Object> qywx_send_text_msg(@RequestParam String msg, @RequestParam String token)
            throws IOException {
        String sb = new Config().getFileString("./config/tokenFile.txt");

        if (token.equals(sb.toString())) {
            String[] wxDat = new Config().getFileArrays("./config/wxData.txt");
            String rdat = new WeCom_API(wxDat[0], wxDat[1])
                    .SendTextToUserExternal("@all", wxDat[2], msg);
            if (rdat.equals("ok")) {
                return new ErrorPage().Page200("OK");
            } else {
                return new ErrorPage().Page500("WeCom Error");
            }
        } else {
            return new ErrorPage().Page403("Invalid Token");
        }
    }

    @RequestMapping("/qwtc")
    public ResponseEntity<Object> qywx_send_text_card_msg(@RequestParam String msg, @RequestParam String token,
            @RequestParam String title, @RequestParam String description, @RequestParam String url,
            @RequestParam String btntxt) throws IOException {
        String sb = new Config().getFileString("./config/tokenFile.txt");

        if (token.equals(sb.toString())) {
            String[] wxDat = new Config().getFileArrays("./config/wxData.txt");
            String rdat = new WeCom_API(wxDat[0], wxDat[1])
                    .SendTextCardToUserExternal("@all", wxDat[2], msg, title, description, url, btntxt);
            if (rdat.equals("ok")) {
                return new ErrorPage().Page200("OK");
            } else {
                return new ErrorPage().Page500("WeCom Error");
            }
        } else {
            return new ErrorPage().Page403("Invalid Token");
        }
    }

    // @Bean
    // public WebServerFactoryCustomizer<ConfigurableWebServerFactory>
    // webServerFactoryCustomizer() {
    // return (factory -> {
    // ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,
    // "/error/errorPage404.html");
    // ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,
    // "/error/errorPage400.html");
    // ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN,
    // "/error/errorPage403.html");
    // ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
    // "/error/errorPage500.html");
    // factory.addErrorPages(errorPage404, errorPage400, errorPage401,
    // errorPage500);
    // });
    // }
}
