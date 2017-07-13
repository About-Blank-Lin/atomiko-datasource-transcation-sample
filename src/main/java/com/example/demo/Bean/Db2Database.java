package com.example.demo.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "db2")
public class Db2Database extends DatabaseInfo {
}
