package com.byd.mydiary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Diary> mData;

    public DiaryAdapter(Context context, ArrayList data) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = View.inflate(mContext, R.layout.diary_item, null);
        TextView title = (TextView) view.findViewById(R.id.diary_title);
        TextView date = (TextView) view.findViewById(R.id.create_date);
        title.setText("[" + mData.get(i).get_id() + "] " + mData.get(i).getTitle());
        date.setText(mData.get(i).getDate());

        return view;
    }
}
