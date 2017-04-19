package com.example.personal.final_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Personal on 01-07-2015.
 */
public class adapter extends ArrayAdapter
{
    List list=new ArrayList();
    public adapter(Context context,int resource)
    {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }
    static class datahandler
    {
        ImageView icon;
        TextView item;
    }
    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        datahandler handler;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.row_layout,parent,false);
            handler=new datahandler();
            handler.icon=(ImageView)row.findViewById(R.id.icon);
            handler.item=(TextView)row.findViewById(R.id.my_string);
            row.setTag(handler);
        }
        else
        {
            handler=(datahandler)row.getTag();
        }
        data_provider dp;
        dp=(data_provider)this.getItem(position);
        handler.icon.setImageResource(dp.getIcon());
        handler.item.setText(dp.getItem());
        return row;
    }
}
