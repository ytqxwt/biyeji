package cn.jxustsrw.biyeji.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettySocketConfig {
    @Bean
    public SocketIOServer socketIOServer() {
        /*
         * 创建Socket，并设置监听端口
         */
        com.corundumstudio.socketio.Configuration config=new com.corundumstudio.socketio.Configuration();
        // 设置主机名，默认是0.0.0.0
        config.setHostname("localhost");
        // 设置监听端口
        config.setPort(9000);
        // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(10000);
        // Ping消息间隔（毫秒），默认25000。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(60000);
        // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(180000);
        // 这个版本0.9.0不能处理好namespace和query参数的问题。所以为了做认证必须使用全局默认命名空间
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}
