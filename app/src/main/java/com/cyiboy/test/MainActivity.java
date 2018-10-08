package com.cyiboy.test;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    int score = 0;
    int questionNumber = 1;
    ListView lv;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button summit,next,back;

    FirebaseListAdapter adapter;
    Bundle bundle;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.ativity_main);
            bundle = getIntent().getExtras();
            String select = bundle.getString("selected");
            setTitle(select);


            lv = findViewById(R.id.list);
            firebaseStuff();
            summit = findViewById(R.id.summit);
            summit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
summit();
                }
            });

          next= findViewById(R.id.next);
           next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
questionNumber++;
                    firebaseStuff();
                }
            });

            back = findViewById(R.id.back);
            if (questionNumber== 0){
                back.setClickable(false);}
            else{ back.setClickable(true);}

           back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    back = findViewById(R.id.back);
                    if (questionNumber== 1){
                        back.setClickable(false);}
                    else{ back.setClickable(true);}
questionNumber--;
firebaseStuff();
                }

            });

                }



    long timer = 300000;
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem counter = menu.findItem(R.id.counter);
        new CountDownTimer(timer, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String  hms =  (TimeUnit.MILLISECONDS.toHours(millis))+":"+
                        (TimeUnit.MILLISECONDS.toMinutes(millis) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)))+":"+ (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                counter.setTitle(hms);
                timer = millis;

            }

            public void onFinish() {
                counter.setTitle("done!");
            }
        }.start();

        return  true;

    }

public void summit(){
        // put the method to caculate score here
        finish();
}
public void firebaseStuff(){
    String select = bundle.getString("selected");
    final Query query = FirebaseDatabase.getInstance().getReference().child(select).orderByChild("no").equalTo(questionNumber);
    final FirebaseListOptions<firehand> options = new FirebaseListOptions.Builder<firehand>()
            .setLayout(R.layout.item_list)
            .setLifecycleOwner(this)
            .setQuery(query, firehand.class)
            .build();
    query.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                adapter = new FirebaseListAdapter(options) {
                    @Override
                    protected void populateView(View v, Object model, int position) {
                        TextView question = v.findViewById(R.id.question);
                        final TextView answer = v.findViewById(R.id.answer);

                        RadioButton r1 = v.findViewById(R.id.r1);
                        RadioButton r2 = v.findViewById(R.id.r2);
                        RadioButton r3 = v.findViewById(R.id.r3);
                        RadioButton r4 = v.findViewById(R.id.r4);

                        firehand firehand1 = (firehand) model;

                        answer.setText(firehand1.getAnswer().toString());
                        r1.setText(firehand1.getA().toString());
                        r2.setText(firehand1.getB().toString());
                        r3.setText(firehand1.getC().toString());
                        r4.setText(firehand1.getD().toString());
                        question.setText(firehand1.getQuestion().toString());
                        //  ad(answer.getText().toString());


                    }

                };
                lv.setAdapter(adapter);




            } else {
                summit();


            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Toast.makeText(MainActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
        }
    });
}
}

