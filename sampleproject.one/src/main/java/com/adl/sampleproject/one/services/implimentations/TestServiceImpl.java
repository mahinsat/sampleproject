package com.adl.sampleproject.one.services.implimentations;

import com.adl.sampleproject.one.dtos.ServerInfoDto;
import com.adl.sampleproject.one.exception.ErrorCode;
import com.adl.sampleproject.one.exception.ServiceException;
import com.adl.sampleproject.one.services.interfaces.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public ServerInfoDto getServerInfo() throws ServiceException {
        log.debug("Inside getServerInfo method");
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            log.debug("Host name : {}  , ip: {}  , port: {}", hostName, ip, port);
            return new ServerInfoDto(hostName, ip, String.valueOf(port), applicationName);
        } catch (UnknownHostException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SVR001, "Unable to retrieve local " +
                    "server data", "Unable to retrieve local server data");
        }
    }
}
