package com.example.demo;

import com.example.demo.parsers.SfeduParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RSSController {

    @GetMapping("/sfedu")
    public String sfedu() {
        String link = "https://sfedu.ru/press-center/newspage/1";
        var sp = new SfeduParser(link);
		return sp.Report();
    }
}
