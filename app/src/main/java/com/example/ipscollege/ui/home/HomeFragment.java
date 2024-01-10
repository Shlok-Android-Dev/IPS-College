package com.example.ipscollege.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ipscollege.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

        private ViewPager viewPager;
        private BranchAdapter adapter;
        private List<BranchModel> list;
        private ImageSlider imageSlider;
        private ImageView map;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_home, container, false);

            // Use view to find the views within the fragment's lay
             imageSlider = view.findViewById(R.id.imageSlider);
             map = view.findViewById(R.id.map);

            ArrayList<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.bcagroup, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.bcagroup1, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.group, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.group1, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.saraswati, ScaleTypes.FIT));

            imageSlider.setImageList(slideModels, ScaleTypes.FIT);

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMap();
                }
            });



            list = new ArrayList<>();
            list.add(new BranchModel(R.drawable.ic_mba,"Master of Business Administration (MBA)","MBAs prepare students for senior management or entrepreneurial roles by providing a holistic view of business  including accounting, finances, marketing, and human resources."));
            list.add(new BranchModel(R.drawable.ic_bba,"Bachelor of Business Administration (BBA)","A BBA is a three-year undergraduate degree that focuses on business administration and management. The degree provides students with a broad understanding of businesssettings."));
            list.add(new BranchModel(R.drawable.ic_cs,"Bachelor of Computer Application (BCA)","BCA is a three-year undergraduate degree program in India. It focuses on computer applications and software development.IT field Students can get job opportunities in the IT sector."));
            list.add(new BranchModel(R.drawable.ic_bcom,"Bachelor of Commerce (bcom)","The Bachelor of Commerce degree is designed to provide students with a wide range of managerial skills, while building competence in a particular area of business; see aside listing."));

            adapter = new BranchAdapter(getContext(), list);

            viewPager = view.findViewById(R.id.viewPagerid);

            viewPager.setAdapter(adapter);

            return view;
        }

        private void openMap() {

            Uri uri  = Uri.parse("geo:0, 0?q=IPS BUSINESS SCHOOL, jaipur ");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        }
    }