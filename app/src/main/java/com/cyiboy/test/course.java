package com.cyiboy.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class course extends AppCompatActivity {
    ListView list;
    String[] itemname ={
            "Ges100",
            "Ges102",
            "ges103",
            "Ges104",
            "Ges105",
            "Ges101",

    };

    Integer[] imgid={
            R.drawable.ic_work_black_24dp,
            R.drawable.ic_work_black_24dp,
            R.drawable.ic_work_black_24dp,
            R.drawable.ic_work_black_24dp,
            R.drawable.ic_work_black_24dp,
            R.drawable.ic_work_black_24dp

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list= findViewById(R.id.lv);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Intent intent = new Intent(course.this,MainActivity.class);
                intent.putExtra("selected", Slecteditem);
                startActivity(intent);

            }
        });
    }
}
