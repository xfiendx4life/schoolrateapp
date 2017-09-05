package com.example.xfiendx4life.schoolrate;

import java.io.Serializable;
import java.util.Hashtable;


class SchoolCardData implements Serializable {
    String name;
    String bio; //описание
    float rating;
    String picLink;
    Hashtable <String, Float> prices;// цены добавляем только те, что есть на сайте

}
