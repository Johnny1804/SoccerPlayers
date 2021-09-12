package com.example.soccerplayers.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerplayers.activities.WebActivity;
import com.example.soccerplayers.R;

import java.util.List;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter <HistoryRecyclerViewAdapter.ViewHolder> {
    public Context context;
    private List <String> history;

    public HistoryRecyclerViewAdapter(Context context, List <String> history) {
        this.context = context;
        this.history = history;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_recycler_view, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String currentUrl = history.get(position);

        holder.url.setText(currentUrl);
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView url;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            url = itemView.findViewById(R.id.urlTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // make an intent and a bundle
                    Intent intent = new Intent(view.getContext(), WebActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("url", history.get(getAdapterPosition()));
                    intent.putExtras(bundle);

                    view.getContext().startActivity(intent);
                }

            });
        }
    }
}
