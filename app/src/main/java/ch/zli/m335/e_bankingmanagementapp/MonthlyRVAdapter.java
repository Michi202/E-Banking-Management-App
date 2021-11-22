package ch.zli.m335.e_bankingmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonthlyRVAdapter extends RecyclerView.Adapter<MonthlyRVAdapter.ViewHolder> {

    private ArrayList<MonthlyPlanService> courseModalArrayList;
    private Context context;

    public MonthlyRVAdapter(ArrayList<MonthlyPlanService> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonthlyPlanService modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getMonthlyName());
        holder.courseDescTV.setText(modal.getMonthlyDescription());
        holder.courseDurationTV.setText(modal.getMonthlyDuration());
        holder.courseTracksTV.setText(modal.getMonthlyTracks());
    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView courseNameTV, courseDescTV, courseDurationTV, courseTracksTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
            courseTracksTV = itemView.findViewById(R.id.idTVCourseTracks);
        }
    }
}
