package com.adl.sampleproject.two.services.interfaces;

import com.adl.sampleproject.two.dtos.AdvanceServerInfoDto;
import com.adl.sampleproject.two.dtos.ServerInfoDto;
import com.adl.sampleproject.two.exception.ServiceException;

public interface TestService {
    ServerInfoDto getServerInfo() throws ServiceException;

    AdvanceServerInfoDto getAdvanceServerInfo() throws ServiceException;
}
