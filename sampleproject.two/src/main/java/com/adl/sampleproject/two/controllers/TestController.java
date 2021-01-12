package com.adl.sampleproject.two.controllers;

import com.adl.sampleproject.two.dtos.AdvanceServerInfoDto;
import com.adl.sampleproject.two.dtos.ServerInfoDto;
import com.adl.sampleproject.two.exception.ServiceException;
import com.adl.sampleproject.two.services.interfaces.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/api/v1/info")
    public ResponseEntity<ServerInfoDto> test(HttpServletRequest request) throws ServiceException {
        log.info("Get server info api called");
        return new ResponseEntity<>(testService.getServerInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/advance-info")
    public ResponseEntity<AdvanceServerInfoDto> test2(HttpServletRequest request) throws ServiceException {
        log.info("get advance server info api called");
        return new ResponseEntity<>(testService.getAdvanceServerInfo(), HttpStatus.OK);
    }
}
