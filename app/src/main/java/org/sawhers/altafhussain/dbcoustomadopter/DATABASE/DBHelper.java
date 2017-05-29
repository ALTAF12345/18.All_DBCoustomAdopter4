package org.sawhers.altafhussain.dbcoustomadopter.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.sawhers.altafhussain.dbcoustomadopter.MODEL.Student;

import java.util.ArrayList;

/**
 * Created by ALTAFHUSSAIN on 1/4/2017.
 */



public class DBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME="student.db";
    private static final int DB_VERSION=1;
    private static final String KEY_TABLE="tblstudent";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_COURSE="course";
    private static final String KEY_TOTAL_FEE="totaldfee";
    private static final String KEY_FEE_PAID="feepaid";

 //************************************************************************
 private static final String CREATE_TABLE="CREATE TABLE " + KEY_TABLE + " ( "

                            +KEY_ID + " Integer Primary Key Autoincrement, "
                            +KEY_NAME + " Text, "
                            +KEY_COURSE + " Text ,"
                            +KEY_TOTAL_FEE + " Integer, "
                            +KEY_FEE_PAID + " Integer "

                            +" ); ";




    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE);
        onCreate(db);
    }

    public long saveStudent(Student student) {
        long result=-1;
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_COURSE,student.getCourse());
        values.put(KEY_TOTAL_FEE,student.getTotalfee());
        values.put(KEY_FEE_PAID,student.getFeepaid());

        //db.insert(KEY_TABLE , null ,values);
        result=db.insert(KEY_TABLE , null ,values);

      return result;
    }

    public ArrayList<Student> showAllStudents() {
        ArrayList<Student> studentArrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from " + KEY_TABLE , null);
        if (c.moveToFirst()){
            do{
                int id=c.getInt(c.getColumnIndex(KEY_ID));
                String name=c.getString(c.getColumnIndex(KEY_NAME));
                String course=c.getString(c.getColumnIndex(KEY_COURSE));
                int totalFee=c.getInt(c.getColumnIndex(KEY_TOTAL_FEE));
                int feePaid=c.getInt(c.getColumnIndex(KEY_FEE_PAID));

                //here we need the id method of student class b/c we access the student id also
                Student s =new Student(id,name,course,totalFee,feePaid);
                studentArrayList.add(s);

            }while (c.moveToNext());
        }


        return studentArrayList;
    }

    public long upDatesStudent(Student s) {
        long result=0;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put(KEY_ID,student.getId());//this update all the record with this update data ,we pass the id in where condition
        values.put(KEY_NAME,s.getName());
        values.put(KEY_COURSE,s.getCourse());
        values.put(KEY_TOTAL_FEE,s.getTotalfee());
        values.put(KEY_FEE_PAID,s.getFeepaid());

        //db.update(KEY_TABLE ,values,KEY_ID + "=?" ,new String[]{String.valueOf(student.getId())});
        Log.e("ALTAF","DBHelper"+result);
        result=db.update(KEY_TABLE, values , KEY_ID + " =? " , new String[]{String.valueOf(s.getId())});



        return result;
    }


public int deleteStudent(String id ){

    int result=0;
    try {
        SQLiteDatabase db=this.getWritableDatabase();
        result=db.delete(KEY_TABLE, KEY_ID + "=?" , new String[]{id} );

        return result;
    }
    catch (Exception e) {
        e.printStackTrace();
        Log.e("ERROR","deleteStudent Wrong Table name ERROR");
    }
    return result;
}
}
