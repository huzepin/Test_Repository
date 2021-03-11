package cn.com.hzp.selfsale.selfsale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication // 使用到了properties 文件
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})  // 不用配置文件
public class SelfsaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfsaleApplication.class, args);
    }

}
