
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Word> mWords = new ArrayList<>();
    private MediaPlayer mMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mWords.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        mWords.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        mWords.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        mWords.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        mWords.add(new Word("black", "kululli\"", R.drawable.color_black, R.raw.color_black));
        mWords.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        mWords.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        mWords.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this, mWords, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = mWords.get(position);
                releaseMediaPlayer();
                mMedia = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                mMedia.start();
                mMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
            }
        });
    }

    private void releaseMediaPlayer(){
        if(mMedia != null) {
            mMedia.release();
            mMedia = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("ColorActivity", "onDestroy");
        releaseMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("ColorActivity", "onDestroy");
        releaseMediaPlayer();
    }
}











