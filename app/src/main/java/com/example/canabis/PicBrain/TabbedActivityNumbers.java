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

public class TabbedActivityNumbers extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    // MediaPlayer instances for number sounds
    private static MediaPlayer[] numberSounds = new MediaPlayer[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_numbers);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initialize MediaPlayer instances for each number sound
        numberSounds[0] = MediaPlayer.create(this, R.raw.one);
        numberSounds[1] = MediaPlayer.create(this, R.raw.two);
        numberSounds[2] = MediaPlayer.create(this, R.raw.three);
        numberSounds[3] = MediaPlayer.create(this, R.raw.four);
        numberSounds[4] = MediaPlayer.create(this, R.raw.five);
        numberSounds[5] = MediaPlayer.create(this, R.raw.six);
        numberSounds[6] = MediaPlayer.create(this, R.raw.seven);
        numberSounds[7] = MediaPlayer.create(this, R.raw.eight);
        numberSounds[8] = MediaPlayer.create(this, R.raw.nine);
        numberSounds[9] = MediaPlayer.create(this, R.raw.ten);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed_activity_numbers, menu);
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
        for (MediaPlayer sound : numberSounds) {
            if (sound != null) {
                sound.release();
            }
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView sayiturkce, sayingilizce;
        private ImageView sayifotografi;
        private ImageButton playbutton;

        public PlaceholderFragment() {
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
            View rootView = inflater.inflate(R.layout.fragment_numbers, container, false);
            sayiturkce = rootView.findViewById(R.id.sayiturkce);
            sayingilizce = rootView.findViewById(R.id.sayiingilizce);
            sayifotografi = rootView.findViewById(R.id.sayifotografi);
            playbutton = rootView.findViewById(R.id.playbutton);

            int sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;
            setupNumberInfo(sectionNumber);

            return rootView;
        }

        private void setupNumberInfo(int sectionNumber) {
            String[] espanolNumbers= {"Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez"};

            String[] englishNumbers = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
            int[] imageResources = {R.drawable._1, R.drawable._2, R.drawable._3, R.drawable._4, R.drawable._5,
                    R.drawable._6, R.drawable._7, R.drawable._8, R.drawable._9, R.drawable._10};

            int index = sectionNumber - 1;
            sayiturkce.setText(espanolNumbers[index]);
            sayingilizce.setText(englishNumbers[index]);
            sayifotografi.setImageResource(imageResources[index]);

            playbutton.setOnClickListener(v -> {
                if (numberSounds[index] != null) {
                    numberSounds[index].start();
                }
            });
        }
    }

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
            return 10; // Show 10 total pages for each number
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SECTION " + (position + 1);
        }
    }
}
