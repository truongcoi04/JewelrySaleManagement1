package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShareTransDetail {

    private Long serviceId;
    private String serviceName;
    private Long quantity;
    private String shareType;
    private Double shareRate;
    private String shareDescription;
    private Long totalRevenue;
    private Long viettelRevenue;
    private Long partnerRevenue;

}
