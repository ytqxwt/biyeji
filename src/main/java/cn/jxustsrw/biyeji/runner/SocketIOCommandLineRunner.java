package cn.jxustsrw.biyeji.runner;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=1)
public class SocketIOCommandLineRunner implements CommandLineRunner {
    private final SocketIOServer server;

    @Autowired
    public SocketIOCommandLineRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        server.start();
        System.out.println("socket.io启动成功！");
    }
}
