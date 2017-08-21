package com.example.xfiendx4life.schoolrate;

import java.io.Serializable;
import java.util.Hashtable;


class SchoolCardData implements Serializable {
    String name;
    String bio; //описание
    int rating;
    String picLink;
    Hashtable <String, Integer> prices;// цены добавляем только те, что есть на сайте

}
