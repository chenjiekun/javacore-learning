package com.cmit.exceltosql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cmit.exceltosql.mapper")
public class ExcelToSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelToSqlApplication.class, args);
	}
}
