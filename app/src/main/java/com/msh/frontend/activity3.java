package com.msh.frontend;




import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class activity3 extends AppCompatActivity {


    RecyclerView recyclerView;

    Query query1;
    private DatabaseReference mdatabasereference;
    private ProgressDialog progressDialog;
    FirebaseRecyclerAdapter<Users, BlogViewHolder> firebaseRecyclerAdapter;
    LinearLayoutManager mLayoutManager;
    String phone_no;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);


        progressDialog = new ProgressDialog(activity3.this);
        progressDialog.setMessage("Loading Products Please Wait...");
        progressDialog.show();

        mdatabasereference = FirebaseDatabase.getInstance().getReference("users");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGirdView);



    }

    @Override
    protected void onStart() {
        super.onStart();
        query1 = FirebaseDatabase.getInstance().getReference("users");
        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(query1, Users.class)
                        .build();

        Log.d("Options"," data : "+options);



        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, activity3.BlogViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull activity3.BlogViewHolder blogViewHolder, @SuppressLint("RecyclerView") final int i, @NonNull Users product_get_set_v)  {

                blogViewHolder.setName(product_get_set_v.getFirstName());
                blogViewHolder.setPhoneNo(product_get_set_v.getPhoneNumber());
                blogViewHolder.setEmailId(product_get_set_v.getGmail());
                blogViewHolder.setAge(product_get_set_v.getAge());






                blogViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String productid=getRef(i).getKey();
                        Log.d("productid"," data : "+productid);


                        mdatabasereference.child(productid).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            }





                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                });

            }

            @NonNull
            @Override
            public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.customuser, parent, false);
                progressDialog.dismiss();
                return new BlogViewHolder(view);
            }
        };
        firebaseRecyclerAdapter.startListening();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;



        }
        public void setName(String name)
        {
            TextView ename=(TextView)mView.findViewById(R.id.text1);
            ename.setText("User Name"+":"+" " +name);

        }

        public void setPhoneNo(String s)
        {
            TextView ename=(TextView)mView.findViewById(R.id.text2);
            ename.setText("Phone No."+":"+" "+s);

        }

        public void setEmailId(String s)
        {
            TextView ename=(TextView)mView.findViewById(R.id.text3);
            ename.setText("Email Id"+":"+" "+s);

        }

        public void setAge(String s)
        {
            TextView ename=(TextView)mView.findViewById(R.id.text4);
            ename.setText("Age"+":"+" "+s);

        }




    }

}