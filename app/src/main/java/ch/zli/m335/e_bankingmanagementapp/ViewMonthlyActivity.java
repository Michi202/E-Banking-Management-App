package ch.zli.m335.e_bankingmanagementapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewMonthlyActivity extends AppCompatActivity {

    private ArrayList<ch.zli.m335.e_bankingmanagementapp.MonthlyPlanService> courseModalArrayList;
    private ch.zli.m335.e_bankingmanagementapp.DBHandler dbHandler;
    private ch.zli.m335.e_bankingmanagementapp.MonthlyRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_courses);

        courseModalArrayList = new ArrayList<>();
        dbHandler = new ch.zli.m335.e_bankingmanagementapp.DBHandler(ViewMonthlyActivity.this);

        courseModalArrayList = dbHandler.readMonthly();

        courseRVAdapter = new ch.zli.m335.e_bankingmanagementapp.MonthlyRVAdapter(courseModalArrayList, ViewMonthlyActivity.this);
        coursesRV = findViewById(R.id.idRVCourses);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewMonthlyActivity.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        coursesRV.setAdapter(courseRVAdapter);
    }
}
