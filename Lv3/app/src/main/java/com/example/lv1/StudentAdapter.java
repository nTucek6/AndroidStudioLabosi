
package com.example.lv1;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends
        RecyclerView.Adapter<StudentAdapter.ViewHolder> {


    private List<Student> StudentList;
    private List<Subject> SubjectList;

    public StudentAdapter(List<Student> studentList,List<Subject> subjectList) {
        this.StudentList = studentList;
        this.SubjectList = subjectList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.studenti_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.tvName.setText(StudentList.get(position).getName());
        holder.tvSurname.setText(StudentList.get(position).getSurname());
        for (Subject subject: SubjectList)
        {
            if(StudentList.get(position).getSubjectId() == subject.Id)
            {
                holder.tvSubject.setText(subject.Subject);
            }
        }
        //
    }
    @Override
    public int getItemCount() {
        return StudentList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvSurname,tvSubject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvIme);
            tvSurname = itemView.findViewById(R.id.tvPrezime);
            tvSubject = itemView.findViewById(R.id.tvPredmet);
        }
    }
}