package com.cwf.dragrecyclerview;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created at 陈 on 2016/10/24.
 *
 * @author chenwanfeng
 * @email 237142681@qq.com
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
    private SparseArray<View> mViews;//存储item中的子view

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<View>();
    }

    /**
     * 根据viewId获取item中对应的子view
     *
     * @param viewId item中子view的Id
     * @return 返回item中的子view
     */
    public View findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    public void setToTextView(int viewId, CharSequence text) {
        TextView textView = (TextView) findViewById(viewId);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void setTextColor(int viewId, int colorRes) {
        TextView textView = (TextView) findViewById(viewId);
        if (textView != null) {
            try {
                textView.setTextColor(colorRes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setTextCompoundDrawablesWithIntrinsicBounds(int viewId, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        TextView textView = (TextView) findViewById(viewId);
        if (textView != null) {
            try {
                textView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}
