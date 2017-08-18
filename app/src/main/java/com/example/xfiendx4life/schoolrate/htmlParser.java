package com.example.xfiendx4life.schoolrate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


class htmlParser {
    static ArrayList<School> Rate() throws IOException {
        ArrayList<School> schools = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://www.schoolrate.ru/").get();

            Elements names = doc.select("a.name");
            Elements score = doc.select("span.points");
            Elements icon = doc.select("img.icon-courses");
            Elements price = doc.select("span.price");


            for (int i = 0; i < 31; i++) {
                School currentSchool = new School();
                currentSchool.schoolName = names.get(i).text();
                currentSchool.score = Integer.parseInt(score.get(i).text());
                currentSchool.pictureLink = icon.get(i).attr("src");
                currentSchool.lessonPrice = Integer.parseInt(price.get(i).text());
                currentSchool.schoolCardLink = names.get(i).attr("href");
                schools.add(currentSchool);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schools;
    }


    }
