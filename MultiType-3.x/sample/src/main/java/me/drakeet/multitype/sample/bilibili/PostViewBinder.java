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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;

/**
 * @author drakeet
 */
public class PostViewBinder extends ItemViewBinder<Post, PostViewBinder.ViewHolder> {

    private boolean mode;
    private boolean hide;
    public Context mActivity;
    public ViewHolder viewHolder;



    public PostViewBinder(Context bilibiliActivity) {
        super();
        mActivity = bilibiliActivity;

    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {

        viewHolder = new ViewHolder(inflater.inflate(R.layout.item_post, parent, false));
        return viewHolder;
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Post post) {
        holder.setData(post, mode,hide);
    }

    public void updateMode(boolean ismode) {

        mode = ismode;
    }

    public void updateHideMode(boolean ishide) {

        hide= ishide;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private @NonNull
        ImageView cover;
        private @NonNull
        TextView title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.delete);
            title = itemView.findViewById(R.id.title);
            cover.setOnClickListener(this);
        }


        void setData(@NonNull final Post post, boolean mode, boolean hide) {
            title.setText(post.title);
                cover.setImageResource(R.mipmap.close);
                if (mode && ((BilibiliActivity) mActivity).getCurrentCatagory().equals("历史记录") ) {
                    cover.setVisibility(View.VISIBLE);
                } else  {
                    cover.setVisibility(View.GONE);
                }


            if (((BilibiliActivity) mActivity).getCurrentCatagory().equals("猜你想搜的")
                    && hide){
                title.setText("");
            }else if (((BilibiliActivity) mActivity).getCurrentCatagory().equals("猜你想搜的")
                    && !hide){
                title.setText(post.title);
            }

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.delete:

                    exeDelete(this.getPosition());
                    break;
            }

        }

        private void exeDelete(int position) {

            ((BilibiliActivity) mActivity).delCurrentItem(position, mode);
        }
    }
}
