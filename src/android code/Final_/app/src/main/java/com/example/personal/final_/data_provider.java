package com.example.personal.final_;

/**
 * Created by Personal on 01-07-2015.
 */
public class data_provider
{
    private int icon;
    private String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    public data_provider(int icon,String item)
    {
        this.setIcon(icon);
        this.setItem(item);
    }
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
