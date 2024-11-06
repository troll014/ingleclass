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

public class TabbedActivityDays extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    // MediaPlayer instances for day sounds
    private static MediaPlayer mpsunday, mpmonday, mptuesday, mpwednesday, mpthursday, mpfriday, mpsaturday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_days);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the SectionsPagerAdapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initialize MediaPlayer instances for each day sound
        mpmonday = MediaPlayer.create(this, R.raw.monday);
        mptuesday = MediaPlayer.create(this, R.raw.tuesday);
        mpwednesday = MediaPlayer.create(this, R.raw.wednesday);
        mpthursday = MediaPlayer.create(this, R.raw.thursday);
        mpfriday = MediaPlayer.create(this, R.raw.friday);
        mpsaturday = MediaPlayer.create(this, R.raw.saturday);
        mpsunday = MediaPlayer.create(this, R.raw.sunday);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
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
        if (mpsunday != null) mpsunday.release();
        if (mpmonday != null) mpmonday.release();
        if (mptuesday != null) mptuesday.release();
        if (mpwednesday != null) mpwednesday.release();
        if (mpthursday != null) mpthursday.release();
        if (mpfriday != null) mpfriday.release();
        if (mpsaturday != null) mpsaturday.release();
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView gunturkce, guningilizce;
        private ImageView gunfotografi;
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
            View rootView = inflater.inflate(R.layout.fragment_days, container, false);
            gunturkce = rootView.findViewById(R.id.gunturkce);
            guningilizce = rootView.findViewById(R.id.guningilizce);
            gunfotografi = rootView.findViewById(R.id.gunfotografi);
            playbutton = rootView.findViewById(R.id.playbutton);

            int sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;
            setupDayInfo(sectionNumber);

            return rootView;
        }

        private void setupDayInfo(int sectionNumber) {
            switch (sectionNumber) {
                case 1:
                    setDayInfo("Lunes", "Monday", R.drawable.pazartesi, mpmonday);
                    break;
                case 2:
                    setDayInfo("Martes", "Tuesday", R.drawable.sali, mptuesday);
                    break;
                case 3:
                    setDayInfo("Miércoles", "Wednesday", R.drawable.carsamba, mpwednesday);
                    break;
                case 4:
                    setDayInfo("Jueves", "Thursday", R.drawable.persembe, mpthursday);
                    break;
                case 5:
                    setDayInfo("Viernes", "Friday", R.drawable.cuma, mpfriday);
                    break;
                case 6:
                    setDayInfo("Sábado", "Saturday", R.drawable.cumartesi, mpsaturday);
                    break;
                case 7:
                    setDayInfo("Domingo", "Sunday", R.drawable.pazar, mpsunday);
                    break;

            }
        }

        private void setDayInfo(String turkce, String ingilizce, int imageResId, MediaPlayer sound) {
            gunturkce.setText(turkce);
            guningilizce.setText(ingilizce);
            gunfotografi.setImageResource(imageResId);
            playbutton.setOnClickListener(v -> {
                if (sound != null) sound.start();
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
            return 7; // Show 7 total pages for each day of the week
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SECTION " + (position + 1);
        }
    }
}
