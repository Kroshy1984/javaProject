package com.example.demo;

public class Article {
    String Link;
    String Title;
    String Description;
    String Date;
    String Image;

    public Article (String link, String title, String description,String date, String image)
    {
        this.Link=link;
        this.Title=title;
        this.Description=description;
        this.Date=date;
        this.Image=image;
    }

    public String To_xml(){
        return """
                <item>
                <title>%s</title>
                <link>%s</link>
                <description>%s</description>
                <pubDate>%s</pubDate>
                <image>
                <url>%s</url>
                </image>
                </item>
                """.formatted(this.Title,this.Link,this.Description,this.Date,this.Image);
    }
}
