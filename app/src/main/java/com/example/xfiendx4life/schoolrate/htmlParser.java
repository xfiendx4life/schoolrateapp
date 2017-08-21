package com.example.xfiendx4life.schoolrate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


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

    public static SchoolCardData cardDataGetter(String url) throws IOException {
        SchoolCardData cardData = new SchoolCardData();
        Document doc = Jsoup.connect(url).get();
       // Element name = doc.select("h1.schoolname").first(); //разобраться с именем
        Element imageDiv = doc.select("div.school-logo").first();
        Element image = imageDiv.children().first();// получаем первый img из дива, после этого нужно по аттрибуту получать адрес
        Element bio = doc.getElementById("desc_here");
        Element rating = doc.select("div.score").first();
        Elements prices = doc.select("ul.av-price>li>div.value");

        Elements pricesNames = doc.select("ul.av-price>li>div.title");

        //cardData.name = name.text();
        cardData.bio = bio.text();
        cardData.rating = Integer.parseInt(rating.text());
        cardData.picLink = image.attr("src");
        Hashtable<String, Integer> pricess = new Hashtable<String, Integer>();
        for (int i = 0; i < pricesNames.size(); i++) {
            pricess.put(pricesNames.get(i).text(), Integer.parseInt(prices.get(i).text()));
        }
        cardData.prices = pricess;

        return cardData;
    }


    }
