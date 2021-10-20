package rob.myappcompany.timerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TimerListActivity extends AppCompatActivity {

    View view;
    TextView textView;
    ArrayAdapter adapter;
    Button imageButton;
    Button cancelButton;

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

        //databaseHelper.insertItem(new TimeValueModel(time, "Time"));

        List<TimeValueModel> getAllTime_db = databaseHelper.getAllTime();

        List<String> num = new ArrayList<>();
        for (int i = 0; i < getAllTime_db.size(); i++){
            num.add(getAllTime_db.get(i).getTIMER_TIME());
        }

        textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(time));

        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item , num);

        ListView listView = (ListView) findViewById(R.id.TimeListView);
        listView.setAdapter(adapter);

        alertMessage();
        init();
    }

    public void init(){

    }

    private void alertMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        EditText imgEditText = customView.findViewById(R.id.descriptionEditText);
        imageButton = customView.findViewById(R.id.imageButton);
        cancelButton = customView.findViewById(R.id.cancelButton);

        builder.setView(customView);
        AlertDialog inputDialog = builder.create();
        inputDialog.show();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Seccess", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                inputDialog.dismiss();
            }
        });




    }


}