package space.growl.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.File;

public class HomeActivity extends AppCompatActivity {
    private static final String LOG_TAG = "HomeActivity";
    public static final String PREFS_NAME = "GrowlSpacePreferences";

    Drawer.Result result;
    Toolbar toolbar;
    SharedPreferences prefs;

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FeedFragment())
                    .commit();
        }

        initVars();
    }

    private void initVars() {
        initPreferences();
        initActionBar();
        initDrawer();
    }

    private void initPreferences() {
        prefs = getSharedPreferences(PREFS_NAME, 0);
        boolean firstRun = prefs.getBoolean("firstRun", true);
        if (firstRun) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
        }
    }

    private void initActionBar() {
        toolbar = (Toolbar) findViewById(R.id.growl_toolbar);
        setSupportActionBar(toolbar);
    }
    private void initDrawer() {

//        AccountHeader.Result headerResult = new AccountHeader()
//                .withActivity(this)
//                .withHeaderBackground(R.drawable.abc_cab_background_top_material)
//                .build();
        result = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
//                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.feed),
                        new PrimaryDrawerItem().withName(R.string.profile)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        switch (i) {
                            case 0:
                                Log.d(LOG_TAG, "Position = " + i);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container, new FeedFragment())
                                        .commit();
                                break;
                            case 1:
                                Log.d(LOG_TAG, "Position = " + i);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container, new ProfileFragment())
                                        .commit();
                                break;
                        }
                    }
                })
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            trimCache(this);
            // Toast.makeText(this,"onDestroy " ,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
