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

public class TabbedActivityGoods extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    // MediaPlayer instances for object sounds
    private static MediaPlayer mpbag, mpbicycle, mpcamera, mperaser, mpmicroscope, mpnotebook, mppencilsharpener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_goods);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the SectionsPagerAdapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initialize MediaPlayer instances for each object sound
        mpbag = MediaPlayer.create(this, R.raw.bag);
        mpbicycle = MediaPlayer.create(this, R.raw.bicycle);
        mpcamera = MediaPlayer.create(this, R.raw.camera);
        mperaser = MediaPlayer.create(this, R.raw.eraser);
        mpmicroscope = MediaPlayer.create(this, R.raw.microscope);
        mpnotebook = MediaPlayer.create(this, R.raw.notebook);
        mppencilsharpener = MediaPlayer.create(this, R.raw.pencil_sharpener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed_activity_goods, menu);
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
        if (mpbag != null) mpbag.release();
        if (mpbicycle != null) mpbicycle.release();
        if (mpcamera != null) mpcamera.release();
        if (mperaser != null) mperaser.release();
        if (mpmicroscope != null) mpmicroscope.release();
        if (mpnotebook != null) mpnotebook.release();
        if (mppencilsharpener != null) mppencilsharpener.release();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private TextView esyaturkce, esyaingilizce;
        private ImageView esyafotografi;
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
            View rootView = inflater.inflate(R.layout.fragment_goods, container, false);
            esyaturkce = rootView.findViewById(R.id.esyaturkce);
            esyaingilizce = rootView.findViewById(R.id.esyaingilizce);
            esyafotografi = rootView.findViewById(R.id.esyafotografi);
            playbutton = rootView.findViewById(R.id.playbutton);

            int sectionNumber = getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1;
            setupGoodsInfo(sectionNumber);

            return rootView;
        }

        private void setupGoodsInfo(int sectionNumber) {
            switch (sectionNumber) {
                case 1:
                    setGoodsInfo("Mochila", "Bag", R.drawable.bag, mpbag);
                    break;
                case 2:
                    setGoodsInfo("Bicicleta", "Bicycle", R.drawable.bicycle, mpbicycle);
                    break;
                case 3:
                    setGoodsInfo("Cámara", "Camera", R.drawable.camera, mpcamera);
                    break;
                case 4:
                    setGoodsInfo("Borrador", "Eraser", R.drawable.eraser, mperaser);
                    break;
                case 5:
                    setGoodsInfo("Cuaderno", "Notebook", R.drawable.notebook, mpnotebook);
                    break;
                case 6:
                    setGoodsInfo("Microscopio", "Microscope", R.drawable.microscope, mpmicroscope);
                    break;
                case 7:
                    setGoodsInfo("Sacapuntas", "Pencil Sharpener", R.drawable.pencilsharpener, mppencilsharpener);
                    break;

            }
        }

        private void setGoodsInfo(String turkce, String ingilizce, int imageResId, MediaPlayer sound) {
            esyaturkce.setText(turkce);
            esyaingilizce.setText(ingilizce);
            esyafotografi.setImageResource(imageResId);
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
            return 7; // Show 7 total pages for each good
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SECTION " + (position + 1);
        }
    }
}
