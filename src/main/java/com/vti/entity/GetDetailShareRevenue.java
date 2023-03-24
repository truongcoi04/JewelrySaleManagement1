package com.vti.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class GetDetailShareRevenue {

    private Long share_trans_id;
    private Long service_id;
    private String service_name;
    private String share_trans_code;
    private Date contract_sign_date;
    private String contract_no;
    private Date status_bbds;
    private Date created_datetime_bbds;
    private String description;



}
