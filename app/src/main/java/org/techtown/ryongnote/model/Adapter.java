package org.techtown.ryongnote.model;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.ryongnote.note.NoteDetails;
import org.techtown.ryongnote.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    // Firebase에서 주어지는 Adpater를 사용하느라 MainActivity에서 사용하지 않는다.
    List<String> titles;
    List<String> content;

    public Adapter(List<String> titles, List<String> content){
        this.titles = titles;
        this.content = content;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.noteTitle.setText(titles.get(position));
        holder.noteContent.setText(content.get(position));
        Integer code = getRandomColor();  // 노트 클릭시 밖에서 보이는 노트 색과 똑같은 노트 색을 보여주기 위해서 색깔 코드를 변수에 넣음
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.mCardView.setCardBackgroundColor(holder.view.getResources().getColor(code, null));
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // note를 클릭시
                Intent i = new Intent(v.getContext(), NoteDetails.class);  // NoteDetails로 화면 전환
                i.putExtra("title", titles.get(position));  // 노트 클릭시 몇 번째 노트인지 position 부여
                i.putExtra("content", content.get(position));  // 노트 클릭시 몇 번째 노트인지 position 부여
                i.putExtra("code", code);
                v.getContext().startActivity(i);
            }
        });

    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.blue);
        colorCode.add(R.color.yellow);
        colorCode.add(R.color.skyblue);
        colorCode.add(R.color.lightPurple);
        colorCode.add(R.color.lightGreen);
        colorCode.add(R.color.gray);
        colorCode.add(R.color.pink);
        colorCode.add(R.color.red);
        colorCode.add(R.color.greenlight);
        colorCode.add(R.color.notgreen);

        Random randomColor = new Random();
        int number = randomColor.nextInt(colorCode.size());
        return colorCode.get(number);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent;
        View view;
        CardView mCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.titles);  // note_view_layout.xml
            noteContent = itemView.findViewById(R.id.content);  // note_view_layout.xml
            mCardView = itemView.findViewById(R.id.noteCard);  // note_view_layout.xml
            view = itemView;
        }
    }
}
