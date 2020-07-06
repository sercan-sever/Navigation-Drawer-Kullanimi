package com.info.navigationdrawerkullanimi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import javax.xml.transform.sax.TemplatesHandler;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigationView);
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        fragment = new FragmentBirinci();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTutucu,fragment).commit();



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawer,toolbar,0,0);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //Toolbar üzerine toggle buttonu getirir.


        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);

        navigationView.setNavigationItemSelectedListener(this);

    }


    // Geri tuşuna basıldığı anda ne olacağını burada tanımladık....
    @Override
    public void onBackPressed() {

        // drawer açık ise ....
        if (drawer.isDrawerOpen(GravityCompat.START)){

            //Geri tuşuna basıldığı anda başlangıç durumuna geri getir demek.....
            drawer.closeDrawer(GravityCompat.START);
        }
        // drawer kapalı ise ...
        else {

            Intent intent = new Intent(Intent.ACTION_MAIN);

            //Ana ekrana dönüleceği için home kategorisi seçiliyor...
            intent.addCategory(Intent.CATEGORY_HOME);

            //Intent i aktif etmek için kullanılır.
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Intenti yerleştirmeye yarar...
            startActivity(intent);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.nav_item_birinci){

            fragment = new FragmentBirinci();
        }
        if (item.getItemId() == R.id.nav_item_ikinci){

            fragment = new FragmentIkinci();
        }
        if (item.getItemId() == R.id.nav_item_ucuncu){

            fragment = new FragmentUcuncu();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTutucu,fragment).commit();

        //Navigation Drawer üzerinde bir item' a tıklandıktan sonra Navigation Drawer kapanır.
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }




}