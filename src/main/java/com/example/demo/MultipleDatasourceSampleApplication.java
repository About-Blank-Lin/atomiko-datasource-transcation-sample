package com.example.demo;

import com.example.demo.Bean.Db1Database;
import com.example.demo.Bean.Db2Database;
import com.example.demo.Bean.Db3Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({Db1Database.class, Db2Database.class, Db3Database.class})
public class MultipleDatasourceSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatasourceSampleApplication.class, args);

	}

}
