package ch.zli.m335.e_bankingmanagementapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MonthlyPlanActivity extends AppCompatActivity {

    ch.zli.m335.e_bankingmanagementapp.MonthlyPlanService monthlyPlanService;
    boolean monthlyPlanBound = false;
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn, readCourseBtn;
    private ch.zli.m335.e_bankingmanagementapp.DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_plan_activity);

        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        readCourseBtn = findViewById(R.id.idBtnReadCourse);

        dbHandler = new ch.zli.m335.e_bankingmanagementapp.DBHandler(MonthlyPlanActivity.this);

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(MonthlyPlanActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewMonthly(courseName, courseDuration, courseDescription, courseTracks);

                Toast.makeText(MonthlyPlanActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");
            }
        });

        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MonthlyPlanActivity.this, ch.zli.m335.e_bankingmanagementapp.ViewMonthlyActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ch.zli.m335.e_bankingmanagementapp.MonthlyPlanService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            ch.zli.m335.e_bankingmanagementapp.MonthlyPlanService.MonthlyPlanBinder binder = (ch.zli.m335.e_bankingmanagementapp.MonthlyPlanService.MonthlyPlanBinder) service;
            monthlyPlanService = binder.getService();
            monthlyPlanBound = true;
            if (monthlyPlanBound) {

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            monthlyPlanBound = false;
        }
    };

}
