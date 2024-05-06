package com.sifts.Commons.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoreResponse {
    private Object data;
    private String token;
    private String refrestToken;

}
