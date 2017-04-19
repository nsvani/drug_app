package com.example.personal.final_;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Personal on 29-06-2015.
 */
public class Adapter_check extends ArrayAdapter
{
    ArrayList list=new ArrayList();
    public Adapter_check(Context context,int resource,ArrayList<data> datalist)
    {
        super(context, resource,datalist);
        for(int i=0;i<datalist.size();i++)
        {
            Log.d("my_tag", "printing inside for");
            // Log.d("my_tag",(String)datalist[i]);
        }
        this.list = new ArrayList<data>();
        this.list.addAll(datalist);
    }

    static class data2
    {
        CheckBox icon;
        TextView item;
    }
    public int getCount() {
        return this.list.size();
    }
    @Override
    public Object getItem(int position) {
        Log.d("my_tag","get item");
        Log.d("my_tag",Integer.toString(position));
        return this.list.get(position);
        //return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        data2 data_handler;
        Log.d("my_tag", "entered view");
        if(convertView==null)
        {
            Log.d("my_tag","entered if");
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.row_layout_1,parent,false);
            data_handler=new data2();
            Log.d("my_tag","made object");
            data_handler.icon=(CheckBox)row.findViewById(R.id.checkBox);
            Log.d("my_tag","check");
            data_handler.item=(TextView)row.findViewById(R.id.text1);
            Log.d("my_tag","text");
            row.setTag(data_handler);
            Log.d("my_tag","set tag");
            data_handler.icon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox c=(CheckBox)v;
                    data d=(data)c.getTag();
                    d.setSelected(c.isChecked());
                }
            });
        }
        else
        {
            data_handler=(data2)row.getTag();
        }
        data d;
        Log.d("my_tag","obj data");
        d=(data)this.getItem(position);
        data_handler.icon.setText(d.getName());
        data_handler.icon.setChecked(d.isSelected());
        data_handler.icon.setTag(d);

        data_handler.item.setText(d.getName());
        Log.d("my_tag","dp");
        return row;
    }

}
