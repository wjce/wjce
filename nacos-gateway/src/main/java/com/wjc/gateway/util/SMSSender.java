package com.wjc.gateway.util;

import java.net.URLEncoder;

/**
 * @author: wjc
 * @createDate: 2020/9/11 20:42
 * @description:
 */
public class SMSSender {

        /**
         * 鸿联九五新平台
         * 2017-10-18号对接
         * @param mobile
         * @param msg
         */
        public static void sendHonglianSms(String mobile, String msg){

        //默认加签名了
        try {
            msg = "【叩我网】" + msg;
            String url = "http://115.28.112.245:8082/SendMT/SendMessage";
            String sms = HttpRequest.sendGet(url, "UserName=kouwo&UserPass=callme111&Mobile="+mobile+"&Content="+ URLEncoder.encode(msg,"utf-8") );
            System.out.println(sms);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
//    public static void  sendHonglianSms(String mobile ,String msg) {
//
//		//默认加签名了
//    	try {
//    		msg = "【叩我网】" + msg;
//    		String url = "http://114.119.10.253:7862/sms?";
//            String sms = HttpRequest.sendPost(url, "action=send&account=200123&password=52wBBs&mobile="+mobile+"&content="+ URLEncoder.encode(msg,"utf-8")+"&extno=106909&rt=json");
//		    System.out.println(sms);
//    	} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//	}
        public static void main(String[] args) {
        //sendAlertSms("test");
//		Order order = OrderFactory.getInstance().getOrderWithId("a1387850538562931887");
//		SMSSender.sendOrderSms("18810836804", order.orderMsg(), order.channelId());

        String msg = "您好，本次操作验证码为: 531474, 有效时间为30分钟";
        //String msg = "【叩我网】您已成功购买影票：[华联国际影城平谷店]8月25日 14:30[蚁人2：黄蜂女现身]七号厅，3排5座，3排6座。请使用票号[989548]验证码[517788]到影院内自助取票机或影院前台 取票";

//		sendSmsNew(SMSType.System, "13311213117", msg, false);
        sendHonglianSms("18515652725", msg);
//		newSendChuangRuiSms(SMSType.System, "13311213117", msg, false,true);


    }

}
