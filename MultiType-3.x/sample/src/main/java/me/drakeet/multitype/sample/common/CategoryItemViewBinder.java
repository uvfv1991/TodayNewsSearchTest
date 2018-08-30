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

package me.drakeet.multitype.sample.common;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.bilibili.BilibiliActivity;
import me.drakeet.multitype.sample.multiSelectable.MultiSelectableActivity;

/**
 * @author JX
 */
public class CategoryItemViewBinder extends ItemViewBinder<Category, CategoryItemViewBinder.ViewHolder> {

    public Context mActivity;
    public boolean isOpen = false;//默认不展开
    public boolean isMode = false;//默认不可编辑状态
    public boolean isHide = false;//默认为显示状态
    private String mcategory = null;
    private int historylistcount=0;


    public CategoryItemViewBinder(Context bilibiliActivity, int historylistsize) {
        super();
        mActivity = bilibiliActivity;
        historylistcount= historylistsize;
    }

    public CategoryItemViewBinder(MultiSelectableActivity bilibiliActivity, int o) {
        super();
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_category, parent, false));
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Category category) {
        holder.title.setText(category.title);
        if (isMode) {
            holder.mode.setText("编辑");
        } else {
            holder.mode.setText("完成");
        }


        if (historylistcount ==0){

            holder.rl_catagory.setVisibility(View.GONE);
        }

        if (historylistcount<4){
            holder.iv_toggle.setVisibility(View.GONE);
        }
        mcategory = category.title;

        if (category.title.equals("猜你想搜的") &&  isHide) {
            holder.title.setVisibility(View.GONE);
            holder.iv_toggle.setVisibility(View.GONE);
            holder.mode.setVisibility(View.GONE);
            holder.iv_hide.setVisibility(View.GONE);

        }else if (category.title.equals("查看全部关键词")){
            holder.iv_toggle.setVisibility(View.GONE);
            holder.mode.setVisibility(View.GONE);
            holder.title.setGravity(Gravity.CENTER_HORIZONTAL);
            holder.title.setTextSize(14);
            holder.title.setText("");

        }

        else if (category.title.equals("猜你想搜的") &&  !isHide) {
            holder.title.setVisibility(View.VISIBLE);
            holder.iv_toggle.setVisibility(View.VISIBLE);
            holder.iv_hide.setVisibility(View.VISIBLE);
            holder.mode.setVisibility(View.GONE);



        }


    }

    public String getCategory() {
        return mcategory;
    }

    public void updateHistoryList(int historycount) {
        this.historylistcount= historycount;
        }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         private @NonNull
         TextView title;

        private @NonNull
        TextView mode;

        private @NonNull
        ImageView iv_toggle;

        private @NonNull
        ImageView iv_key;

        private @NonNull
        ImageView iv_hide;

        private @NonNull
        RelativeLayout rl_seekey;

        private @NonNull
        RelativeLayout rl_catagory;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            mode = itemView.findViewById(R.id.enmode);
            iv_toggle = itemView.findViewById(R.id.iv_toggle);
            iv_hide = itemView.findViewById(R.id.iv_hiddle);
            iv_key= itemView.findViewById(R.id.iv_key);
            rl_catagory = itemView.findViewById(R.id.rl_category);
            rl_seekey = itemView.findViewById(R.id.rl_seekeywords);

            if (historylistcount>=4){
                iv_toggle.setOnClickListener(this);
            }

            mode.setOnClickListener(this);
            iv_hide.setOnClickListener(this);
            iv_key.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_key:
                    exeHiddle(false,this);
                    break;

                case R.id.iv_toggle:

                    exeToggle(!isOpen,this);
                    break;

                case R.id.enmode:
                    exeMode(isMode);
                    break;

                case R.id.iv_hiddle:
                    exeHiddle(true,this);
                    break;


            }


        }
    }

    /*
    * 除历史记录外全部隐藏
    * */
    private void exeHiddle(boolean ishide, ViewHolder viewHolder) {


        if (ishide){

            if (mcategory.equals("查看全部关键词")){
                viewHolder.rl_seekey.setVisibility(View.VISIBLE);
            }
            }else{
            if (mcategory.equals("查看全部关键词")){
                viewHolder.rl_seekey.setVisibility(View.GONE);
            }
        }

        ((BilibiliActivity) mActivity).onHide(ishide);
        isHide = ishide;



    }

    private void exeMode(boolean ismode) {

        ((BilibiliActivity) mActivity).onMode(ismode);
        isMode = !ismode;
    }


    private void exeToggle(boolean b, ViewHolder holder) {

        if(!holder.title.getText().equals("历史记录")){

            return;
        }



        if (!b) {
            isOpen = false;
        } else {
            isOpen = true;
        }
        ((BilibiliActivity) mActivity).onToggle(b);

    }


    public boolean getToggle() {

        return isOpen;
    }
}
