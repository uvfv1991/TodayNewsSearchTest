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

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class HorizontalPostsViewBinder extends ItemViewBinder<PostList, HorizontalPostsViewBinder.ViewHolder> {


    public static PostsAdapter adapter = new PostsAdapter();

    private ViewHolder mViewHolder;
    private List<Post> mPost = new ArrayList<>();

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_horizontal_list, parent, false);
        mViewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PostList postList) {

        mPost= postList.posts;
        holder.setPosts(mPost);
        assertGetAdapterNonNull();

    }

    public void setDisPlayCount(List<Post> posts) {

        mPost= posts;

    }


    private void assertGetAdapterNonNull() {
        if (getAdapter() == null) {
            throw new NullPointerException("getAdapter() == null");
        }
    }


    public static final class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;


        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.post_list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            new LinearSnapHelper().attachToRecyclerView(recyclerView);

            recyclerView.setAdapter(adapter);
        }


        private void setPosts(List<Post> posts) {
            adapter.setPosts(posts);

            adapter.notifyDataSetChanged();
        }
    }
}