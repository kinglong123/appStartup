package com.nd.hy.ele.idpmodule.module;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by lanjl on 2020/5/27.
 */
public class UserInfoVo implements Serializable {

    @JsonProperty("open_id")
    private String open_id;

    @JsonProperty("nick_name")
    private String nick_name;

    @JsonProperty("avatar_url")
    private String avatar_url;

    @JsonProperty("gender")
    private String gender;

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
