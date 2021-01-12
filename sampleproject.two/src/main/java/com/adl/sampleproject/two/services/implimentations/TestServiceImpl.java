package com.adl.sampleproject.two.services.implimentations;

import com.adl.sampleproject.two.dtos.AdvanceServerInfoDto;
import com.adl.sampleproject.two.dtos.ServerInfoDto;
import com.adl.sampleproject.two.exception.ErrorCode;
import com.adl.sampleproject.two.exception.ServiceException;
import com.adl.sampleproject.two.services.interfaces.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${remote.info.url}")
    private String remoteUrl;

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public AdvanceServerInfoDto getAdvanceServerInfo() throws ServiceException {
        log.debug("Inside getServerInfo method");
        AdvanceServerInfoDto result = new AdvanceServerInfoDto();
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            log.debug("Host name : {}  , ip: {}  , port: {}", hostName, ip, port);
            result.setLocalServerInfo(new ServerInfoDto(hostName, ip, String.valueOf(port), applicationName));
        } catch (UnknownHostException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SVR001, "Unable to retrieve local " +
                    "server data", "Unable to retrieve local server data");
        }

        try {
            ResponseEntity<ServerInfoDto> remoteResult = restTemplate.exchange(remoteUrl, HttpMethod.GET, null,
                    ServerInfoDto.class);
            log.debug("Result from remote server: {}", remoteResult.getBody());
            result.setRemoteServerInfo(remoteResult.getBody());
        } catch (RestClientException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.HTTP001, "Unable to retrieve " +
                    "remote server data", "Unable to retrieve remote server data");
        }

        return result;
    }
}
