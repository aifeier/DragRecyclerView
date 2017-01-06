package com.cwf.dragrecyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created at 陈 on 2017/1/6.
 *
 * @author chenwanfeng
 * @email 237142681@qq.com
 */

public interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */

    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
