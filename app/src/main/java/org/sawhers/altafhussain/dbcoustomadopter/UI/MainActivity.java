package org.sawhers.altafhussain.dbcoustomadopter.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sawhers.altafhussain.dbcoustomadopter.DATABASE.DBHelper;
import org.sawhers.altafhussain.dbcoustomadopter.MODEL.Student;
import org.sawhers.altafhussain.dbcoustomadopter.R;

public class MainActivity extends AppCompatActivity {
    EditText etName,etCourse,etTotalFee,etFeePaid;
    Button btnSave,btnShowALL;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= (EditText) findViewById(R.id.et_name);
        etCourse= (EditText) findViewById(R.id.et_course);
        etTotalFee= (EditText) findViewById(R.id.et_totalfee);
        etFeePaid= (EditText) findViewById(R.id.et_feepaid);
        btnSave= (Button) findViewById(R.id.btn_save);
        btnShowALL= (Button) findViewById(R.id.btn_showall);

 //**********************************************************************
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=getValueFromField();
                dbHelper=new DBHelper(MainActivity.this);
                long result=dbHelper.saveStudent(student);
                if (result!=-1){
                    Toast.makeText(MainActivity.this, "student added to database"+result, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "student addition failled"+result, Toast.LENGTH_SHORT).show();
                }


            }
        });
 //*******************************************************************************
        btnShowALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this ,StudentListActivity.class));
            }
        });

    }
    public Student getValueFromField(){
        String name=etName.getText().toString().trim();
        String course=etCourse.getText().toString().trim();
        String totalfee=etTotalFee.getText().toString().trim();
        String feepaid=etFeePaid.getText().toString().trim();
        Student student=new Student(name,course,Integer.parseInt(totalfee),Integer.parseInt(feepaid));

     return student;
    }
}
