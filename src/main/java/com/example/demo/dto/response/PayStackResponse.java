package com.example.demo.dto.response;
import com.example.demo.dto.response.PayStackDataResponse;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PayStackResponse {
    private boolean status;
    private String message;
    private PayStackDataResponse data;

}
