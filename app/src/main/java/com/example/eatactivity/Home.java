package com.example.eatactivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatactivity.Common.Common;
import com.example.eatactivity.Interface.ItemClickListener;
import com.example.eatactivity.MenuViewHolder.MenuViewHolder;
import com.example.eatactivity.Model.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseDatabase database;
    DatabaseReference category;
    TextView txtFullName;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, MenuViewHolder>adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        //Init firebase
        database=FirebaseDatabase.getInstance();
        category=database.getReference("Category");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent=new Intent(Home.this,Cart.class);
                startActivity(cartIntent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_menu, R.id.nav_cart, R.id.nav_orders,R.id.nav_log_out)
                .setDrawerLayout(drawer)
                .build();
        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,
                drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        View headerView=navigationView.getHeaderView(0);
        navigationView.getBackground().setColorFilter(0x80000000, PorterDuff.Mode.MULTIPLY);
        txtFullName=(TextView)headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentUser.getName());

        recycler_menu= findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        loadMenu();
    }
    public void loadMenu(){
         adapter=new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,
                 R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Category category, int i) {
                menuViewHolder.txtMenuName.setText(category.getName());
                Picasso.get().load(category.getImage())
                        .into(menuViewHolder.imageView);
                final Category clickItem=category;
                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onclick(View view, int position, boolean isLongClick) {
                        Intent foodList=new Intent(Home.this,FoodList.class);
                        foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/

    public  boolean onOptionsItemSelected(MenuItem item){
        int id1=item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nav_cart){

        }else if (id==R.id.nav_orders){

        }else if (id==R.id.nav_log_out){

        }else if (id==R.id.nav_menu){

        }
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
