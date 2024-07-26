package com.fullstack.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 애플리케이션의 메인 클래스입니다.
 * <p>
 * 이 클래스는 애플리케이션의 시작점을 정의하고, Spring Boot 애플리케이션 컨텍스트를 초기화합니다.
 * </p>
 * 
 * @author sh00n
 * @version 1.0_0725
 */
@SpringBootApplication // 이 어노테이션은 @Configuration, @EnableAutoConfiguration, @ComponentScan을 포함하여
												// Spring Boot 애플리케이션을 구성합니다.
public class ProjectApplication {

	/**
	 * 애플리케이션의 진입점입니다.
	 * <p>
	 * 이 메서드는 SpringApplication.run()을 호출하여 Spring Boot 애플리케이션을 시작합니다.
	 * </p>
	 * 
	 * @param args 커맨드라인 인자
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args); // SpringApplication을 실행하여 Spring Boot 애플리케이션을 시작합니다.
	}
}
