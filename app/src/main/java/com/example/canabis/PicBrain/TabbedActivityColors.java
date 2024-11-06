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

public class TabbedActivityColors extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    // MediaPlayer instances for color sounds
    private static MediaPlayer mpblue, mpgreen, mppink, mppurple, mpwhite, mpred, mpyellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_colors);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the SectionsPagerAdapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initialize MediaPlayer instances for each color sound
        mpblue = MediaPlayer.create(this, R.raw.blue);
        mpgreen = MediaPlayer.create(this, R.raw.green);
        mppink = MediaPlayer.create(this, R.raw.pink);
        mppurple = MediaPlayer.create(this, R.raw.purple);
        mpwhite = MediaPlayer.create(this, R.raw.white);
        mpred = MediaPlayer.create(this, R.raw.red);
        mpyellow = MediaPlayer.create(this, R.raw.yellow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed_activity_colors, menu);
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
        if (mpblue != null) mpblue.release();
        if (mpgreen != null) mpgreen.release();
        if (mppink != null) mppink.release();
        if (mppurple != null) mppurple.release();
        if (mpwhite != null) mpwhite.release();
        if (mpred != null) mpred.release();
        if (mpyellow != null) mpyellow.release();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView renkturkce, renkingilizce;
        private ImageView renkfotografi;
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
            View rootView = inflater.inflate(R.layout.fragment_colors, container, false);
            renkturkce = rootView.findViewById(R.id.renkturkce);
            renkingilizce = rootView.findViewById(R.id.renkingilizce);
            renkfotografi = rootView.findViewById(R.id.renkfotografi);
            playbutton = rootView.findViewById(R.id.playbutton);

            int sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;
            setupColorInfo(sectionNumber);

            return rootView;
        }

        private void setupColorInfo(int sectionNumber) {
            switch (sectionNumber) {
                case 1:
                    setColorInfo("Azul", "Blue", R.drawable.blue, mpblue);
                    break;
                case 2:
                    setColorInfo("Verde", "Green", R.drawable.green, mpgreen);
                    break;
                case 3:
                    setColorInfo("Rojo", "Red", R.drawable.red, mpred);
                    break;
                case 4:
                    setColorInfo("Morado", "Purple", R.drawable.purple, mppurple);
                    break;
                case 5:
                    setColorInfo("Rosa", "Pink", R.drawable.pink, mppink);
                    break;
                case 6:
                    setColorInfo("Blanco", "White", R.drawable.white, mpwhite);
                    break;
                case 7:
                    setColorInfo("Amarillo", "Yellow", R.drawable.yellow, mpyellow);
                    break;

            }
        }

        private void setColorInfo(String turkce, String ingilizce, int imageResId, MediaPlayer sound) {
            renkturkce.setText(turkce);
            renkingilizce.setText(ingilizce);
            renkfotografi.setImageResource(imageResId);
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
            return 7; // Show 7 total pages
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SECTION " + (position + 1);
        }
    }
}
