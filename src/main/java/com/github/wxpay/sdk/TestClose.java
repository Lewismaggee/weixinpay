package com.github.wxpay.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据订单号关闭订单
 */
public class TestClose {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        Map map = new HashMap();
        map.put("appid",config.getAppID());
        map.put("mch_id",config.getMchID());
        map.put("out_trade_no","13");
        map.put("nonce_str",WXPayUtil.generateNonceStr());

        String signedXml = WXPayUtil.generateSignedXml(map,config.getKey());
        System.out.println("参数: "+signedXml);

        WXPayRequest request = new WXPayRequest(config);
        String xmlResult = request.requestWithCert("/pay/closeorder", null, signedXml, false);
        System.out.println("结果: "+xmlResult);
        Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);
        String tradeState = mapResult.get("trade_state");
        System.out.println(tradeState);
    }
}
