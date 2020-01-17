package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by luokaixuan
 * @date 2019/9/28
 * 这个类是用来干嘛的
 */

public class BaseListAdapter<ITEM> extends RecyclerView.Adapter {

    private String TAG = getClass().getSimpleName();
    List<ITEM> itemList = new ArrayList<>();

    public List<ITEM> getItems() {
        return itemList;
    }

    public void setItems(List<ITEM> items) {
        this.itemList = items;
    }

    private RecyclerViewPlus.OnRecyclerViewBindListener mRecyclerViewBindListener;

    public RecyclerViewPlus.OnRecyclerViewBindListener getRecyclerViewBindListener() {
        return mRecyclerViewBindListener;
    }

    public void setRecyclerViewBindListener(RecyclerViewPlus.OnRecyclerViewBindListener mRecyclerViewBindListener) {
        this.mRecyclerViewBindListener = mRecyclerViewBindListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (getRecyclerViewBindListener() == null) {
            Debug.d(TAG, "onCreateViewHolder getRecyclerViewBindListener() == null ... ");
            return null;
        } else {
            return getRecyclerViewBindListener().createItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (itemList != null) {
            ITEM item = itemList.get(position);
            if (getRecyclerViewBindListener() == null) {
                Debug.d(TAG, "onBindViewHolder getRecyclerViewBindListener() == null ... ");
            } else {
                getRecyclerViewBindListener().bindItemViewHolder(item, holder, position);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (itemList != null) {
            ITEM item = itemList.get(position);
            if (getRecyclerViewBindListener() == null) {
                Debug.d(TAG, "onBindViewHolder getRecyclerViewBindListener() == null ... ");
            } else {
                getRecyclerViewBindListener().bindItemViewHolder(item, holder, position, payloads);
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}

