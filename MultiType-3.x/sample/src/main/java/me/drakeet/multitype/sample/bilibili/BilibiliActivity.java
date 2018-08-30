/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.drakeet.multitype.sample.bilibili;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.sample.MenuBaseActivity;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.common.Category;
import me.drakeet.multitype.sample.common.CategoryItemViewBinder;
import me.drakeet.multitype.sample.normal.ImageItem;
import me.drakeet.multitype.sample.normal.RichItem;
import me.drakeet.multitype.sample.normal.TextItem;
import me.drakeet.multitype.sample.normal.TextItemViewBinder;

public class BilibiliActivity extends MenuBaseActivity {

    private static final int SPAN_COUNT = 2;
    private int HISTORYLIST = 10;
    @VisibleForTesting
    List<Object> items;
    @VisibleForTesting
    List<Object> items2;
    @VisibleForTesting
    MultiTypeAdapter adapter;
    private PostViewBinder postViewBinder = null;
    private GridLayoutManager layoutManager = null;
    private CategoryItemViewBinder categoryItemViewBinder;
    private int historylistsize = 0;
    private boolean isToggle;

    private HorizontalPostsViewBinder horizontalPostsViewBinder = new HorizontalPostsViewBinder();

    public void onToggle(boolean b) {

        isToggle = b;

        if (b) {

            adapter.setItems(items2);
            SpanSizeLookup spanSizeLookup = new SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    Object item = items2.get(position);
                    return item instanceof PostList || item instanceof Category ? SPAN_COUNT : 1;
                }
            };
            layoutManager.setSpanSizeLookup(spanSizeLookup);
            adapter.notifyDataSetChanged();

        } else {

            adapter.setItems(items);
            SpanSizeLookup spanSizeLookup = new SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    Object item = items.get(position);
                    return item instanceof PostList || item instanceof Category ? SPAN_COUNT : 1;
                }
            };
            layoutManager.setSpanSizeLookup(spanSizeLookup);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initData();


        adapter = new MultiTypeAdapter();
        categoryItemViewBinder = new CategoryItemViewBinder(this, historylistsize);
        postViewBinder = new PostViewBinder(this);
        adapter.register(Category.class, categoryItemViewBinder);
        adapter.register(Post.class, postViewBinder);
        adapter.register(PostList.class, horizontalPostsViewBinder);
        RecyclerView recyclerView = findViewById(R.id.list);

        layoutManager = new GridLayoutManager(this, 2);
        SpanSizeLookup spanSizeLookup = new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                Object item;
                item = items.get(position);
                return item instanceof PostList || item instanceof Category ? SPAN_COUNT : 1;

            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        adapter.setItems(items);
        adapter.notifyDataSetChanged();

    }

    private void initData() {

        JsonData data = new JsonData();
        items = new ArrayList<>();
        items2 = new ArrayList<>();

        items.add(data.category0);
        for (int i = 1; i < HISTORYLIST; i++) {
            items.add(new Post(1, R.drawable.img_00, "搜索历史"));
        }

        historylistsize = HISTORYLIST;

        for (int i = 0; i < 1; i++) {
            /* You also could use Category as your CategoryItemContent directly */
            items.add(data.category1);
            items.add(data.postArray[0]);
            items.add(data.postArray[1]);
            items.add(data.category2);
        }

        for (int i = 0; i < 1; i++) {
            /* You also could use Category as your CategoryItemContent directly */
            items2.add(data.category0);
            items2.add(data.postArray[0]);
            items2.add(data.postArray[1]);
            items2.add(data.postArray[0]);
            items2.add(data.postArray[1]);
        }

        for (int i = 0; i < 1; i++) {
            /* You also could use Category as your CategoryItemContent directly */
            items2.add(data.category1);
            items2.add(data.postArray[0]);
            items2.add(data.postArray[1]);
            items2.add(data.category2);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onMode(boolean ismode) {

        postViewBinder.updateMode(ismode);
        adapter.notifyDataSetChanged();
    }

    public void delCurrentItem(int position, boolean mode) {

        if (mode && categoryItemViewBinder.isOpen) {
            items2.remove(position);
            adapter.setItems(items2);
        } else {
            items.remove(position);
            historylistsize -= 1;
            adapter.setItems(items);
        }
        categoryItemViewBinder.updateHistoryList(historylistsize);
        adapter.notifyDataSetChanged();
    }

    public String getCurrentCatagory() {

        return categoryItemViewBinder.getCategory();
    }

    public void onHide(boolean ishide) {
        postViewBinder.updateHideMode(ishide);
        adapter.notifyDataSetChanged();
    }
}
