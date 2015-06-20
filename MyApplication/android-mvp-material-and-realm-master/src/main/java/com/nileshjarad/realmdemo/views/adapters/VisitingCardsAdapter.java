package com.nileshjarad.realmdemo.views.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nileshjarad.realmdemo.R;
import com.nileshjarad.realmdemo.models.VisitingCardPOJO;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public class VisitingCardsAdapter extends RecyclerView.Adapter<VisitingCardsAdapter.ViewHolder> {


    private final ArrayList<VisitingCardPOJO> visitingCards;

    public void removeItem(int position) {
        visitingCards.remove(position);
        notifyItemRemoved(position);

//        for (int i = 0; i < visitingCards.size(); i++) {
////            if (visitingCards.get(i).getNo() == id) {
//////                visitingCards.remove(i);
////                notifyDataSetChanged();
////                return;
////            }
//        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_no)
        TextView tvNo;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_address)
        TextView tvAddress;
        @InjectView(R.id.card_view)
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

    }


    public VisitingCardsAdapter(ArrayList<VisitingCardPOJO> visitingCards) {
        this.visitingCards = visitingCards;
    }


    public int getID(int posiyion) {
        return visitingCards.get(posiyion).getNo();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_visiting_cards, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.mTextView.setText(mDataset[position]);
        holder.tvNo.setText("" + visitingCards.get(position).getNo());
        holder.tvName.setText("" + visitingCards.get(position).getName());
        holder.tvAddress.setText("" + visitingCards.get(position).getAddress());

    }


    @Override
    public int getItemCount() {
        return visitingCards.size();
    }
}
