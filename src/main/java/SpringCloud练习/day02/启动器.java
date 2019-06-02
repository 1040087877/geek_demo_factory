package SpringCloud练习.day02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class 启动器 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudApplication.class,args);
    }
}
