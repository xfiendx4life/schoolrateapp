package com.example.xfiendx4life.schoolrate;

import java.io.Serializable;
import java.util.Hashtable;


class SchoolCardData implements Serializable {
    String name;
    String bio; //описание
    float rating;
    String picLink;
    Hashtable <String, Float> prices;// цены добавляем только те, что есть на сайте

    int maxStudents = 0;
    boolean licence = false;
    boolean monthlyPayment = false;
    int numberOfSchools = 0;
    boolean engForChildren = false;
    boolean firstFree = false;

}
