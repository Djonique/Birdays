package com.djonique.birdays.activities;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.djonique.birdays.Ads;
import com.djonique.birdays.R;
import com.djonique.birdays.adapters.PagerAdapter;
import com.djonique.birdays.alarm.AlarmHelper;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.dialogs.NewPersonDialogFragment;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.fragments.FamousFragment;
import com.djonique.birdays.fragments.MonthFragment;
import com.djonique.birdays.model.Person;
import com.djonique.birdays.utils.ConstantManager;
import com.djonique.birdays.utils.MyApplication;

public class MainActivity extends AppCompatActivity implements
        NewPersonDialogFragment.AddingPersonListener, AllFragment.DeletingRecordListener {

    public DBHelper dbHelper;
    MonthFragment monthFragment;
    AllFragment allFragment;
    FamousFragment famousFragment;
    private FragmentManager fragmentManager;
    private FloatingActionButton fab;
    private SearchView searchView;
    private PagerAdapter pagerAdapter;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ads.showBanner(this);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);

        AlarmHelper.getInstance().init(getApplicationContext());

        if (savedInstanceState == null) {
            monthFragment = ((MonthFragment)
                    pagerAdapter.getItem(PagerAdapter.MONTH_FRAGMENT_POSITION));
            allFragment = ((AllFragment) pagerAdapter.getItem(PagerAdapter.ALL_FRAGMENT_POSITION));
            famousFragment = ((FamousFragment)
                    pagerAdapter.getItem(PagerAdapter.FAMOUS_FRAGMENT_POSITION));
        }

        dbHelper = new DBHelper(getApplicationContext());
        fragmentManager = getFragmentManager();

        initUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
    }

    private void initUI() {
        appBarLayout = ((AppBarLayout) findViewById(R.id.appbar));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

                appBarLayout.setExpanded(true, true);

                if (position == PagerAdapter.FAMOUS_FRAGMENT_POSITION) {
                    fab.hide();
                    searchView.setVisibility(View.GONE);
                } else {
                    fab.show();
                    searchView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        searchView = ((SearchView) findViewById(R.id.searchView));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (allFragment != null) {
                    allFragment.findPerson(newText);
                }
                if (monthFragment != null) {
                    monthFragment.findPerson(newText);
                }
                return false;
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newPersonDialogFragment = new NewPersonDialogFragment();
                newPersonDialogFragment.show(fragmentManager, ConstantManager.NEW_PERSON_DIALOG_TAG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_github:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.github_url))));
                break;
            case R.id.menu_rate_app:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.googleplay_app_link))));
                break;
            case R.id.menu_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(ConstantManager.TEXT_PLAIN);
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.googleplay_app_link));
                startActivity(Intent.createChooser(intent, getString(R.string.app_name)));

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPersonAdded(Person person) {
        monthFragment.addPerson(person);
        allFragment.addPerson(person, true);
        Snackbar snackbar = Snackbar.make(findViewById(R.id.container),
                R.string.record_added, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void onPersonAddedCancel() {
    }

    @Override
    public void onRecordDeleted(long timeStamp) {
        monthFragment.deleteRecord(timeStamp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        if (resultCode == RESULT_OK) {
            int position = data.getIntExtra(ConstantManager.POSITION, 0);
            allFragment.removePersonDialog(position);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        allFragment.addPersonFromDB();
        monthFragment.addPersonFromDB();
    }
}


