package org.buttondesigner;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.buttondesigner.listutils.CustomAdapter;
import org.buttondesigner.listutils.ListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ButtonDesignerMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "ListView Title 1", "ListView Title 2", "ListView Title 3", "ListView Title 4",
            "ListView Title 5", "ListView Title 6", "ListView Title 7", "ListView Title 8",
    };

    String[] listviewShortDescription = new String[]{
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
    };

    private ListView listLayoutContainer;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_designer);
        initializeBarElements();

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_description", listviewShortDescription[i]);
            aList.add(hm);
        }

        String[] from = {"listview_title", "listview_description"};
        int[] to = {R.id.layout_name, R.id.layout_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_items, from, to);
        listLayoutContainer = (ListView) findViewById(R.id.created_layouts_list);
        listLayoutContainer.setAdapter(simpleAdapter);

        registerForContextMenu(listLayoutContainer);
        listLayoutContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "You press a item list", Toast.LENGTH_SHORT).show();
                view.setSelected(true);
            }
        });

        TextView empty_message = (TextView) findViewById(R.id.empty_list_text);
        if(!aList.isEmpty()){
            empty_message.setVisibility(View.INVISIBLE);
        }
        else{
            empty_message.setVisibility(View.VISIBLE);
        }
    }

    /**
     * This method initialize the toolbar and the navigation bar
     */
    private void initializeBarElements(){
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        //navigation bar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.button_designer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.layouts_created_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        switch (item.getItemId()){

            case R.id.upload_item_list:
                Snackbar snackbar1 = Snackbar.make(fab, "Your layout was uploaded", Snackbar.LENGTH_SHORT);

                View sbView1 = snackbar1.getView();
                TextView textView1 = (TextView) sbView1.findViewById(android.support.design.R.id.snackbar_text);
                textView1.setTextColor(Color.WHITE);
                snackbar1.show();
                break;

            case R.id.edit_item_list:
                Snackbar snackbar2 = Snackbar.make(fab, "Preparing to edit", Snackbar.LENGTH_SHORT);

                View sbView2 = snackbar2.getView();
                TextView textView2 = (TextView) sbView2.findViewById(android.support.design.R.id.snackbar_text);
                textView2.setTextColor(Color.WHITE);
                snackbar2.show();
                break;

            case R.id.delete_item_list:
                Snackbar snackbar3 = Snackbar.make(fab, "Your layout was deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar = Snackbar.make(fab, "Your layout was restored", Snackbar.LENGTH_SHORT);
                                View sbView = snackbar.getView();
                                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                                textView.setTextColor(Color.WHITE);
                                snackbar.show();
                            }
                        });

                snackbar3.setActionTextColor(getResources().getColor(R.color.colorAccent));

                View sbView3 = snackbar3.getView();
                TextView textView3 = (TextView) sbView3.findViewById(android.support.design.R.id.snackbar_text);
                textView3.setTextColor(Color.WHITE);
                snackbar3.show();
                break;
        }

        return super.onContextItemSelected(item);
    }


}
