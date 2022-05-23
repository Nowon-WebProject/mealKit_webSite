package kr.co.EZHOME.domain;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SendMessage {
 
        /*
         * 서버에서 받은 API_KEY, API_SECRET를 입력해주세요.
         */
        
        public void sendMessage(String phone) {
        	String api_key = "";
            String api_secret = "";
            Message coolsms = new Message(api_key, api_secret);
            String[] phoneSplit = phone.split("-");
            phone = phoneSplit[0] + phoneSplit[1] + phoneSplit[2];
            // 4 params(to, from, type, text) are mandatory. must be filled
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("to", "01039333062");
            params.put("from", phone);
            params.put("type", "SMS");
            params.put("text", "[112233] 이젠, 집에서 인증번호를 입력해주세요.");
            params.put("app_version", "test app 1.2"); // application name and version

            try {
              JSONObject obj = (JSONObject) coolsms.send(params);
              System.out.println(obj.toString());
            } catch (CoolsmsException e) {
              System.out.println(e.getMessage());
              System.out.println(e.getCode());
            }
          }
}
