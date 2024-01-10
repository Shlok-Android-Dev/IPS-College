package com.example.ipscollege.ui.faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ipscollege.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {
    private RecyclerView ComputerScience, Accounts, Management, HeadOfDepartment, Staff, Lecturer, TecAndMan, Principal ;
    private LinearLayout csNoData, accNoData, manNoData, hodNoData, staffNoData, lecturerNoData, tecManNoData, principalNoData;
    private List<TeacherData> list1,list2,list3,list4,list5,list6,list7,list8;
    private DatabaseReference referencer, dbRef;
    private  TeacherAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_faculty, container,false);

        ComputerScience = view.findViewById(R.id.ComputerScience);
        Accounts = view.findViewById(R.id.Accounts);
        Management = view.findViewById(R.id.Management);
        HeadOfDepartment = view.findViewById(R.id.HeadOfDepartment);
        Staff = view.findViewById(R.id.Staff);
        Lecturer =  view.findViewById(R.id.Lecturer);
        TecAndMan = view.findViewById(R.id.TecAndMan);
        Principal = view.findViewById(R.id.Principal);

        csNoData = view.findViewById(R.id.csNoData);
        accNoData = view.findViewById(R.id.accNoData);
        manNoData = view.findViewById(R.id.manNoData);
        hodNoData = view.findViewById(R.id.hodNoData);
        staffNoData = view.findViewById(R.id.staffNoData);
        lecturerNoData = view.findViewById(R.id.lecturerNoData);
        tecManNoData = view.findViewById(R.id.tecManNoData);
        principalNoData = view.findViewById(R.id.principalNoData);

        referencer = FirebaseDatabase.getInstance().getReference().child("Faculty info");

        FirebaseApp.initializeApp(getContext());

        ComputerScience();
        Accounts();
        Management();
        HeadOfDepartment();
        Staff();
        Lecturer();
        TecAndMan();
        Principal();


        return view;

    }

    private void ComputerScience() {

        dbRef = referencer.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    ComputerScience.setVisibility(View.GONE);
                }else {
                    csNoData.setVisibility(View.GONE);
                    ComputerScience.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    ComputerScience.setHasFixedSize(true);
                    ComputerScience.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1, getContext());
                    ComputerScience.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void Accounts() {
        dbRef = referencer.child("Accounts");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if (!snapshot.exists()){
                    accNoData.setVisibility(View.VISIBLE);
                    Accounts.setVisibility(View.GONE);
                }else {
                    accNoData.setVisibility(View.GONE);
                    Accounts.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    Accounts.setHasFixedSize(true);
                    Accounts.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2, getContext());
                    Accounts.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Management() {
        dbRef = referencer.child("Management");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if (!snapshot.exists()){
                    manNoData.setVisibility(View.VISIBLE);
                    Management.setVisibility(View.GONE);
                }else {
                    manNoData.setVisibility(View.GONE);
                    ComputerScience.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    Management.setHasFixedSize(true);
                    Management.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3, getContext() );
                    Management.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void HeadOfDepartment() {
        dbRef = referencer.child("Head of Department");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if (!snapshot.exists()){
                    hodNoData.setVisibility(View.VISIBLE);
                    HeadOfDepartment.setVisibility(View.GONE);
                }else {
                    hodNoData.setVisibility(View.GONE);
                    HeadOfDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    HeadOfDepartment.setHasFixedSize(true);
                    HeadOfDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4, getContext());
                    HeadOfDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Staff(){
        dbRef = referencer.child("Staff");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5 = new ArrayList<>();
                if (!snapshot.exists()){
                    staffNoData.setVisibility(View.VISIBLE);
                    Staff.setVisibility(View.GONE);
                }else {
                    staffNoData.setVisibility(View.GONE);
                    Staff.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    Staff.setHasFixedSize(true);
                    Staff.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list5, getContext() );
                    Staff.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Lecturer() {
        dbRef = referencer.child("Lecturer");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list6 = new ArrayList<>();
                if (!snapshot.exists()){
                    lecturerNoData.setVisibility(View.VISIBLE);
                    Lecturer.setVisibility(View.GONE);
                }else {
                    lecturerNoData.setVisibility(View.GONE);
                    Lecturer.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    Lecturer.setHasFixedSize(true);
                    Lecturer.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list6, getContext() );
                    Lecturer.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void TecAndMan() {
        dbRef = referencer.child("TECHNOLOGY AND MANAGEMENT");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list7 = new ArrayList<>();
                if (!snapshot.exists()){
                    tecManNoData.setVisibility(View.VISIBLE);
                    TecAndMan.setVisibility(View.GONE);
                }else {
                    tecManNoData.setVisibility(View.GONE);
                    TecAndMan.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    TecAndMan.setHasFixedSize(true);
                    TecAndMan.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list7, getContext());
                    TecAndMan.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Principal() {
        dbRef = referencer.child("Principal");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list8 = new ArrayList<>();
                if (!snapshot.exists()){
                    principalNoData.setVisibility(View.VISIBLE);
                    Principal.setVisibility(View.GONE);
                }else {
                    principalNoData.setVisibility(View.GONE);
                    Principal.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list8.add(data);
                    }
                    Principal.setHasFixedSize(true);
                    Principal.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list8, getContext() );
                    Principal.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}