package cn.parkmanasys.util.ocr;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author linlangleo
 * @desc 图片识别的token获取类
 * @date 2018-05-09 0:13
 **/
public class OrcToken {
    //设置APPID/AK/SK
    public static final String APP_ID = "10839366";
    public static final String API_KEY = "0L26q7r2OATel4LgMjkgyRiT";
    public static final String SECRET_KEY = "NOtniNWETXL3RLVeZYCR56oxmEiOOCMl ";
    public static String TOKEN = null;

    public static String getToken() {
        if (TOKEN == null || TOKEN.trim().equals("")) {
            // 获取token地址
            String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
            String getAccessTokenUrl = authHost
                    // 1. grant_type为固定参数
                    + "grant_type=client_credentials"
                    // 2. 官网获取的 API Key
                    + "&client_id=" + API_KEY
                    // 3. 官网获取的 Secret Key
                    + "&client_secret=" + SECRET_KEY;
            try {
                URL realUrl = new URL(getAccessTokenUrl);
                // 打开和URL之间的连接
                HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                // 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.err.println(key + "--->" + map.get(key));
                }
                // 定义 BufferedReader输入流来读取URL的响应
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String result = "";
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                /**
                 * 返回结果示例
                 */
                System.err.println("result:" + result);
                JSONObject jsonObject = new JSONObject(result);
                String access_token = jsonObject.getString("access_token");
                TOKEN = access_token;
            } catch (Exception e) {
                System.err.printf("获取token失败！");
                e.printStackTrace(System.err);
            }
        }
        return TOKEN;
    }
}
