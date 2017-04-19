package com.example.personal.final_;

/**
 * Created by Personal on 29-03-2017.
 */
public class data
{
    String name;
    boolean val;
    data(String name,boolean val)
    {
        this.setName( name);
        this.setVal(val);
    }
    public void setName(String item) {
        this.name = item;
    }
    public String getName(){
        return this.name;
    }

    public boolean getVal() {
        return this.val ;
    }
    public boolean isSelected() {
        return val;
    }
    public void setVal(boolean val) {
        this.val = val;
    }
    public void setSelected(boolean selected) {
        this.val = selected;
    }
}
