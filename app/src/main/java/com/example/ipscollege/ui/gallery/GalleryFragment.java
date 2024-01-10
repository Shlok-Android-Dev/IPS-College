package com.example.ipscollege.ui.gallery;

import static com.example.ipscollege.R.id.placementsRecycler;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ipscollege.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    RecyclerView eventsRecycler, placements, festivalRecycler, sportsRecycler, othersRecycler;
    private ProgressBar pgbar,pgbar1,pgbar2,pgbar3,pgbar4;
    GalleryAdapter adapter;

    DatabaseReference reference,DbRef;
    private ArrayList<GalleryData> listCT1,listCT2,listCT3,listCT4,listCT5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);

        eventsRecycler = view.findViewById(R.id.EventsRecycler);
        placements = view.findViewById(placementsRecycler);
        festivalRecycler = view.findViewById(R.id.festivalRecycler);
        sportsRecycler = view.findViewById(R.id.sportsRecycler);
        othersRecycler = view.findViewById(R.id.OtherRecycler);


        pgbar= view.findViewById(R.id.pgbar0);
        pgbar1 = view.findViewById(R.id.pgbar1);
        pgbar2 = view.findViewById(R.id.pgbar2);
        pgbar3 = view.findViewById(R.id.pgbar3);
        pgbar4 = view.findViewById(R.id.pgbar4);


        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getEventImage();
        getPlacementsImage();
        getFestivalImage();
        getSportImage();
        getOtherImage();


        return view;
    }

    private void getEventImage() {

        reference.child("Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String data  =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                eventsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                eventsRecycler.setAdapter(adapter);
                pgbar.setVisibility(View.GONE);
                eventsRecycler.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"fetching error:"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getPlacementsImage() {


        reference.child("Placements").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String data  =(String) snapshot.getValue();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                placements.setLayoutManager(new GridLayoutManager(getContext(), 3));
                placements.setAdapter(adapter);
                pgbar1.setVisibility(View.GONE);
                placements.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "fetching error:"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getFestivalImage() {


        reference.child("Festivals").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String data  =(String) snapshot.getValue();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                festivalRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                festivalRecycler.setAdapter(adapter);
                pgbar2.setVisibility(View.GONE);
                festivalRecycler.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"fetching error:"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getSportImage() {

        reference.child("Sports").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String data  =(String) snapshot.getValue();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                sportsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                sportsRecycler.setAdapter(adapter);
                pgbar3.setVisibility(View.GONE);
                sportsRecycler.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "fetching error:"+error, Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getOtherImage() {


        reference.child("Other Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String data  =(String) snapshot.getValue();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                othersRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                othersRecycler.setAdapter(adapter);
                pgbar4.setVisibility(View.GONE);
                othersRecycler.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "fetching error:"+error, Toast.LENGTH_SHORT).show();
            }
        });


    }

}