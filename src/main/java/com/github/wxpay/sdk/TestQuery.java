package com.github.wxpay.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付查询订单接口调用
 */
public class TestQuery {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        Map map = new HashMap();
        map.put("appid",config.getAppID());
        map.put("mch_id",config.getMchID());

        //1159373104884092928  trade_state: NOTPAY   trade_state_desc: 订单未支付
        //1159457451251208192  trade_state: SUCCESS  trade_state_desc: 支付成功
        map.put("out_trade_no","1028624753851629569");
        map.put("nonce_str",WXPayUtil.generateNonceStr());

        String signedXml = WXPayUtil.generateSignedXml(map,config.getKey());
        System.out.println("参数: "+signedXml);

        WXPayRequest request = new WXPayRequest(config);
        String xmlResult = request.requestWithCert("/pay/orderquery", null, signedXml, false);
        System.out.println("结果: "+xmlResult);
        Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);
        String tradeState = mapResult.get("trade_state_desc");
        System.out.println(tradeState);
    }
}
