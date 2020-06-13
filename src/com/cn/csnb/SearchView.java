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
 * ������
 */
public class SearchView extends LinearLayout implements TextWatcher{
 
    private EditText et_search;     //�����
    private ImageView iv_clear;     //ɾ��ͼ��
 
    private SearchWay mSearch;      //ƥ�䷽��
    private String searchText;      //�ı�������
    private WaitThread waitThread;  //�ȴ��߳�
    private int waitTime = 200;     //��ʱ����ʱ�䣬Ĭ��200ms
    private int curTime;            //��ǰ��ʱʱ��
    private Handler mHandler;
 
    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //���ز����ļ�
        View view = LayoutInflater.from(context).inflate(R.layout.find, null);
 
        //��ȡ�ؼ�
        et_search = (EditText) view.findViewById(R.id.ed_find);
        iv_clear = (ImageView) view.findViewById(R.id.img_clear);
 
        //������հ�ť�Ĵ�����
        iv_clear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                et_search.setText("");
            }
        });
 
        //��ȡ����
        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        String s;
        //���ִ�С
        float textSize = typed.getDimension(R.styleable.SearchView_sv_textSize, 15);
        et_search.setTextSize(textSize);
        //����������
        s = typed.getString(R.styleable.SearchView_sv_text);
        if (s != null){
            et_search.setText(s);
        }
        //��ʾ����
        s = typed.getString(R.styleable.SearchView_sv_hint);
        if (s != null){
            et_search.setHint(s);
        }
        //�Ƿ���������ͼ��
//        boolean hideImg = typed.getBoolean(R.styleable.SearchView_sv_hideImg, false);
//        if (hideImg) {
//            view.findViewById(R.id.img_search).setVisibility(GONE);
//        }
        //������Դ
        typed.recycle();
 
        //���ָı����
        et_search.addTextChangedListener(this);
 
        //�첽����
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //���»ص��ӿ�
                if (0 == msg.what){
                    waitThread = null;
                    //ƥ�����ص�
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
 
        //�Ѳ�����ӵ���ǰ�ؼ���
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, params);
    }
 
    /**
     * ����ƥ�����ݵķ���
     */
    public void setSearchWay(SearchWay search){
        mSearch = search;
    }
 
    /**
     * ��ȡ�����������
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
        //ɾ��ͼ��
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
                //����������ַ����仯�����õȴ�ʱ��
                if (!searchText.equals(s.toString())) {
                    curTime = 0;
                }
            }
        }
 
        searchText = s.toString();
    }
 
    /**
     * ����������ʱʱ��
     * @param waitTime ���룬����Ϊ100ms
     */
    public void setWaitTime(int waitTime){
        this.waitTime = waitTime;
    }
 
    /**
     * ��ʱ�������߳�
     */
    private class WaitThread extends Thread{
        @Override
        public void run() {
            //�ȴ���ʱ
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
     * ����ƥ����
     */
    public static abstract class SearchWay<T>{
        /**
         * @return ����Դ
         */
        public abstract List<T> getData();
 
        /**
         * @return item���Ƿ������s
         */
        public abstract boolean matchItem(T item, String s);
 
        /**
         * �����б�
         * @param resultList ƥ������ݣ����¼��ص��б�
         */
        public abstract void update(List<T> resultList);
    }
}