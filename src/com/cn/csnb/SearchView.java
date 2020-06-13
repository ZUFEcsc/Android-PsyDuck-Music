package com.cn.csnb;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
 
import java.util.ArrayList;
import java.util.List;
 
/**
 * Created by Administrator on 2016/8/31.
 * 搜索框
 */
public class SearchView extends LinearLayout implements TextWatcher{
 
    private EditText et_search;     //输入框
    private ImageView iv_clear;     //删除图标
 
    private SearchWay mSearch;      //匹配方法
    private String searchText;      //改变后的文字
    private WaitThread waitThread;  //等待线程
    private int waitTime = 200;     //延时搜索时间，默认200ms
    private int curTime;            //当前延时时间
    private Handler mHandler;
 
    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //加载布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.find, null);
 
        //获取控件
        et_search = (EditText) view.findViewById(R.id.ed_find);
        iv_clear = (ImageView) view.findViewById(R.id.img_clear);
 
        //设置清空按钮的触发器
        iv_clear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                et_search.setText("");
            }
        });
 
        //读取属性
        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        String s;
        //文字大小
        float textSize = typed.getDimension(R.styleable.SearchView_sv_textSize, 15);
        et_search.setTextSize(textSize);
        //搜索框文字
        s = typed.getString(R.styleable.SearchView_sv_text);
        if (s != null){
            et_search.setText(s);
        }
        //提示文字
        s = typed.getString(R.styleable.SearchView_sv_hint);
        if (s != null){
            et_search.setHint(s);
        }
        //是否隐藏搜索图标
//        boolean hideImg = typed.getBoolean(R.styleable.SearchView_sv_hideImg, false);
//        if (hideImg) {
//            view.findViewById(R.id.img_search).setVisibility(GONE);
//        }
        //回收资源
        typed.recycle();
 
        //文字改变监听
        et_search.addTextChangedListener(this);
 
        //异步处理
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //更新回调接口
                if (0 == msg.what){
                    waitThread = null;
                    //匹配结果回调
                    List searchList = new ArrayList();
                    List list = mSearch.getData();
                    if (list != null) {
                        for (Object o : list) {
                            if (mSearch.matchItem(o, searchText)) {
                                searchList.add(o);
                            }
                        }
                        mSearch.update(searchList);
                    }
                }
            }
        };
 
        //把布局添加到当前控件中
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, params);
    }
 
    /**
     * 设置匹配数据的方法
     */
    public void setSearchWay(SearchWay search){
        mSearch = search;
    }
 
    /**
     * 获取搜索框的文字
     */
    public String getText(){
        return et_search.getText().toString();
    }
 
    public SearchWay getSearchWay() {
        return mSearch;
    }


    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
 
    }
 

    public void onTextChanged(CharSequence s, int start, int before, int count) {
 
    }
 

    public void afterTextChanged(Editable s) {
        //删除图标
        if (s.toString().isEmpty()){
            iv_clear.setVisibility(GONE);
        }else {
            iv_clear.setVisibility(VISIBLE);
        }
 
        if (mSearch != null) {
            if (null == waitThread) {
                waitThread = new WaitThread();
                waitThread.start();
            } else {
                //搜索框的文字发生变化就重置等待时间
                if (!searchText.equals(s.toString())) {
                    curTime = 0;
                }
            }
        }
 
        searchText = s.toString();
    }
 
    /**
     * 设置搜索延时时间
     * @param waitTime 毫秒，精度为100ms
     */
    public void setWaitTime(int waitTime){
        this.waitTime = waitTime;
    }
 
    /**
     * 延时搜索的线程
     */
    private class WaitThread extends Thread{
        @Override
        public void run() {
            //等待延时
            for (curTime = 0; curTime < waitTime; curTime += 100){
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
 
            mHandler.sendEmptyMessage(0);
        }
    }
 
    /**
     * 用于匹配项
     */
    public static abstract class SearchWay<T>{
        /**
         * @return 数据源
         */
        public abstract List<T> getData();
 
        /**
         * @return item中是否包含有s
         */
        public abstract boolean matchItem(T item, String s);
 
        /**
         * 更新列表
         * @param resultList 匹配的数据，重新加载到列表
         */
        public abstract void update(List<T> resultList);
    }
}