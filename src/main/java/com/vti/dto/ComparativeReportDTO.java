package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ComparativeReportDTO {

    private String errorCode;
    private String message;
    private String errorField;
    private Long status;

    private Long shareTransId;
    private Long serviceId;
    private String serviceName;
    private String shareTransCode;
    private Date contractSignDate;
    private String contractNo;
    private Date shareCycle;
    private Long statusBbds;
    private Date createdDatetimeBbds;
    private List<FileBbds> listFileBbds;
    private List<ShareTransDetail> shareTransDetailLv1;
    private String description;



}
