package com.example.xfiendx4life.schoolrate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class htmlParser {
    public static ArrayList<School> Rate() throws IOException {
        ArrayList<School> schools = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://www.schoolrate.ru/").get();

            Elements names = doc.select("a.name");
            Elements score = doc.select("span.points");
            Elements icon = doc.select("img.icon-courses");
            Elements price = doc.select("span.price");


            for (int i = 0; i < 30; i++) {
                School currentSchool = new School();
                currentSchool.schoolName = names.get(i).text();
                currentSchool.score = Integer.parseInt(score.get(i).text());
                currentSchool.pictureLink = icon.get(i).attr("src");
                currentSchool.lessonPrice = Integer.parseInt(price.get(i).text());
                schools.add(currentSchool);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return schools;
    }


    }
