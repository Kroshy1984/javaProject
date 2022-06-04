package com.example.demo.parsers;

import com.example.demo.Article;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SfeduParser extends Parser{
    ArrayList<Article> articles;

    public SfeduParser(String url){
        super(url);
        articles=new ArrayList<Article>();
        this.Parse();
    }

    @Override
    public void Parse(){

        Elements news= this.Content.select("div.act");

        for (var i:news) {
            Element news_titles= i.select("div.acttitle").first();
            Element news_description= i.select("div.acttext").first();
            Element news_date=i.select("div.actdate").first();
            Element news_image = i.select("img").first();

            Article a=new Article("https://sfedu.ru" + news_titles.select("a[href]").attr("href"),
                    news_titles.text(),
                    news_description.text(),
                    this.Format_date(news_date.text()),
                    "sfedu.ru" + news_image.attr("src"));

            this.articles.add(a);
        }
    }

    private String Format_date(String str){
        LocalDateTime news_date;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);
        String timezone = TimeZone.getDefault().getDisplayName(false,TimeZone.SHORT);

        if (str.equals("Сегодня")){
            news_date = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),0,0,0);
        }
        else  if (str.equals("Вчера")){
            news_date = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth()-1,0,0,0);
        }
        else{
            String[] tuple = str.split(" ");
            int days=Integer.parseInt(tuple[0]);
            int month=0;
            switch (tuple[1]){
                case ("января"):
                    month=1;
                    break;
                case ("февраля"):
                    month=2;
                    break;
                case ("марта"):
                    month=3;
                    break;
                case ("апреля"):
                    month=4;
                    break;
                case ("мая"):
                    month=5;
                    break;
                case ("июня"):
                    month=6;
                    break;
                case ("июля"):
                    month=7;
                    break;
                case ("августа"):
                    month=8;
                    break;
                case ("сентября"):
                    month=9;
                    break;
                case ("октября"):
                    month=10;
                    break;
                case ("ноября"):
                    month=11;
                    break;
                case ("декабря"):
                    month=12;
                    break;
            }
            int year= now.getYear();
            if ((month >= now.getMonth().ordinal()+1) && (days >now.getDayOfMonth())){ year-=1; }

            news_date=LocalDateTime.of(year,month,days,0,0,0);
        }
        return news_date.format(formatter).toString()+" "+timezone;
    }

    public static String Date_now(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);
        String timezone = TimeZone.getDefault().getDisplayName(false,TimeZone.SHORT);
        return now.format(formatter).toString()+" "+timezone;
    }

    public String Report() {
        String res= """
                <?xml version="1.0" encoding="utf-8"?>
                <rss version="2.0">
                <channel>
                <title>Sfedu News</title>
                <link>https://sfedu.ru/press-center/news</link>
                <description>Sfedu Press Center Feed</description>
                <language>ru-ru</language>
                <pubDate>%s</pubDate>
                <lastBuildDate>%s</lastBuildDate>
                """.formatted(SfeduParser.Date_now(),SfeduParser.Date_now());
        for (Article a: articles){
            res+=a.To_xml();
        }
        res+= """
                </channel>
                </rss>""";
        return res;
    }
}
