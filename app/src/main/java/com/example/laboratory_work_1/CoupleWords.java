package com.example.laboratory_work_1;

import android.os.Parcel;
import android.os.Parcelable;

public class CoupleWords implements Parcelable {
    private int wordID;
    private String word;
    private String translation;
    private int countPassed;

    public CoupleWords() {}

    public CoupleWords(String word, String translation) {
        this.word = word;
        this.translation = translation;
        this.countPassed = 0;
    }

    public CoupleWords(int wordID, String word, String translation) {
        this.wordID = wordID;
        this.word = word;
        this.translation = translation;
        this.countPassed = 0;
    }

    public CoupleWords(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        wordID = Integer.valueOf(data[0]);
        word = data[1];
        translation = data[2];
        countPassed = Integer.valueOf(data[3]);
    }

    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getCountPassed() {
        return countPassed;
    }

    public void setCountPassed(int countPassed) {
        this.countPassed = countPassed;
    }

    public static final Parcelable.Creator<CoupleWords> CREATOR = new Parcelable.Creator<CoupleWords>() {

        @Override
        public CoupleWords createFromParcel(Parcel source) {
            return new CoupleWords(source);
        }

        @Override
        public CoupleWords[] newArray(int size) {
            return new CoupleWords[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { String.valueOf(wordID), word, translation, String.valueOf(countPassed) });
    }
}
