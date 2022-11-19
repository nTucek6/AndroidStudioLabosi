package com.example.lv4;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lv4.Class.SaveSummary;
import com.example.lv4.Class.Student;
import com.example.lv4.Class.Subject;
import java.util.List;

public class StudentAdapter extends
        RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<SaveSummary> summaryList;

    public  StudentAdapter(List<SaveSummary> saveSummaries)
    {
        this.summaryList = saveSummaries;
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
        holder.tvName.setText(summaryList.get(position).getName());
        holder.tvSurname.setText(summaryList.get(position).getSurname());
        holder.tvSubject.setText(summaryList.get(position).getSubject());
        holder.imageViewUser.setImageBitmap(ConvertImageToBitmap(summaryList.get(position).getImageBase64()));
    }
    @Override
    public int getItemCount() {
        return summaryList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvSurname,tvSubject;
        ImageView imageViewUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvIme);
            tvSurname = itemView.findViewById(R.id.tvPrezime);
            tvSubject = itemView.findViewById(R.id.tvPredmet);
            imageViewUser = itemView.findViewById(R.id.personImage);
        }
    }

    public Bitmap ConvertImageToBitmap(String sImage)
    {
        if(sImage != null)
        {
            byte[] bytes = Base64.decode(sImage,Base64.DEFAULT);
            Bitmap image = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            return image;
        }
        else
        {
            return null;
        }
    }


}