package com.loftschool.loftmoneytracker.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Constantine on 14.09.2015.
 */

@Table(name = "Categories")
public class Categories extends Model {

    @Column(name = "name")
    public String name;

    public Categories() {
        super();
    }

    public Categories(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public List<Expenses> expenses() {
        return getMany(Expenses.class, "category");
    }
}
