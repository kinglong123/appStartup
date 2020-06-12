package com.nd.hy.ele.idpmodule.module;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lanjl on 2020/5/27.
 */
public class RefreshTokenBodyVo {
    @JsonProperty("client_id")
    private String client_id;
    @JsonProperty("refresh_token")
    private String refresh_token;

    @JsonProperty("grant_type")
    private String grant_type;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
