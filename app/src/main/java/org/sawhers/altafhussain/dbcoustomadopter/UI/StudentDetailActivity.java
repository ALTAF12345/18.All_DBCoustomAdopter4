package org.sawhers.altafhussain.dbcoustomadopter.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sawhers.altafhussain.dbcoustomadopter.DATABASE.DBHelper;
import org.sawhers.altafhussain.dbcoustomadopter.MODEL.Student;
import org.sawhers.altafhussain.dbcoustomadopter.R;

public class StudentDetailActivity extends AppCompatActivity {
    EditText etNameD,etCourseD,etTotalFeeD,etFeePaidD;
    Button btnUpdate;
    DBHelper dbHelper;
    int studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        etNameD= (EditText) findViewById(R.id.et_nameD);
        etCourseD= (EditText) findViewById(R.id.et_courseD);
        etTotalFeeD= (EditText) findViewById(R.id.et_totalfeeD);
        etFeePaidD= (EditText) findViewById(R.id.et_feepaidD);
        btnUpdate= (Button) findViewById(R.id.btnUpdata);
        dbHelper=new DBHelper(StudentDetailActivity.this);

//**********************************************************************
        Bundle bundle=getIntent().getExtras();
        Student s= (Student) bundle.getSerializable("STUDENT");

        etNameD.setText(s.getName());
        etCourseD.setText(s.getCourse());
        etTotalFeeD.setText(String.valueOf(s.getTotalfee()));
        etFeePaidD.setText(String.valueOf(s.getFeepaid()));
        studentID=s.getId();

 //************************************************************************
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(StudentDetailActivity.this, "Record updtes", Toast.LENGTH_SHORT).show();
                //Student student=getValuesFromFiled();//the getValuesFromFiled() give me the studnet obj and we pass to student obj
                Student s=getValuesFromFiled();
                DBHelper dbHelper=new DBHelper(StudentDetailActivity.this);
                long result =dbHelper.upDatesStudent(s);
                Log.e("ALTAF","studeneDeatail"+result);
                if (result>0){
                    Toast.makeText(StudentDetailActivity.this, "Record updtes", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(StudentDetailActivity.this, "Updation is Fail", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private Student getValuesFromFiled(){
        String name=etNameD.getText().toString().trim();
        String course=etCourseD.getText().toString().trim();
        String totalFee=etTotalFeeD.getText().toString().trim();
        String feePaid=etFeePaidD.getText().toString().trim();
        Student s=new Student(studentID,name,course,Integer.parseInt(totalFee),Integer.parseInt(feePaid));
        return s;
    }

}
