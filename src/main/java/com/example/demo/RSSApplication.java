package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RSSApplication {

	public static void main(String[] args) {

		SpringApplication.run(RSSApplication.class, args);

		/*String link = "https://sfedu.ru/press-center/newspage/1";
		var sp = new SfeduParser(link);
		System.out.println(sp.Report());*/
	}
}
