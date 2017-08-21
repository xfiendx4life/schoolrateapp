package com.example.xfiendx4life.schoolrate;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Kirill on 21.08.2017.
 */

public class SchoolCardData implements Serializable {
    String bio; //описание
    int rating;
    String picLink;
    Hashtable <String, Integer> prices;// цены добавляем только те, что есть на сайте

}
