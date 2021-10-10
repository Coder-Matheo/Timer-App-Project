package rob.myappcompany.timerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TimerListActivity extends AppCompatActivity {

    View view;
    TextView textView;


    ArrayAdapter adapter;

    String[] mobilArray ={"Android", "Sony", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_list);


        //timeListView = findViewById(R.id.TimeListView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        view = this.getWindow().getDecorView();
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.appleBlack));

        Intent getTime = getIntent();
        String time = getTime.getStringExtra("timeValue");

        double timeDouble = Double.parseDouble(time);




        List<String> num = new ArrayList<>();
        num.add("Apple");
        num.add("Android");
        num.add("Android");
        num.add("Android");
        num.add("Android");
        textView = findViewById(R.id.textView);

        textView.setText(String.valueOf(time));

        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item , num);

        ListView listView = (ListView) findViewById(R.id.TimeListView);
        listView.setAdapter(adapter);


    }



}