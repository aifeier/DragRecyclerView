package com.cwf.dragrecyclerview;

import android.view.View;

/**
 * Created at 陈 on 2016/10/24.
 * 按钮点击
 * @author chenwanfeng
 * @email 237142681@qq.com
 */

public interface OnItemClickListener {
    void onItemClick(View view, int position);

    boolean onItemLongClick(View view, int position);
}
