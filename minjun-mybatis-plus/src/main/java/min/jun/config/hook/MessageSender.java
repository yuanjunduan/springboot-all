package min.jun.config.hook;

import org.springframework.stereotype.Component;

public class MessageSender {

    public static <T> void sendMQMessage(BaseEvent<T> event) {
        // 发送消息到指定的交换机和路由键
        System.out.println("MQ Message sent: " + event);
    }
}