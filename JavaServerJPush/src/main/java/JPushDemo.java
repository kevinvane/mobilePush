import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by led on 2017/10/20.
 */
public class JPushDemo {


    protected static final Logger logger = LoggerFactory.getLogger(JPushDemo.class);

    // demo App defined in resources/jpush-api.conf
    //test
    protected static final String APP_KEY ="df792d093f50b3a9ba0f8d43";
    protected static final String MASTER_SECRET = "8ac41bf861e765d840a80c59";
    protected static final String GROUP_PUSH_KEY = "2c88a01e073a0fe4fc7b167c";
    protected static final String GROUP_MASTER_SECRET = "b11314807507e2bcfdeebe2e";

    public static final String TITLE = "Test from API example";
    public static final String ALERT = "啦啦啦-samwen";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";
    public static long sendCount = 0;
    private static long sendTotalTime = 0;


    public static void main(String[] args){

        //接口文档 https://docs.jiguang.cn/jpush/server/sdk/java_sdk/
        //示例代码 https://github.com/jpush/jpush-api-java-client/blob/master/example/main/java/cn/jpush/api/examples/PushExample.java
        List list = new ArrayList<String>();
        list.add("root");
        pushPlatformAllNotification(list,"小乐！","你有一条新的语音消息");
    }

    public static void pushPlatformAllMessage(List<String> alias, String msg) {

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg)
                        .addExtra("from", "阿里云server")
                        .build())
                .build();
        pushPaylod(payload);
    }

    public static void pushPlatformAllNotification(List<String> alias, String title ,String msg) {

        Map<String, String> extras = new HashMap<String, String>();
        extras.put("my_url", "http://wx.rayshine.cc");
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(
                        Notification.newBuilder()
                                .setAlert(msg)
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder()
                                                .setTitle(title + "--Android")
                                                .addExtras(extras).build())
                                .addPlatformNotification(
                                        IosNotification.newBuilder()
                                                .incrBadge(1)
                                                .setAlert(title + "--iOS")
                                                .addExtra("extra_key", "extra_value").build())
                                .build())
                .build();
        pushPaylod(payload);
    }

    private static void pushPaylod(PushPayload payload){

        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        // For push, all you need do is to build PushPayload object.
        //PushPayload payload = buildPushObject_all_all_alert(data);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.error("Should review the error, and fix the request", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
    }



    public static PushPayload buildPushObject_all_all_alert(String data) {
        return PushPayload.alertAll(data);
    }
}
