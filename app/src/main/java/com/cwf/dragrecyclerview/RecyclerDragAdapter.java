package com.cwf.dragrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created at 陈 on 2017/1/6.
 *
 * @author chenwanfeng
 * @email 237142681@qq.com
 */

public class RecyclerDragAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements ItemTouchHelperAdapter {

    private OnItemClickListener mOnItemClickListener;
    private Context context;
    private List<String> list;

    public RecyclerDragAdapter(Context ct) {
        context = ct;
        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        final RecyclerViewHolder holder = new RecyclerViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                Log.e("onclick", "item" + getItem(position));
//                onItemDismiss(holder.getLayoutPosition());
//                addItem(getItemCount() + 1 + "");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, holder.getLayoutPosition());
                }
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return holder;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.setToTextView(android.R.id.text1, "item" + getItem(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getItem(int position) {
        return list.get(position);
    }

    public void addItem(String item) {
        addItem(list.size(), item);
    }

    public void addItem(int position, String item) {
        list.add(position, item);
        notifyItemInserted(position);
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
