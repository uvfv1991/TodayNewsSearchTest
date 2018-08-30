package me.drakeet.multitype.sample.bilibili;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.sample.R;
import me.drakeet.multitype.sample.common.Category;
import me.drakeet.multitype.sample.normal.TextItem;

public class JsonData {

    private static final String PREFIX = "搜索历史";

    private Post post00 = new Post(0, R.drawable.img_00, PREFIX);
    private Post post01 = new Post(1,R.drawable.img_01, PREFIX);
    private Post post10 = new Post(2,R.drawable.img_10, PREFIX);
    private Post post11 = new Post(3,R.drawable.img_11, PREFIX);

    //栏名称
    Category category3 = new Category("热点词");

    //栏名称
    Category category0 = new Category("历史记录");

    //栏名称
    Category category1 = new Category("猜你想搜的");

    //栏名称
    Category category2 = new Category("查看全部关键词");

    //填充内容
    Post[] postArray = { post00, post01, post10, post11 };


    List<TextItem> textItems=new ArrayList<TextItem>();

        {
        textItems.add(new TextItem("农科院1"));
        textItems.add(new TextItem("农科院2"));
        textItems.add(new TextItem("农科院3"));
        }

    /*
    *   List<Post> postList= new ArrayList<>();

    {
        postList.add(new Post(5,R.drawable.img_00, PREFIX ));
        postList.add(new Post(6,R.drawable.img_00, PREFIX ));
        postList.add(new Post(7,R.drawable.img_00, PREFIX));
        postList.add(new Post(8,R.drawable.img_00, PREFIX));
        postList.add(new Post(9,R.drawable.img_00, PREFIX));
        postList.add(new Post(10,R.drawable.img_00, PREFIX));
    }

    List<Post> postList2= new ArrayList<>();

    {
        postList2.add(new Post(5,R.drawable.img_00, PREFIX + ""));
        postList2.add(new Post(6,R.drawable.img_00, PREFIX + ""));
        postList2.add(new Post(7,R.drawable.img_00, PREFIX + ""));
        postList2.add(new Post(8,R.drawable.img_00, PREFIX + ""));
    }
    *
    * */



}