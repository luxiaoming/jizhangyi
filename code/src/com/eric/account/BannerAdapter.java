package com.eric.account;  
  
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
  
public class BannerAdapter extends BaseAdapter {  
    private List<BannerBean> mList;  
    private Context mContext;  
      
    public BannerAdapter(Context context, List<BannerBean> list, int page, int pageNum) {  
        mContext = context;   
          
        mList = new ArrayList<BannerBean>();  
        int i = page * pageNum;  
        int iEnd = i + pageNum;  
        while ((i<list.size()) && (i<iEnd)) {  
            mList.add(list.get(i));  
            i++;  
        }  
    }  
    public int getCount() {  
        return mList.size();  
    }  
  
    public Object getItem(int position) {  
        return mList.get(position);  
    }  
  
    public long getItemId(int position) {  
        return position;  
    }  
  
    public View getView(int position, View convertView, ViewGroup parent) {  
    	BannerBean bean = mList.get(position);  
        BannerView appItem;  
        if (convertView == null) {  
            View v = LayoutInflater.from(mContext).inflate(R.layout.nav_banner_item, null);  
              
            appItem = new BannerView();  
            appItem.mIcon = (ImageView)v.findViewById(R.id.img);  
            appItem.mName = (TextView)v.findViewById(R.id.name);  
              
            v.setTag(appItem);  
            convertView = v;  
        } else {  
            appItem = (BannerView)convertView.getTag();  
        }  
        appItem.mIcon.setImageResource(bean.img);  
        appItem.mName.setText(bean.name);  
          
        return convertView;  
    }  
  
    class BannerView {  
        ImageView mIcon;  
        TextView mName;  
    }  
}  
