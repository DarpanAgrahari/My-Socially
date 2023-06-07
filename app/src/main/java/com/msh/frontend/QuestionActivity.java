package com.msh.frontend;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class QuestionActivity extends AppCompatActivity {

    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference myRef= database.getReference();


    DatabaseReference reference ;

    FirebaseUser user;
    private String res="";

    Button b1,b2,b3,b4;


    private TextView question, questionNo;
    private LinearLayout optionsContainer;
    private Button nextBtn;
    private int count=0;
    private List<QuestionModel> list;

    private int position=0;
    private String set;
    private int setNo=1;
    //ActivityFragment activityFragment=new ActivityFragment();
    private int total=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        question=findViewById(R.id.textView13);
        questionNo=findViewById(R.id.textView22);
        optionsContainer=findViewById(R.id.linearLayout2);
        nextBtn=findViewById(R.id.button6);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button1);
        b3=findViewById(R.id.button2);
        b4=findViewById(R.id.button3);

        set=getIntent().getStringExtra("sets");



         list= new ArrayList<>();


        myRef.child("Question").child(set).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    list.add(dataSnapshot.getValue(QuestionModel.class));

                }
                if(list.size()>0){
                    for(int i=0;i<4;i++){
                        optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                submitAnswer((Button) view);
                            }
                        });
                    }
                    playAnim(question,0,list.get(position).getQuestion());
                    nextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            nextBtn.setEnabled(false);
                            nextBtn.setAlpha(0.7f);
                            enableOption(true);
                            position++;
                            if( position == list.size()){
                                // next page.
                                //HomeFragment activityFragment=new HomeFragment();
                              //getSupportFragmentManager().beginTransaction().replace(R.id.container, activityFragment).commit();

                               //myRef= database.getReference("users");
                                 String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                Toast.makeText(QuestionActivity.this,user,Toast.LENGTH_SHORT).show();
                                reference = FirebaseDatabase.getInstance().getReference("Response");
                                reference.child(user).child(set).setValue(total);
                                System.out.println(res);
                                Intent intent= new Intent(QuestionActivity.this,Result.class);
                                intent.putExtra("sets",set);
                                startActivity(intent);

                               // Response response = new Response(result);
                                //myRef.child(phoneNo).setValue(response);
                                //Response response= new Response(result);
                                //reference.child(phoneNo).child(set).setValue(response);

                                return;
                            }
                            count=0;
                            playAnim(question,0,list.get(position).getQuestion());

                        }
                    });
                }else{
                    finish();
                    Toast.makeText(QuestionActivity.this,"no question left",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QuestionActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void playAnim(View view,final int value, final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {
                        if(value==0 && count <4){
                            String option="";
                            if(count==0){
                                option= list.get(position).getOptionA();
                            }
                            else if(count==1){
                                option= list.get(position).getOptionB();

                            }
                            else if(count==2){
                                option= list.get(position).getOptionC();
                            }
                            else if(count==3){
                                option= list.get(position).getOptionD();
                            }
                            playAnim(optionsContainer.getChildAt(count),0,option);
                            count++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {

                        if(value==0){
                            try{
                                ((TextView)view).setText(data);
                                questionNo.setText(position+1 +"/" + list.size());
                            }catch(ClassCastException ex){
                                ((Button)view).setText(data);
                            }
                            view.setTag(data);

                            playAnim(view,1,data);
                        }

                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });
    }
    private void submitAnswer(Button selectedOption){
        enableOption(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        int ans = selectedOption.getId();
        res+=ans;
        if(ans == b1.getId()){
            total+=4;
        }
        else if( ans == b2.getId()){
            total+=3;
        }
        else if(ans == b3.getId()){
            total+=2;
        }
        else{
            total+=1;
        }
    }
    private void enableOption(Boolean enable)
    {
        for(int i=0;i<4;i++){
            optionsContainer.getChildAt(i).setEnabled(enable);
            if(enable){
                optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));

            }
        }
    }
    }



