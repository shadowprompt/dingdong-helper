import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户信息
 * 通过小程序抓包购物车接口获取headers和body中的数据填入
 */
public class UserConfig {

    //城市
    public static final String cityId = "0101";//默认上海

    //站点id
    public static final String stationId = "5c04bd75716de1403a8b6797";

    //收货地址id
    public static final String addressId = "625968be2bbe330001e4128d";

    // 构造随机的openId
    public static final String randId = UUID.randomUUID().toString().substring(0, 5);
    public static final String fakeOpenId = "osP8I0cMnJvcFssalHGhymedEucc".substring(0, 23) + randId;
    /**
     * 确认收货地址id和站点id
     * 每天抢之前先允许一下此接口 确认登录信息是否有效 如果失效了重新抓一次包
     */
    public static void main(String[] args) {
        Api.checkUserConfig();
    }

    /**
     * 抓包后参考项目中的image/headers.jpeg 把信息一行一行copy到下面 没有的key不需要复制
     */
    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("ddmc-city-number", cityId);
        headers.put("ddmc-time", String.valueOf(new Date().getTime() / 1000));
        headers.put("ddmc-build-version", "2.83.0");
        headers.put("ddmc-station-id", stationId);
        headers.put("ddmc-channel", "applet");
        headers.put("ddmc-os-version", "[object Undefined]");
        headers.put("ddmc-app-client-id", "4");
        headers.put("ddmc-ip", "");
        headers.put("ddmc-api-version", "9.50.0");
        headers.put("accept-encoding", "gzip,compress,br,deflate");
        headers.put("referer", "https://servicewechat.com/wx1e113254eda17715/425/page-frame.html");

        // ------------  填入以下6项 上面不要动 ------------
        headers.put("ddmc-device-id", fakeOpenId);
        headers.put("cookie", "DDXQSESSID=10d966e31314abe7525a46c5dc413df9"); //--
        headers.put("ddmc-longitude", "121.401653");
        headers.put("ddmc-latitude", "31.24744");
        headers.put("ddmc-uid", "625968755c3f5d0001c33b85"); //
        headers.put("user-agent", "");
//        headers.put("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        return headers;
    }

    /**
     * 抓包后参考项目中的image/body.jpeg 把信息一行一行copy到下面 没有的key不需要复制
     * <p>
     * 这里不能加泛型 有些接口是params  泛型必须要求<String,String> 有些是form表单 泛型要求<String,Object> 无法统一
     */
    public static Map getBody(Map<String, String> headers) {
        Map body = new HashMap<>();
        body.put("uid", headers.get("ddmc-uid"));
        body.put("longitude", headers.get("ddmc-longitude"));
        body.put("latitude ", headers.get("ddmc-latitude"));
        body.put("station_id", headers.get("ddmc-station-id"));
        body.put("city_number", headers.get("ddmc-city-number"));
        body.put("api_version", headers.get("ddmc-api-version"));
        body.put("app_version ", headers.get("ddmc-build-version"));
        body.put("applet_source", "");
        body.put("channel", "applet");
        body.put("app_client_id", "4");
        body.put("sharer_uid", "");
        body.put("h5_source", "");
        body.put("time", headers.get("ddmc-time"));

        // ------------  填入这3项上面不要动 ------------
        body.put("s_id", "10d966e31314abe7525a46c5dc413df9"); // ---
        body.put("openid", fakeOpenId);
        body.put("device_token", "WHJMrwNw1k/FKPjcOOgRd+IO+egB0+hZPaHqL9kG5GY91qeyxLgYvk4Xgf7EkU/LRdLOjc0SRVt7fMtHNws1EEQU9Dzwb5+YqdCW1tldyDzmauSxIJm5Txg==1487582755342");
        return body;
    }
}
