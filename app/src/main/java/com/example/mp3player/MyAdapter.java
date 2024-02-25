package com.example.mp3player;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {

    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Tạo TextView
        TextView textView = (TextView) super.getView(position, convertView, parent);

        // Set màu cho text
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(30);

        return textView;
    }

}
