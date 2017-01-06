package com.cwf.dragrecyclerview;

/**
 * Created at 陈 on 2017/1/6.
 *
 * @author chenwanfeng
 * @email 237142681@qq.com
 */

public interface  ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);



    void onItemDismiss(int position);
}
