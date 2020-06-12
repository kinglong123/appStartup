package com.nd.hy.ele.startb;

import com.nd.hy.ele.idpmodule.cache.TicketUtil;
import com.nd.hy.ele.idpmodule.module.TicketVo;
import com.nd.hy.ele.idpmodule.module.UserInfoVo;
import com.nd.hy.ele.idpmodule.sdk.UcTicket;
//import com.tencent.mm.opensdk.openapi.IWXAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivityExplicit extends AppCompatActivity  {
//    应用ID：d11bd323-3d76-47eb-968c-36851184372a
//    密钥：acdb9f6339814436995d39babf873960
    public static final  String CLIENT_ID = "d11bd323-3d76-47eb-968c-36851184372a";
    public static final  String CLIENT_SECRET = "acdb9f6339814436995d39babf873960";

    TextView mTextView;
//    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.tv_2)).setText("显示启动");
        mTextView =   ((TextView)findViewById(R.id.tv_3));

        getAppData();

//        api = WXAPIFactory.createWXAPI(this, "");
//        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


    }

    private void getAppData() {

        Intent intent=getIntent();

       //String data_str = intent.getStringExtra("data");
        Bundle bundle=intent.getExtras();
        if(bundle!=null) {
            String type = bundle.getString("type");
            String ticket = bundle.getString("ticket");
            mTextView.setText("type:"+type+"---ticket:"+ticket);
//            Log.i("tag", data_str);

            if(!TextUtils.isEmpty(ticket)){
                getAccessToken(ticket);
            }else {
                Log.e("ticket:","ticket is empty");
            }
        }


    }

    private void getAccessToken(String ticket) {
        UcTicket.getInstance().getAccessToken(MainActivityExplicit.this,CLIENT_ID,CLIENT_SECRET,ticket,"ticket")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TicketVo>() {
                    @Override
                    public void call(TicketVo ticketVo) {

                        Toast.makeText(getApplication(),ticketVo.getAccess_token(),Toast.LENGTH_SHORT).show();
                        getUserInfo();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("getAccessToken:",throwable.getMessage());
                    }
                });
    }

    private  void  getUserInfo(){

        UcTicket.getInstance().getUserInfo(TicketUtil.getOpenId(),TicketUtil.getAccess())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfoVo>() {
                    @Override
                    public void call(UserInfoVo userInfoVo) {
                        Toast.makeText(getApplication(),userInfoVo.getNick_name(),Toast.LENGTH_SHORT).show();


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("getUserInfo:",throwable.getMessage());
                        System.out.println("TicketUtil getAccess:"+ TicketUtil.getAccess());

                        throwable.printStackTrace();
                    }
                });
    }


}
