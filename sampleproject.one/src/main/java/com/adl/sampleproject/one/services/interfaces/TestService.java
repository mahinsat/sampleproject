package com.adl.sampleproject.one.services.interfaces;

import com.adl.sampleproject.one.dtos.ServerInfoDto;
import com.adl.sampleproject.one.exception.ServiceException;

public interface TestService {
    ServerInfoDto getServerInfo() throws ServiceException;
}
