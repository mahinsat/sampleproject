package com.adl.sampleproject.two.dtos;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class AdvanceServerInfoDto {
    private ServerInfoDto localServerInfo;
    private ServerInfoDto remoteServerInfo;
}
