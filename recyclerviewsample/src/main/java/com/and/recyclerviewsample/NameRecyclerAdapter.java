/*
 * Copyright (c) 2014. AnD all rights reserved.
 */

package com.and.recyclerviewsample;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by k on 14. 11. 15.
 *
 * Recycler Adapter for TestDB
 *
 */
public class NameRecyclerAdapter extends RecyclerView.Adapter<NameViewHolder> {

    private Cursor mCursor;

    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        NameViewHolder vh = new NameViewHolder(new TextView(parent.getContext()));
        return vh;
    }

    @Override
    public void onBindViewHolder(NameViewHolder viewHolder, int position) {
        mCursor.moveToPosition(position);

        ((TextView)(viewHolder.itemView))
            .setText(mCursor.getString(mCursor.getColumnIndex(TestDb.NAME)));
    }

    @Override
    public int getItemCount() {
        if (mCursor != null)
            return mCursor.getCount();
        else return 0;
    }

    /**
     * set DataBase Cursor for Recycler Adapter
     * @param cur
     */
    public void setDataSet(Cursor cur) { mCursor = cur; }
}
