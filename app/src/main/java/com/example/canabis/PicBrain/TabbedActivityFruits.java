package com.example.canabis.PicBrain;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TabbedActivityFruits extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    // MediaPlayer instances for fruit sounds
    private static MediaPlayer mpapple, mpbanana, mpcherry, mpcoconut, mpgrape, mpmelon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_fruits);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the SectionsPagerAdapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initialize MediaPlayer instances for each fruit sound
        mpapple = MediaPlayer.create(this, R.raw.apple);
        mpbanana = MediaPlayer.create(this, R.raw.banana);
        mpcherry = MediaPlayer.create(this, R.raw.cherry);
        mpcoconut = MediaPlayer.create(this, R.raw.coconut);
        mpgrape = MediaPlayer.create(this, R.raw.grape);
        mpmelon = MediaPlayer.create(this, R.raw.melon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed_activity_fruits, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, QuizMainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release MediaPlayer resources
        if (mpapple != null) mpapple.release();
        if (mpbanana != null) mpbanana.release();
        if (mpcherry != null) mpcherry.release();
        if (mpcoconut != null) mpcoconut.release();
        if (mpgrape != null) mpgrape.release();
        if (mpmelon != null) mpmelon.release();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView meyveturkce, meyveingilizce;
        private ImageView meyvefotografi;
        private ImageButton playbutton;

        public PlaceholderFragment() {
            // Required empty public constructor
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_fruits, container, false);
            meyveturkce = rootView.findViewById(R.id.meyveturkce);
            meyveingilizce = rootView.findViewById(R.id.meyveingilizce);
            meyvefotografi = rootView.findViewById(R.id.meyvefotografi);
            playbutton = rootView.findViewById(R.id.playbutton);

            int sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;
            setupFruitInfo(sectionNumber);

            return rootView;
        }

        private void setupFruitInfo(int sectionNumber) {
            switch (sectionNumber) {
                case 1:
                    setFruitInfo("Manzana", "Apple", R.drawable.apple, mpapple);
                    break;
                case 2:
                    setFruitInfo("Guineo", "Banana", R.drawable.banana, mpbanana);
                    break;
                case 3:
                    setFruitInfo("Cereza", "Cherry", R.drawable.cherry, mpcherry);
                    break;
                case 4:
                    setFruitInfo("Coco", "Coconut", R.drawable.coconut, mpcoconut);
                    break;
                case 5:
                    setFruitInfo("Uva", "Grape", R.drawable.grape, mpgrape);
                    break;
                case 6:
                    setFruitInfo("Melón", "Melon", R.drawable.melon, mpmelon);
                    break;

            }
        }

        private void setFruitInfo(String turkce, String ingilizce, int imageResId, MediaPlayer sound) {
            meyveturkce.setText(turkce);
            meyveingilizce.setText(ingilizce);
            meyvefotografi.setImageResource(imageResId);
            playbutton.setOnClickListener(v -> {
                if (sound != null) sound.start();
            });
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 6; // Show 6 total pages for each fruit
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SECTION " + (position + 1);
        }
    }
}
