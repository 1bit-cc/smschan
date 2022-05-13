package com.example.smschan.error;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPage {
    public ResponseEntity<Object> Page200(String msg) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<Object>("{\"errorcode\":\"0\",\"code\":\"200\",\"msg\":\"" + msg + "\"}", headers,
                HttpStatus.OK);
    }

    public ResponseEntity<Object> Page404(String errormsg) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<Object>("{\"errorcode\":\"404\",\"errormsg\":\"" + errormsg + "\"}", headers,
                HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> Page400(String errormsg) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<Object>("{\"errorcode\":\"400\",\"errormsg\":\"" + errormsg + "\"}", headers,
                HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> Page403(String errormsg) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<Object>("{\"errorcode\":\"403\",\"errormsg\":\"" + errormsg + "\"}", headers,
                HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<Object> Page500(String errormsg) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<Object>("{\"errorcode\":\"500\",\"errormsg\":\"" + errormsg + "\"}", headers,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
