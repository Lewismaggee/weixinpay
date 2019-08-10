package com.github.wxpay.sdk;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        //封装请求参数
        Map<String,String> map = new HashMap();
        map.put("appid",config.getAppID()); //公众账号id
        map.put("mch_id",config.getMchID()); //商户号
        map.put("nonce_str",WXPayUtil.generateNonceStr());//随机字符串
        map.put("body","青橙"); //商品描述
        map.put("out_trade_no","888882"); //订单号
        map.put("total_fee","1"); //金额
        map.put("spbill_create_ip","127.0.0.1"); //终端ip
        map.put("notify_url","http://www.baidu.com");//回调地址
        map.put("trade_type","NATIVE"); //交易类型

        //将map转换成xml
        String xmlParam = WXPayUtil.generateSignedXml(map, config.getKey()); //xml格式的参数

        //发送请求
        WXPayRequest wxPayRequest = new WXPayRequest(config);
        //调用接口返回结果字符串
        String xmlResult = wxPayRequest.requestWithCert("/pay/unifiedorder", null, xmlParam, false);
        System.out.println(xmlResult);
        //解析返回结果

        Map<String, String> resultMap = WXPayUtil.xmlToMap(xmlResult); //将xml解析成map
        String code_url = resultMap.get("code_url");
        System.out.println(code_url);
    }
}
