package com.example.gen_medicine.USER;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gen_medicine.DATABASE.DocList;
import com.example.gen_medicine.DATABASE.MyAdaptor;
import com.example.gen_medicine.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultFragment extends Fragment {
    RecyclerView recyclerView;

    MyAdaptor adaptor;
    EditText loc;
    ArrayList<DocList>list;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public ConsultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultFragment newInstance(String param1, String param2) {
        ConsultFragment fragment = new ConsultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_consult, container, false);
        recyclerView=root.findViewById(R.id.recycle);
        list=new ArrayList<>();
        adaptor=new MyAdaptor(requireContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adaptor);
        firebaseDatabase=FirebaseDatabase.getInstance();
        loc=root.findViewById(R.id.loc);
        databaseReference=firebaseDatabase.getReference("doctor");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot i : snapshot.getChildren())
                {
                    DocList dl=new DocList();
                    dl.setDname(i.child("Name").getValue(String.class));
                    dl.setHospital(i.child("Hospital").getValue(String.class));
                    dl.setPhone(i.child("Phone").getValue(String.class));
                    list.add(dl);

                }
                adaptor.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
            }
        });
        return root;
    }
    void search(String s)
    {
        ArrayList<DocList> dc=new ArrayList<>();
        for(DocList i : list)
        {
            if(i.getHospital().toString().toLowerCase().contains(s.toLowerCase()))
            {
                dc.add(i);
            }
        }
        adaptor.setFilter(dc);
    }
}