package rob.myappcompany.timerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TIMER_APP_DB = "TIMER_IMG_DB";
    public static final String TIMER_TABLE = "TIMER_TABLE";
    public static final String TIMER_ID = "TIMER_ID";
    public static final String TIMER_TIME = "TIMER_TIME";
    public static final String TIMER_DATA = "TIMER_DATA";
    public static final String TIMER_DESC = "TIMER_DESC";
    public static final String TIMER_IMG = "TIMER_IMG";


    public DatabaseHelper(@Nullable Context context) {
        super(context, TIMER_APP_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLiteException{
        try {
            String timerTableStat = "CREATE TABLE IF NOT EXISTS " + TIMER_TABLE + "(" + TIMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIMER_TIME + " TEXT, " + TIMER_DATA + " TEXT, "+
                    TIMER_DESC + " TEXT,"+ TIMER_IMG +" BLOG )";

            db.execSQL(timerTableStat);
        }catch (SQLiteException e){
            try{
                throw new IOException(e);
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TIMER_TABLE);
        onCreate(db);
    }

    public String getDataTime(String choose){
        LocalDateTime localDateTime;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDateTime = LocalDateTime.now();

            if (choose.equals("Data")){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return localDateTime.format(formatter);
            }else if(choose == "Time"){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                return localDateTime.format(formatter);
            }
        }
        return choose;
    }

    public boolean insertItem (TimeValueModel timeValueModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues insertVal = new ContentValues();
        insertVal.put(TIMER_TIME, timeValueModel.getTIMER_TIME());
        insertVal.put(TIMER_DESC, timeValueModel.getTIMER_DESC());
        insertVal.put(TIMER_DATA, getDataTime("Data"));
        long inserting = db.insert(TIMER_TABLE, null, insertVal);
        if (inserting == -1){
            return false;
        }else {
            return true;
        }
    }

    public List<TimeValueModel> getAllTime(){
        List<TimeValueModel> ret_list = new ArrayList<>();
        String queryString = "SELECT * FROM "+ TIMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            do {
                //TimeValueModel setTimeVal = new TimeValueModel(cursor.getString(1),cursor.getString(2));
                //ret_list.add(setTimeVal);
            }while (cursor.moveToNext());
        }
        return ret_list;
    }
}
