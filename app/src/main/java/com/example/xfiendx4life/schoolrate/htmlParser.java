package com.example.xfiendx4life.schoolrate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
            Elements position = doc.select("span.position");

            //найти макс элемент в позишн
            int[] pos = new int[position.size()];
            for(int j = 0;j < position.size();j++) {
                pos[j] = Integer.valueOf(position.get(j).text());
            }
            Arrays.sort(pos);
            int num = pos[position.size()-1];


            for (int i = 0; i < num; i++) {
                School currentSchool = new School();
                currentSchool.schoolName = names.get(i).text();
                currentSchool.score = Float.parseFloat(score.get(i).text());
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

    static SchoolCardData cardDataGetter(String url) throws IOException {
        SchoolCardData cardData = new SchoolCardData();
        Document doc = Jsoup.connect(url).get();
        Element name = doc.select("h1#schoolname").first(); //разобраться с именем
        Element imageDiv = doc.select("div.school-logo").first();
        Element image = imageDiv.children().first();// получаем первый img из дива, после этого нужно по аттрибуту получать адрес
        Element bio = doc.getElementById("desc_here");
        Element rating = doc.select("div.score").first();
        Elements prices = doc.select("ul.av-price>li>div.value");

        Elements pricesNames = doc.select("ul.av-price>li>div.title");

        cardData.name = name.text();
        cardData.bio = bio.text();
        cardData.rating = Float.parseFloat(rating.text());
        cardData.picLink = image.attr("src");
        Hashtable<String, Float> pricess = new Hashtable<>();
        for (int i = 0; i < pricesNames.size(); i++) {
            try {
                pricess.put(pricesNames.get(i).text(), Float.parseFloat(prices.get(i).text()));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        cardData.prices = pricess;

        return cardData;
    }
    static ArrayList<School>  FullSchoolList (String url) throws IOException {
        ArrayList<School> schools = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements names = doc.select("div.col1>a");
        Elements prices = doc.select("div.col2");
        Elements houses = doc.select("div.col3");
        Elements year = doc.select("div.col5");
        //получить данные из алфавитного спискаЫ
        School newSchool = new School();
        int i = 1;
        for(Element e: names ){
            newSchool.schoolName = e.text();
            newSchool.schoolCardLink = e.attr("href");

            try {
                newSchool.numberOfSchools = Integer.valueOf(houses.get(i).text());
                newSchool.lessonPrice = Integer.valueOf(prices.get(i).text());
            }
            catch (Exception ex) {
                newSchool.lessonPrice = 0;
            }
            newSchool.yearOfBirth = year.get(i).text();
            schools.add(newSchool);
            i++;
        }



        return schools;
    }


    }
