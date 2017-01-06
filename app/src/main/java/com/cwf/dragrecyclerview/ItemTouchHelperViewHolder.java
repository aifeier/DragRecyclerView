package com.cwf.dragrecyclerview;

/**
 * Created at 陈 on 2017/1/6.
 *
 * @author chenwanfeng
 * @email 237142681@qq.com
 */

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Notifies a View Holder of relevant callbacks from
 * <p>
 * {@link ItemTouchHelper.Callback}.
 */

public interface ItemTouchHelperViewHolder {


    /**
     * Called when the {@link ItemTouchHelper} first registers an
     * <p>
     * item as being moved or swiped.
     * <p>
     * Implementations should update the item view to indicate
     * <p>
     * it's active state.
     */

    void onItemSelected();


    /**
     * Called when the {@link ItemTouchHelper} has completed the
     * <p>
     * move or swipe, and the active item state should be cleared.
     */

    void onItemClear();

}