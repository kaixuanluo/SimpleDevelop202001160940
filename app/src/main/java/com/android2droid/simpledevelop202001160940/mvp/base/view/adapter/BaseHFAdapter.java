package com.android2droid.simpledevelop202001160940.mvp.base.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.LayoutManager;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/10/20 14:46 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/10/20 14:46 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class BaseHFAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    public Adapter mInnerAdapter;
    private static final String TAG = BaseHFAdapter.class.getSimpleName();

    public BaseHFAdapter(Adapter adapter) {
        mInnerAdapter = adapter;
    }

    public boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    public boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(View view) {
        int mHeardPosition = mHeaderViews.size() + BASE_ITEM_TYPE_HEADER;
        mHeaderViews.put(mHeardPosition, view);
    }

    public void removeHearderView(View view) {
        if (view == null) {
            Debug.d(TAG, "removeHearderView view == null ... ");
        } else {
            int index = mHeaderViews.indexOfValue(view);
            if (index == -1) {
                Debug.d(TAG, "removeHearderView index == -1");
            } else {
                int key = mHeaderViews.keyAt(index);
                if (key == -1) {
                    Debug.d(TAG, "removeHearderView key == -1 value not found ... ");
                } else {
                    Debug.d(TAG, "remove view " + key);
                    mHeaderViews.remove(key);
                }
            }
        }
    }

    public void removeAllHeardView() {
        mHeaderViews.clear();
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    /**
     * 获取头部的高度和
     *
     * @return
     */
    public int getHeaderHeightCount() {
        int height = 0;
        for (int i = 0; i < mHeaderViews.size(); i++) {
            View view = mHeaderViews.get(mHeaderViews.keyAt(0));
            height += view.getHeight();
        }
        return height;
    }

    /**
     * 头部集合是否包含指定的view
     *
     * @param view 指定的view
     * @return 是否
     */
    public boolean isContainHeard(View view) {
        return mHeaderViews.indexOfValue(view) >= 0;
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    GridLayoutManager.SpanSizeLookup oldLookup;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {

            //            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            ViewHolder viewHolder = new HolderHeader(mHeaderViews.get(viewType));
            return viewHolder;

        } else if (mFootViews.get(viewType) != null) {
            //            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            ViewHolder viewHolder = new HolderFoot(mFootViews.get(viewType));
            return viewHolder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    public int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (isHeaderViewPos(position)) {
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount(), payloads);
        }
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    public class HolderHeader extends ViewHolder {

        public HolderHeader(View itemView) {
            super(itemView);

        }

    }

    public class HolderFoot extends ViewHolder {

        public HolderFoot(View itemView) {
            super(itemView);
        }
    }

    public abstract class BetterViewHolder extends ViewHolder {

        public BetterViewHolder(View itemView) {
            super(itemView);
        }

        public abstract BetterViewHolder onCreateViewHolder(ViewGroup parent);

        public abstract void onBindViewHolder(BetterViewHolder holder);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //针对Grid的 尾部铺满
    //    @Override
    //    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    //    {
    //        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    //        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    //        if (layoutManager instanceof GridLayoutManager)
    //        {
    //            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
    //            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
    //            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
    //            {
    //                @Override
    //                public int getSpanSize(int position)
    //                {
    //                    int viewType = getItemViewType(position);
    //                    if (mHeaderViews.get(viewType) != null)
    //                    {
    //                        return ((GridLayoutManager) layoutManager).getSpanCount();
    //                    } else if (mFootViews.get(viewType) != null)
    //                    {
    //                        return ((GridLayoutManager) layoutManager).getSpanCount();
    //                    }
    //                    if (oldLookup != null)
    //                        return oldLookup.getSpanSize(position);
    //                    return 1;
    //                }
    //            });
    //            oldLookup = spanSizeLookup;
    //            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
    //        }
    //    }

    /**
     * 对GridLayoutManager的处理；该方法会在RecyclerView.setAdapter()方法中被调用，因此前面建议保证一定在setLayoutManager方法之后调用该方法
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    //                    if: positon是Header视图或Footer视图显示的位置
                    int spanCount = gridLayoutManager.getSpanCount();
                    if (isFooterViewPos(position) || isHeaderViewPos(position)) {
                        return spanCount;
                    } else {
                        return getSpanCount(position, spanCount);
                    }
                }
            });
        }
    }

    protected int getSpanCount(int position, int spanCount) {
        return 1;
    }

    //    针对瀑布流的 //尾部铺满
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }
}