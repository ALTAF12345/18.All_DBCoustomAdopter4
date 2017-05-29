package org.sawhers.altafhussain.dbcoustomadopter.ADOPTER;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.sawhers.altafhussain.dbcoustomadopter.DATABASE.DBHelper;
import org.sawhers.altafhussain.dbcoustomadopter.MODEL.Student;
import org.sawhers.altafhussain.dbcoustomadopter.R;
import org.sawhers.altafhussain.dbcoustomadopter.UI.StudentDetailActivity;
import org.sawhers.altafhussain.dbcoustomadopter.UI.StudentListActivity;

import java.util.ArrayList;

/**
 * Created by ALTAFHUSSAIN on 1/5/2017.
 */

public class StudentAdopter extends ArrayAdapter<Student> {
    Context ctx;
    ArrayList<Student> studentArrayList;
    DBHelper dbHelper;
    //public StudentAdopter(Context context, int resource) {
        //super(context, 0,resource);
    //}
    //now we know in "int resource" we access the Arraylist<Student>
    //0 mean the layout we want to access this arrayadopter, we can pass later or you can give it right now


    public StudentAdopter(Context context, ArrayList<Student> list) {
        super(context, R.layout.row_student,list);

        ctx=context;
        studentArrayList=list;
        dbHelper=new DBHelper(ctx);

    }

    //here the work method is getview


    //the getView method populate the one by one row in our costome adopter
     @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // return super.getView(position, convertView, parent);
         //after that we need the obj of Layoutinflator for to access the xml or bring xml code to java class and assign to view
         //---> now we need to access the posistion of student which we get from the position vise from studentArrayList and this we get from selected student in listview which sho us in StudentlistActivity
         final Student s=studentArrayList.get(position);


         LayoutInflater layoutInflater=LayoutInflater.from(ctx);
         final View view=layoutInflater.inflate(R.layout.row_student,parent,false);//here we pass the xml layout to view
         //in our coustome xml what we define like textview edittext etc now we access through this view
        //after the above comments we goto above like this symbol"--->"
        //below here all the textvew and etc
         TextView tvName,tvCourse,tvTotalFee,tvFeePaid;
         ImageView ivEdit,ivDelete;
         tvName= (TextView) view.findViewById(R.id.tvName_rowStudent);
         tvCourse= (TextView) view.findViewById(R.id.tvCourse_rowStudent);
         tvTotalFee= (TextView) view.findViewById(R.id.tvTotalFee_rowStudent);
         tvFeePaid= (TextView) view.findViewById(R.id.tvFeePaid_rowStudent);
         tvName.setText("Name :"+s.getName());
         tvCourse.setText("Course :"+s.getCourse());
         tvTotalFee.setText(String.valueOf(s.getTotalfee()));
         tvFeePaid.setText(String.valueOf(s.getFeepaid()));

 //**********************************************************
         ivEdit= (ImageView) view.findViewById(R.id.ivEdit_rowStudent);
         ivDelete= (ImageView) view.findViewById(R.id.ivDelete_rowStudent);
         ivEdit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
            //we have the select student abouve so we used this student mean like previse we pass the slected
                 // student from lvstudent to StudentDetailActivity with the help of bundle
                 Bundle bundle=new Bundle();
                 bundle.putSerializable("STUDENT",s);
                 Intent intent=new Intent(ctx, StudentDetailActivity.class);
                 intent.putExtras(bundle);
                 ctx.startActivity(intent);

             }
         });
 //************************************************************
         ivDelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder builder=new AlertDialog.Builder(ctx);
                 builder.setMessage("Are You Sure To Delete :"+s.getName());
                 builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(ctx, "Student Will be deleted  :", Toast.LENGTH_SHORT).show();

                      //********************************************
                         int studentid=s.getId();
                       int result= dbHelper.deleteStudent(String.valueOf(studentid));

                       if (result>0){
                           //Toast.makeText(ctx, "Student Deleted", Toast.LENGTH_SHORT).show();
                           studentArrayList.remove(s);
                           //studentArrayAdapter.notifyDataSetChanged();
                           notifyDataSetChanged();
                       }

                       else{
                           Toast.makeText(ctx, "Student fail to delete", Toast.LENGTH_SHORT).show();
                       }


                   }
                       //****************************************************

                 });
                 builder.setNegativeButton("No",null);
                 builder.show();


             }
         });

    return view;
    }
}
