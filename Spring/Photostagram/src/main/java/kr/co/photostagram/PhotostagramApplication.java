package kr.co.photostagram;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("kr.co.photostagram.dao")
@SpringBootApplication
public class PhotostagramApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotostagramApplication.class, args);
	}

}
