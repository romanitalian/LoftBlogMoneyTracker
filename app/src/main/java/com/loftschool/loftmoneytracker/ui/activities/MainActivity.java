package com.loftschool.loftmoneytracker.ui.activities;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.MenuItem;

import com.activeandroid.query.Select;
import com.loftschool.loftmoneytracker.R;
import com.loftschool.loftmoneytracker.TrackerApplication;
import com.loftschool.loftmoneytracker.database.Categories;
import com.loftschool.loftmoneytracker.rest.RestService;
import com.loftschool.loftmoneytracker.ui.fragments.CategoriesFragment_;
import com.loftschool.loftmoneytracker.ui.fragments.ExpensesFragment_;
import com.loftschool.loftmoneytracker.ui.fragments.SettingsFragment_;
import com.loftschool.loftmoneytracker.ui.fragments.StatisticsFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private RestService restService;

    @ViewById
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.navigation_view)
    NavigationView navView;

    @OptionsItem(android.R.id.home)
    void drawer() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawers();
        } else drawerLayout.openDrawer(navView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ExpensesFragment_()).commit();
        }
        setupWindowAnimations();
        restService = new RestService();
        testRest();
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= 21) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);

            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setReturnTransition(slide);
        }
    }

    @Background
    void testRest() {
        String authToken = TrackerApplication.getToken(this);
        String googleToken = TrackerApplication.getGoogleToken(this);
        Log.e("LOG_TAG", " " + authToken);
//        AddCategoryModel category = restService.addCategory("Rest", googleToken, authToken);
//        Log.e("LOG_TAG", "Category name: " + category.getData().getTitle()
//                + ", category id: " + category.getData().getId());
    }

    @AfterViews
    void ready() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        setCategories();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                selectItem(menuItem);
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private List<Categories> getCategories() {
        return new Select().from(Categories.class).execute();
    }

    public void setCategories() {
        if (getCategories().isEmpty()) {
            new Categories("Food").save();
            new Categories("Stuff").save();
            new Categories("Clothes").save();
            new Categories("Fun").save();
            new Categories("Other").save();
        }
    }

    private void selectItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.drawer_expenses:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ExpensesFragment_()).addToBackStack(null).commit();
                break;
            case R.id.drawer_categories:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new CategoriesFragment_()).addToBackStack(null).commit();
                break;
            case R.id.drawer_statistics:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new StatisticsFragment_()).addToBackStack(null).commit();
                break;
            case R.id.drawer_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new SettingsFragment_()).addToBackStack(null).commit();
                break;
        }
    }
}