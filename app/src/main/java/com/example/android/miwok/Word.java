package com.example.android.miwok;

/**
 * Created by Tony Howarth on 1/27/2017.
 */

public class Word {

    private String mEnglish;
    private String mMiwok;
    private static final int NO_IMAGE_PROVIDED = 0;
    private int mImageId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId = NO_IMAGE_PROVIDED;

    public Word(String eng, String miwok, int audioResourceID){
        mEnglish = eng;
        mMiwok = miwok;
        mAudioResourceId = audioResourceID;
    }

    public Word(String eng, String miwok, int imageResourceID, int audioResourceID){
        mEnglish = eng;
        mMiwok = miwok;
        mImageId = imageResourceID;
        mAudioResourceId = audioResourceID;
    }

    public String getEnglish() {
        return mEnglish;
    }

    public String getMiwok() {
        return mMiwok;
    }

    public int getImageResourceId(){
        return mImageId;
    }

    public boolean hasImage(){
        return mImageId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }
}
