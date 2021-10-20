package rob.myappcompany.timerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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

    private static final int REQUEST_CODE_GALLERY = 999;
    Context context;
    View view;
    TextView textView;
    ArrayAdapter adapter;
    Button imageButton;
    Button cancelButton;
    ListView listView;

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


        alertMessage();
        init();
        requestPermissionForImage();
        getItemFromDBTogetAdapterPar();
        insertItemToDB();
    }



    public void init(){
        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.TimeListView);
        databaseHelper = new DatabaseHelper(this);
    }

    private void getItemFromDBTogetAdapterPar() {
        List<TimeValueModel> getAllTime_db = databaseHelper.getAllTime();

        List<String> num = new ArrayList<>();
        for (int i = 0; i < getAllTime_db.size(); i++){
            num.add(getAllTime_db.get(i).getTIMER_TIME());
        }

        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item , num);


        listView.setAdapter(adapter);
    }

    public void requestPermissionForImage(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY);
    }

    private void insertItemToDB() {

        Intent getTime = getIntent();
        String time = getTime.getStringExtra("timeValue");
        //databaseHelper.insertItem(new TimeValueModel(time, "Time"));

        textView.setText(String.valueOf(time));

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