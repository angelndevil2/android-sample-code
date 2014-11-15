/*
 * Copyright (c) 2014. AnD all rights reserved.
 */

package com.and.recyclerviewsample;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by k on 14. 11. 15.
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
