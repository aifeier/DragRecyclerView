package com.cwf.dragrecyclerview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView titlerecyclerview;
    private RecyclerView recyclerView;
    private RecyclerDragAdapter dragAdapter;
    RecyclerDragAdapter recyclerDragAdapter;
    private FrameLayout mView;
    private float topHeight = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mView = (FrameLayout) findViewById(R.id.view);
        titlerecyclerview = (RecyclerView) findViewById(R.id.norecyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        titlerecyclerview.setLayoutManager(gridLayoutManager);
        recyclerDragAdapter = new RecyclerDragAdapter(this);

        titlerecyclerview.setAdapter(recyclerDragAdapter);
        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback =
                new SimpleItemTouchHelperCallback(recyclerDragAdapter, gridLayoutManager);
        final ItemTouchHelper helper = new ItemTouchHelper(simpleItemTouchHelperCallback);
        helper.attachToRecyclerView(titlerecyclerview);
        recyclerDragAdapter.setmOnStartDragListener(new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                helper.startDrag(viewHolder);
            }
        });

        dragAdapter = new RecyclerDragAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(dragAdapter);
        dragAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                if (topHeight == -1)
                    topHeight = getSupportActionBar().getHeight() + 24 * getResources().getDisplayMetrics().density;
                int[] initLocation = new int[2];
                view.getLocationOnScreen(initLocation);
                Log.e("test", initLocation[0] + ":" + initLocation[1]);
                View endView = titlerecyclerview.getChildAt(0);
                int[] endLocation = new int[2];
                endView.getLocationOnScreen(endLocation);
                Log.e("test", endLocation[0] + ":" + endLocation[1]);
                TranslateAnimation moveAnimation =
                        new TranslateAnimation(initLocation[0], endLocation[0]
                                , initLocation[1] - topHeight, endLocation[1] - topHeight);
                moveAnimation.setDuration(1000L);
                //动画配置
                AnimationSet moveAnimationSet = new AnimationSet(true);
                moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
                moveAnimationSet.addAnimation(moveAnimation);
                ImageView img = new ImageView(MainActivity.this);
                view.setDrawingCacheEnabled(true);
                Bitmap bitmap = view.getDrawingCache();
                img.setImageBitmap(bitmap.createBitmap(bitmap));
                view.setDrawingCacheEnabled(false);
                mView.addView(img);
                mView.setVisibility(View.VISIBLE);
                mView.startAnimation(moveAnimationSet);
                moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        dragAdapter.onItemDismiss(position);
//                        mView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mView.removeAllViews();
                        mView.setVisibility(View.GONE);
                        recyclerDragAdapter.addItem(0, "add" + position);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                return false;
            }
        });
    }


}
