package com.example.myapp;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Hero {

    private String name;

    @SerializedName("imageurl")
    private String imageUrl;

    @SerializedName("bio")
    private String info;

    public Hero(String name, String imageUrl, String info) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.info = info;
    }

    public static Comparator<Hero> heroNameAZComparator = new Comparator<Hero>() {
        @Override
        public int compare(Hero h1, Hero h2) {
            return h1.getName().compareTo(h2.getName());
        }
    };

    public static Comparator<Hero> heroNameZAComparator = new Comparator<Hero>() {
        @Override
        public int compare(Hero h1, Hero h2) {
            return h2.getName().compareTo(h1.getName());
        }
    };

    public static Comparator<Hero> heroNameInfoASCComparator = new Comparator<Hero>() {
        @Override
        public int compare(Hero h1, Hero h2) {
            return h1.getInfo().length() - h2.getInfo().length();
        }
    };

    public static Comparator<Hero> heroNameInfoDECComparator = new Comparator<Hero>() {
        @Override
        public int compare(Hero h1, Hero h2) {
            return h2.getInfo().length() - h1.getInfo().length();
        }
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
