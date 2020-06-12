package com.nd.hy.ele.idpmodule.module;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by lanjl on 2020/5/27.
 */
public class TicketVo implements Serializable {

    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("expires_at")
    private String expires_at;

    @JsonProperty("refresh_token")
    private String refresh_token;


    @JsonProperty("open_id")
    private String open_id;

    @JsonProperty("server_time")
    private String server_time;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getServer_time() {
        return server_time;
    }

    public void setServer_time(String server_time) {
        this.server_time = server_time;
    }
}
