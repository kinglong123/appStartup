package com.nd.hy.ele.idpmodule.sdk;

import com.nd.hy.ele.idpmodule.api.TicketApi;
import com.nd.hy.ele.idpmodule.cache.TicketUtil;
import com.nd.hy.ele.idpmodule.client.AppClient;
import com.nd.hy.ele.idpmodule.module.AccessTokenBodyVo;
import com.nd.hy.ele.idpmodule.module.RefreshTokenBodyVo;
import com.nd.hy.ele.idpmodule.module.TicketVo;
import com.nd.hy.ele.idpmodule.module.UserInfoBodyVo;
import com.nd.hy.ele.idpmodule.module.UserInfoVo;

import android.content.Context;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by lanjl on 2020/5/27.
 */
public class UcTicket {
    /**
     * 缺点：效率相对低一点，影响速度
     * 有点：节约性能，能够确保多线程的情况下也是唯一 一个实例
     */
    private static UcTicket instance = null;

    private UcTicket() {
    }

    public static UcTicket getInstance() {

        if (instance == null) {//第一个用来提供速度，被进synchronized 等着
            synchronized (UcTicket.class) {
                if (instance == null) {//这是必须的，两个都会走到这里时，要为第二个来的判断下
                    instance = new UcTicket();
                }
            }
        }
        return instance;
    }

    public Observable<TicketVo> getAccessToken(final Context context,String client_id, String client_secret,String ticket,String grant_type) {
        TicketApi ticketApi = AppClient.INSTANCE.getTicketApi();
        AccessTokenBodyVo accessTokenBodyVo = new AccessTokenBodyVo();
        accessTokenBodyVo.setClient_id(client_id);
        accessTokenBodyVo.setClient_secret(client_secret);
        accessTokenBodyVo.setTicket(ticket);
        accessTokenBodyVo.setGrant_type(grant_type);
        return ticketApi.getAccessToken(accessTokenBodyVo).doOnNext(new Action1<TicketVo>() {
            @Override
            public void call(TicketVo ticketVo) {
                TicketUtil.setTicketVo(context,ticketVo);
            }
        });

    }

    public Observable<TicketVo> getAccessToken(final Context context,String client_id, String refresh_token,String grant_type) {
        TicketApi ticketApi = AppClient.INSTANCE.getTicketApi();
        RefreshTokenBodyVo refreshTokenBodyVo = new RefreshTokenBodyVo();
        refreshTokenBodyVo.setClient_id(client_id);
        refreshTokenBodyVo.setGrant_type(grant_type);
        return ticketApi.getRefreshyToken(refreshTokenBodyVo).doOnNext(new Action1<TicketVo>() {
            @Override
            public void call(TicketVo ticketVo) {
                TicketUtil.setTicketVo(context,ticketVo);
            }
        });

    }

    public Observable<UserInfoVo> getUserInfo(String open_id, String access_token) {
        TicketApi ticketApi = AppClient.INSTANCE.getTicketApi();
        UserInfoBodyVo userInfoBodyVo = new UserInfoBodyVo();
        userInfoBodyVo.setOpen_id(open_id);
        userInfoBodyVo.setAccess_token(access_token);
        return ticketApi.getUserInfo(userInfoBodyVo);

    }

}
