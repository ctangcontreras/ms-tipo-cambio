package com.ctang.spring.webflux.model;


import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class TipoCambio {
    
    private String result;
    private String provider;
    private String documentation;
    private String terms_of_use;
    private String time_last_update_unix;
    private String time_last_update_utc;
    private String time_next_update_unix;
    private String time_next_update_utc;
    private String time_eol_unix;
    private String base_code;
    private Map<String, Object> rates;
}
