
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private ArrayList<Word> mWords = new ArrayList<>();
    private MediaPlayer mMedia;

    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMedia.pause();
                        mMedia.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMedia.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        mWords.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        mWords.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        mWords.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        mWords.add(new Word("four", "oyysia", R.drawable.number_four, R.raw.number_four));
        mWords.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        mWords.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        mWords.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        mWords.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        mWords.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        mWords.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, mWords, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = mWords.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMedia = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mMedia.start();
                    mMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });
    }

    private void releaseMediaPlayer(){
        if(mMedia != null) {
            mMedia.release();
            mMedia = null;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("NumbersActivity", "onDestroy");
        releaseMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("NumbersActivity", "onDestroy");
        releaseMediaPlayer();
    }
}
