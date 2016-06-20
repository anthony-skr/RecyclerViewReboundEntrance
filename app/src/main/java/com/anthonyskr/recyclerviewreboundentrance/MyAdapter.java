package com.anthonyskr.recyclerviewreboundentrance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Classic custom adapter.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    /**
     * Classic ViewHolder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mBackground;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text);
            mBackground = (ImageView) v.findViewById(R.id.image);
        }
    }

    private RecyclerViewAnimator mAnimator;

    /**
     * @param recyclerView We ask it but it's not necessarily needed.
     */
    public MyAdapter(RecyclerView recyclerView) {
        mAnimator = new RecyclerViewAnimator(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        /**
         * First item's entrance animations.
         */
        mAnimator.onCreateViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(getTitle(position));

        Glide.with(holder.mBackground.getContext())
                .load(getBackgroundImage(position))
                .centerCrop()
                .into(holder.mBackground);

        /**
         * Item's entrance animations during scroll are performed here.
         */
        mAnimator.onBindViewHolder(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    /**
     * Random data.
     */
    private String getTitle(int position) {
        if (position % 5 == 0) {
            return "Shoreline of Algarve Portugal";
        } else if (position % 4 == 0) {
            return "Houses and buildings in Porto";
        } else if (position % 3 == 0) {
            return "Pavilhão Rosa Mota";
        } else if (position % 2 == 0) {
            return "Tower at dusk in Lisbon";
        } else {
            return "Dramatic clouds over Lisbon";
        }
    }

    /**
     * Random data.
     */
    private String getBackgroundImage(int position) {
        if (position % 5 == 0) {
            return "https://www.goodfreephotos.com/albums/portugal/other-portugal/shoreline-of-algarve-portugal.jpg";
        } else if (position % 4 == 0) {
            return "https://www.goodfreephotos.com/albums/portugal/porto/houses-and-buildings-in-porto-portugal.jpg";
        } else if (position % 3 == 0) {
            return "https://www.goodfreephotos.com/albums/portugal/porto/Pavilh%C3%A3o-Rosa-Mota-in-porto-portugal.jpg";
        } else if (position % 2 == 0) {
            return "https://www.goodfreephotos.com/albums/portugal/lisbon/tower-at-dusk-in-lisbon-portugal.jpg";
        } else {
            return "https://www.goodfreephotos.com/albums/portugal/lisbon/dramatic-clouds-over-lisbon-portugal.jpg";
        }
    }
}
