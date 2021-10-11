package rob.myappcompany.timerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TimerListActivity extends AppCompatActivity {

    View view;
    TextView textView;
    ArrayAdapter adapter;

    private DatabaseHelper databaseHelper;

    String tag = TimerListActivity.class.getSimpleName();


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

        //double timeDouble = Double.parseDouble(time);

        databaseHelper = new DatabaseHelper(this);


        databaseHelper.insertItem(new TimeValueModel(time, "Time"));
        List<TimeValueModel> getAllTime_db = databaseHelper.getAllTime();

        List<String> num = new ArrayList<>();
        for (int i = 0; i < getAllTime_db.size(); i++){
            num.add(getAllTime_db.get(i).getTIMER_TIME());
        }




        textView = findViewById(R.id.textView);

        textView.setText(String.valueOf(time));

        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item , num){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                //Initialize a TextView for ListView each Item
                TextView tv = view.findViewById(android.R.id.text1);
                //set the text color of TextView (ListView Item)
                tv.setTextColor(Color.RED);
                tv.setBackgroundColor(Color.GREEN);

                return view;
            }
        };

        ListView listView = (ListView) findViewById(R.id.TimeListView);
        listView.setAdapter(adapter);





    }






}