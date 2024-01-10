package com.example.ipscollege.ui.about;


import android.content.Context;
import static com.example.ipscollege.R.id.videoView;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.ipscollege.R;

public class AboutFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container,false);

        VideoView videoview = (VideoView) view.findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.background);
        videoview.setVideoURI(uri);
        videoview.start();

        return view;
    }
}