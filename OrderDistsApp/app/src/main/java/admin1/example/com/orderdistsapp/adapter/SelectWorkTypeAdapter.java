package admin1.example.com.orderdistsapp.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.bean.IndustryItem;

/**
 * @Created by admin
 * @Created on 2018/9/30.
 **/
public class SelectWorkTypeAdapter extends BaseQuickAdapter<IndustryItem> implements View.OnClickListener {


    public SelectWorkTypeAdapter(int layoutResId, List<IndustryItem> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, IndustryItem industryItems) {
        baseViewHolder.setText(R.id.tv_indu_type, industryItems.getName())
                .setChecked(R.id.cb_industry,industryItems.getChecked())
                .setOnClickListener(R.id.rl_content,this)   ;
    }

    @Override
    public void onClick(View v) {

    }
}
