package com.example.doctor.searchwidget;

/**
 * Created by Doctor on 5/9/2016.
 */
public class dictionarysample {

        public String vocab;
        public String meaning;
        public int favorite;

    public dictionarysample(){}

    public dictionarysample(String vocab,String meaning,int favorite){
        this.vocab=vocab;
        this.meaning=meaning;
        this.favorite=favorite;
    }

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}

