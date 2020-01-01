package com.example.genaration.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yechao yechao
 * @date 2017/8/1 22:25.
 * @E-mail gonefuture@qq.com
 * <p>
 * 说明：状态信息类
 */

@Getter
@Setter
@ToString
public class ServerResponse<T> implements Serializable {
    //状态码
    private String status;
    //具体信息
    private String msg;
    //用来放各种关键的属性，如userId
    private T data;

    public ServerResponse(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServerResponse(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

}
