package org.sawhers.altafhussain.dbcoustomadopter.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.sawhers.altafhussain.dbcoustomadopter.ADOPTER.StudentAdopter;
import org.sawhers.altafhussain.dbcoustomadopter.DATABASE.DBHelper;
import org.sawhers.altafhussain.dbcoustomadopter.MODEL.Student;
import org.sawhers.altafhussain.dbcoustomadopter.R;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    ListView lvStudent;
    //ArrayAdapter<Student> studentArrayAdapter;
    StudentAdopter studentArrayAdapter;
    ArrayList<Student> studentArrayList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        lvStudent= (ListView) findViewById(R.id.lv_student);
        dbHelper=new DBHelper(this);
       // studentArrayList=dbHelper.showAllStudents();
        //studentArrayAdapter=new ArrayAdapter<>(StudentListActivity.this,android.R.layout.simple_list_item_1,studentArrayList);

       // lvStudent.setAdapter(studentArrayAdapter);

 //*******************************************************************
       /* lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //studentArrayList.get(position);
                Student s=studentArrayList.get(position);
                Bundle bundle=new Bundle();
                bundle.putSerializable("STUDENT",s);
                Intent intent=new Intent(StudentListActivity.this,StudentDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });*/
//************************************************************************************************
//       lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//           @Override
//           public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//             //  Toast.makeText(StudentListActivity.this, "long click", Toast.LENGTH_SHORT).show();
//               //***********************************
//               AlertDialog.Builder builder1 = new AlertDialog.Builder(StudentListActivity.this);
//               builder1.setMessage("Are you sure want to delete");
//               builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                   public void onClick(DialogInterface dialog, int id) {
//                       Student s=studentArrayList.get(position);
//                       int studentid=s.getId();
//                       int result= dbHelper.deleteStudent(String.valueOf(studentid));
//
//                       if (result>0){
//                           Toast.makeText(StudentListActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
//                           studentArrayList.remove(s);
//                           studentArrayAdapter.notifyDataSetChanged();
//                       }
//
//                       else{
//                           Toast.makeText(StudentListActivity.this, "Student fail to delete", Toast.LENGTH_SHORT).show();
//                       }
//
//
//                   }
//               });
//
//               builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                   public void onClick(DialogInterface dialog, int id) {
//                       dialog.cancel();
//                   }
//               });
//
//               AlertDialog alert11 = builder1.create();
//               alert11.show();
//
//               return true;
//           }
//            //**************************************
//
//       });

    }


    @Override
    protected void onResume() {
        super.onResume();

        studentArrayList = new ArrayList<>();
        studentArrayList=dbHelper.showAllStudents();
       // studentArrayAdapter=new ArrayAdapter<>(StudentListActivity.this,android.R.layout.simple_list_item_1,studentArrayList);
        studentArrayAdapter =new StudentAdopter(StudentListActivity.this,studentArrayList);
        lvStudent.setAdapter(studentArrayAdapter);
    }
}
