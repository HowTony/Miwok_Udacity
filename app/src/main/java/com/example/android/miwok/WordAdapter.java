package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;



/**
 * Created by Tony Howarth on 1/27/2017.
 */

public class WordAdapter extends ArrayAdapter {

    private int mBackground;
    private MediaPlayer mMediaPlayer;


    public WordAdapter(Context context, ArrayList<Word>resource, int color) {
        super(context, 0, resource);
        mBackground = color;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the object located at this position in the list
        Word currentWordObject = (Word) getItem(position);
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView notTranslatedText = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        notTranslatedText.setText(currentWordObject.getMiwok());
        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentWordObject.getEnglish());
        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image_view);
        //if there should'nt be an icon associated with the view, set view to gone
        if(currentWordObject.hasImage()){
            iconView.setVisibility(View.VISIBLE);
        }else{
            iconView.setVisibility(View.GONE);
        }
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        iconView.setImageResource(currentWordObject.getImageResourceId());

        //Set theme color for list item
        View textContainer = listItemView.findViewById(R.id.linear_layout_vertical);

        //find the color id we passed in
        int color = ContextCompat.getColor(getContext(), mBackground);
        //set the background color for the textViews
        textContainer.setBackgroundColor(color);
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
