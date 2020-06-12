package com.nd.hy.ele.idpmodule.cache;

import com.nd.hy.ele.idpmodule.module.TicketVo;

import android.content.Context;

/**
 * Created by lanjl on 2020/5/27.
 */
public class TicketUtil {

    private static final String CACHE_NAME = "TICKET_CACHE_NAMEE";

    private static final String CACHE_KEY = "TICKET_CACHE__KEY";

    private static  SharedPrefCache<String, TicketVo> cache;


    public static void setTicketVo(Context context,TicketVo ticketVo) {
        if(cache==null){
            cache =  new SharedPrefCache<>(context,CACHE_NAME, TicketVo.class);
        }
        cache.put(CACHE_KEY, ticketVo);
    }
    public static TicketVo getTicketVo() {
        if(cache !=null) {
            TicketVo ticketVo = cache.get(CACHE_KEY);
            return ticketVo;
        }else {
            return null;
        }

    }
    public static String getAccess() {
        if(cache !=null) {
            TicketVo ticketVo = getTicketVo();
            if(ticketVo!=null){
               return ticketVo.getAccess_token();
            }
            return "";
        }else {
            return null;
        }

    }
    public static String getOpenId() {
        if(cache !=null) {
            TicketVo ticketVo = getTicketVo();
            if(ticketVo!=null){
                return ticketVo.getOpen_id();
            }
            return "";
        }else {
            return null;
        }

    }

}
