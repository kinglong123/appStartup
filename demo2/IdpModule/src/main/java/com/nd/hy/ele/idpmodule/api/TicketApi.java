package com.nd.hy.ele.idpmodule.api;

import com.nd.hy.ele.idpmodule.module.AccessTokenBodyVo;
import com.nd.hy.ele.idpmodule.module.RefreshTokenBodyVo;
import com.nd.hy.ele.idpmodule.module.TicketVo;
import com.nd.hy.ele.idpmodule.module.UserInfoBodyVo;
import com.nd.hy.ele.idpmodule.module.UserInfoVo;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lanjl on 2020/5/27.
 */
public interface TicketApi {
//    @FormUrlEncoded
    @POST("/v1.1/oauth2/access_token")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Observable<TicketVo> getAccessToken(
            @Body AccessTokenBodyVo accessTokenBodyVo
    );

    @POST("/v1.1/oauth2/refresh_token")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Observable<TicketVo> getRefreshyToken(
            @Body RefreshTokenBodyVo refreshTokenBodyVo
    );

    @POST("/v1.1/oauth2/get_user_info")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Observable<UserInfoVo> getUserInfo(
            @Body UserInfoBodyVo userInfoBodyVo
    );


}
