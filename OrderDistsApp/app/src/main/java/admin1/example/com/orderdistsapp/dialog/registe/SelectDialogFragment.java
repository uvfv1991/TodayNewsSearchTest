package admin1.example.com.orderdistsapp.dialog.registe;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import admin1.example.com.orderdistsapp.Config;
import admin1.example.com.orderdistsapp.R;
import admin1.example.com.orderdistsapp.adapter.SelectWorkTypeAdapter;
import admin1.example.com.orderdistsapp.bean.IndustryItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by admin
 * @Created on 2018/9/18.
 * <p>
 * single type BaseQuickAdapter使用
 **/
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class SelectDialogFragment extends android.support.v4.app.DialogFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private SelectWorkTypeAdapter selectWorkTypeAdapter;
    private IndustryItem industryItem;
    public List<IndustryItem> induList = new ArrayList<IndustryItem>();
    private View rootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, rootView);

        initData();
        initViews();
    }

    private void initData() {
        industryItem = new IndustryItem();


        for (String indu : Config.induString) {

            industryItem.setName(indu);
            industryItem.setChecked(false);

            induList.add(industryItem);
        }

        selectWorkTypeAdapter = new SelectWorkTypeAdapter(R.layout.select_indust_item_layout, induList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_select_business_dialog, container, false);



        }
        return rootView;


    }

    private void initViews() {

        recyclerView.setAdapter(selectWorkTypeAdapter);

    }
}
