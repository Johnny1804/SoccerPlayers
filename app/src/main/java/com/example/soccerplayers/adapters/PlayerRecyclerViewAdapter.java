package com.example.soccerplayers.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerplayers.activities.PlayerActivity;
import com.example.soccerplayers.models.SoccerPlayer;
import com.example.soccerplayers.R;

import java.util.List;

public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List <SoccerPlayer> playersList;
    private Context context;

    public PlayerRecyclerViewAdapter(Context context, List <SoccerPlayer> mItemList){
        this.playersList = mItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if (viewType == TYPE_ITEM) {
            // inflate item layout and pass it to view holder
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item_recycler_view,parent,false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            // inflate header layout and pass it to view holder
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_header_recycler_view,parent,false);
            return new HeaderViewHeader(view);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            final SoccerPlayer item = playersList.get(position - 1);

            String imageName = item.getImage();
            imageName = imageName.substring(0, imageName.indexOf("."));

            // populate item holder with data
            ((ItemViewHolder) holder).imageView.setImageResource(this.context.getResources().getIdentifier(imageName, "drawable", context.getPackageName()));
            ((ItemViewHolder) holder).nameView.setText(item.getName());
            ((ItemViewHolder) holder).currentClubView.setText(String.valueOf(item.getCurrentClub()));
            ((ItemViewHolder) holder).citizenshipView.setText(String.valueOf(item.getCitizenship()));

        } else if (holder instanceof HeaderViewHeader) {
            // populate header holder with data
            ((HeaderViewHeader) holder).header.setText("Soccer players list");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return playersList.size() + 1;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView nameView;
        private TextView currentClubView;
        private TextView citizenshipView;
        private ImageView imageView;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.playerImage);
            nameView = itemView.findViewById(R.id.nameView);
            currentClubView =  itemView.findViewById(R.id.currentClubView);
            citizenshipView =  itemView.findViewById(R.id.citizenshipView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), PlayerActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("player", playersList.get(getAdapterPosition() - 1));
                    intent.putExtras(bundle);

                    view.getContext().startActivity(intent);
                }
            });
        }

    }

    class HeaderViewHeader extends RecyclerView.ViewHolder {
        TextView header;

        public HeaderViewHeader(View itemView) {
            super(itemView);

            header = itemView.findViewById(R.id.textView);
        }
    }
}