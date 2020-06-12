package com.nd.hy.ele.idpmodule.module;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by lanjl on 2020/5/27.
 */
public class UserInfoBodyVo  implements Serializable {
    @JsonProperty("open_id")
    private String open_id;
    @JsonProperty("access_token")
    private String access_token;

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
