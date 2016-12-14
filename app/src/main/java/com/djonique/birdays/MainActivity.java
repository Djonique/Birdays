package com.djonique.birdays;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.djonique.birdays.adapters.PagerAdapter;
import com.djonique.birdays.database.DBHelper;
import com.djonique.birdays.dialogs.NewPersonDialogFragment;
import com.djonique.birdays.fragments.AllFragment;
import com.djonique.birdays.fragments.FamousFragment;
import com.djonique.birdays.fragments.MonthFragment;
import com.djonique.birdays.model.Person;

public class MainActivity extends AppCompatActivity implements
        NewPersonDialogFragment.AddingPersonListener, AllFragment.DeletingRecordListener {

    public static final String NEW_PERSON_DIALOG_TAG = "NewPersonDialogFragment";
    public DBHelper dbHelper;
    MonthFragment monthFragment;
    AllFragment allFragment;
    FamousFragment famousFragment;
    private FragmentManager fragmentManager;
    private FloatingActionButton fab;

    public FloatingActionButton getFab() {
        return fab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(getApplicationContext());
        fragmentManager = getFragmentManager();

        initUI();
    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == PagerAdapter.FAMOUS_FRAGMENT_POSITION) {
                    fab.hide();
                } else fab.show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        monthFragment = ((MonthFragment)
                pagerAdapter.getItem(PagerAdapter.MONTH_FRAGMENT_POSITION));
        allFragment = ((AllFragment) pagerAdapter.getItem(PagerAdapter.ALL_FRAGMENT_POSITION));
        famousFragment = ((FamousFragment)
                pagerAdapter.getItem(PagerAdapter.FAMOUS_FRAGMENT_POSITION));

        SearchView searchView = ((SearchView) findViewById(R.id.searchView));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                allFragment.findPerson(newText);
                monthFragment.findPerson(newText);
                return false;
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newPersonDialogFragment = new NewPersonDialogFragment();
                newPersonDialogFragment.show(fragmentManager, NEW_PERSON_DIALOG_TAG);
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
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPersonAdded(Person person) {
        monthFragment.addPerson(person);
        allFragment.addPerson(person, true);
        Toast.makeText(this, R.string.record_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPersonAddedCancel() {
    }

    @Override
    public void onRecordDeleted(long timeStamp) {
        monthFragment.deleteRecord(timeStamp);
    }
}


