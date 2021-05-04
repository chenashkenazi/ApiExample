package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    HeroAdapter adapter;

    String currentItem = "Heroes";

    List<Hero> heroList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                currentItem = item.getTitle().toString();
                drawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HeroesViewModel heroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel.class);
        System.out.println(heroesViewModel.getHeroList());

        heroesViewModel.getHeroList().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(List<Hero> heroes) {
                adapter = new HeroAdapter(MainActivity.this, heroes);
                recyclerView.setAdapter(adapter);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView)menuItem.getActionView();


        searchView.setQueryHint("Search hero");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        switch(item.getItemId()) {
            case R.id.a_z:
                Collections.sort(heroList, Hero.heroNameAZComparator);
        }
        return super.onOptionsItemSelected(item);

    }
}