package com.example.demo.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Parser {
    String Url;
    Document Content;

    public Parser(String url){
         this.Url=url;
         this.Get_Content();
    }

    private void Get_Content() {
        Document parser;

        try {
            parser = Jsoup.connect(this.Url).get();
        } catch (IOException ie) {
            ie.printStackTrace();
            parser = null;
        }
        if (parser != null) {
            this.Content=parser;
        }
    }

    public void Parse(){};
}
