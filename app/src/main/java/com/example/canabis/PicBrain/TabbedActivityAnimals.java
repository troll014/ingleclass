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

public class TabbedActivityAnimals extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    // MediaPlayer instances for animal sounds
    private static MediaPlayer mpcow, mpdog, mpdonkey, mpelephant, mpfrog, mpgoat, mphorse, mplion, mprooster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_animals);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the SectionsPagerAdapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initialize MediaPlayer instances for each animal sound
        mpcow = MediaPlayer.create(this, R.raw.cow);
        mpdog = MediaPlayer.create(this, R.raw.dog);
        mpdonkey = MediaPlayer.create(this, R.raw.donkey);
        mpelephant = MediaPlayer.create(this, R.raw.elephant);
        mpfrog = MediaPlayer.create(this, R.raw.frog);
        mpgoat = MediaPlayer.create(this, R.raw.goat);
        mphorse = MediaPlayer.create(this, R.raw.horse);
        mplion = MediaPlayer.create(this, R.raw.lion);
        mprooster = MediaPlayer.create(this, R.raw.rooster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_activity_animals, menu);
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
        if (mpcow != null) mpcow.release();
        if (mpdog != null) mpdog.release();
        if (mpdonkey != null) mpdonkey.release();
        if (mpelephant != null) mpelephant.release();
        if (mpfrog != null) mpfrog.release();
        if (mpgoat != null) mpgoat.release();
        if (mphorse != null) mphorse.release();
        if (mplion != null) mplion.release();
        if (mprooster != null) mprooster.release();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView hayvanturkce, hayvaningilizce;
        private ImageView hayvanfotografi;
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
            View rootView = inflater.inflate(R.layout.fragment_animals, container, false);
            hayvanturkce = rootView.findViewById(R.id.hayvanturkce);
            hayvaningilizce = rootView.findViewById(R.id.hayvaningilizce);
            hayvanfotografi = rootView.findViewById(R.id.hayvanfotografi);
            playbutton = rootView.findViewById(R.id.playbutton);

            int sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;
            setupAnimalInfo(sectionNumber);

            return rootView;
        }

        private void setupAnimalInfo(int sectionNumber) {
            switch (sectionNumber) {
                case 1:
                    setAnimalInfo("Vaca", "Cow", R.drawable.cow, mpcow);
                    break;
                case 2:
                    setAnimalInfo("Perro", "Dog", R.drawable.dog, mpdog);
                    break;
                case 3:
                    setAnimalInfo("Burro", "Donkey", R.drawable.donkey, mpdonkey);
                    break;
                case 4:
                    setAnimalInfo("Elefante", "Elephant", R.drawable.elephant, mpelephant);
                    break;
                case 5:
                    setAnimalInfo("Sapo", "Frog", R.drawable.frog, mpfrog);
                    break;
                case 6:
                    setAnimalInfo("Cabra", "Goat", R.drawable.goat, mpgoat);
                    break;
                case 7:
                    setAnimalInfo("caballo", "Horse", R.drawable.horse, mphorse);
                    break;
                case 8:
                    setAnimalInfo("Leon", "Lion", R.drawable.lion, mplion);
                    break;
                case 9:
                    setAnimalInfo("Gallo", "Rooster", R.drawable.rooster, mprooster);
                    break;
            }
        }

        private void setAnimalInfo(String turkce, String ingilizce, int imageResId, MediaPlayer sound) {
            hayvanturkce.setText(turkce);
            hayvaningilizce.setText(ingilizce);
            hayvanfotografi.setImageResource(imageResId);
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
            return 9; // Show 9 total pages
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SECTION " + (position + 1);
        }
    }
}
