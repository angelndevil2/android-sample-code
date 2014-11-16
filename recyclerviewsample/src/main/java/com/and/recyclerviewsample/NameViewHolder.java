package com.and.recyclerviewsample;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 * viewHolder for Test DB Name
 */
public class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public NameViewHolder(View itemView) {
        super(itemView);
        // add click listener for this view
        itemView.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
