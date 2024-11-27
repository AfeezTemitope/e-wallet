package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayStackDataResponse {

        @JsonProperty("authorization_url")
        private String authorizationUrl;
        @JsonProperty("access_code")
        private String accessCode;
        private String reference;

}
