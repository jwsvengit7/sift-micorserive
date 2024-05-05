package com.sifts.Commons.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private T data;
    private String message;
    private int status;
    public APIResponse(T data,int status){
        this.data=data;
        this.status=status;
        this.message="";
    }
    public APIResponse(T data){
        this.data=data;
        this.status=200;
        this.message="";
    }
    public APIResponse(T data,int status,String message){
        this.data=data;
        this.status=status;
        this.message=message;
    }
}
