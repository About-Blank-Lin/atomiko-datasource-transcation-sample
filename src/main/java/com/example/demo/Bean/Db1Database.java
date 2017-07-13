package com.example.demo.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "db1")
public class Db1Database extends DatabaseInfo {

}
