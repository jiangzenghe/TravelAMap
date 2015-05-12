package com.tiger.mobile.amap.remote.model;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Cate")
public class Category extends Model { 
    @Column(name = "Name")
    public String name;
    @Column(name = "colorId")
    public String color;
}
