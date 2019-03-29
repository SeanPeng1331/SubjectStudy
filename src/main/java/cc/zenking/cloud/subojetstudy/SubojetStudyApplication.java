package cc.zenking.cloud.subojetstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@ComponentScan({ "cc.zenking.cloud.redis", "cc.zenking.cloud.subojetstudy" })
public class SubojetStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubojetStudyApplication.class, args);
	}
}
