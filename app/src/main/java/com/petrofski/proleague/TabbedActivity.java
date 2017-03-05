package com.petrofski.proleague;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.petrofski.proleague.model.Match;
import com.petrofski.proleague.model.Team;

import java.io.File;
import java.io.FileOutputStream;


public class TabbedActivity extends AppCompatActivity implements LeagueTableFragment.OnListFragmentInteractionListener, MatchesFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Activity thisActivity;
    private ProgressBar progressBar2;
    private LinearLayout mainProfileView;
    private ImageView profilePic;
    private File f = new File(Environment.getExternalStorageDirectory(), "ProLeague");

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        thisActivity = this;
        this.progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        this.mainProfileView = (LinearLayout) findViewById(R.id.main_profile_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setChangeListener(mViewPager);

        AdView mAdView = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    // Geen alternatief gevonden om aan de profile picture te geraken
    public void setChangeListener(ViewPager mViewPager) {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2) {
                    setPicture();
                    setVersion();
                    setEmail();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onListFragmentInteraction(Team team) {

    }

    @Override
    public void onFragmentInteraction(Match match) {

    }

    public void logout(View v) {
        ParseUser.logOutInBackground(new LogOutCallback() {
            public void done(ParseException e) {
                Intent intent = new Intent(thisActivity, LoginActivity.class);
                thisActivity.finish();
                startActivity(intent);
            }
        });
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MatchesFragment();
                case 1:
                    return new LeagueTableFragment();
                case 2:
                    return new ProfileFragment();
                default:
                    return new LeagueTableFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.title_matchday);
                case 1:
                    return getResources().getString(R.string.title_leatue_table);
                case 2:
                    return getResources().getString(R.string.title_profile);
            }
            return null;
        }

    }



    // Taking picture and saving it to /ProLeague/Pic.jpg
    public void takePicture(View v) {
        // Vanuit Fragment niet mogelijk om methodes aan te roepen
        // Hier profilePic setten, anders null
        this.profilePic = (ImageView) findViewById(R.id.profile_image);

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        if (!f.exists()) {
            f.mkdirs();
        }
        File photo = new File(f, "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, 100);

    }

    // When picture is taken, compress and set profile image
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize=8;
                        Bitmap bmp=BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options);
                        try {
                            FileOutputStream out = new FileOutputStream(imageUri.getPath());
                            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            out.flush();
                            out.close();
                        } catch(Exception e) {}

                        profilePic.setImageBitmap(bitmap);
                        Toast.makeText(thisActivity, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(thisActivity, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }


    // Set picture
    private void setPicture() {
        this.profilePic = (ImageView) findViewById(R.id.profile_image);
        if(profilePic != null) {
            File image = new File(f, "Pic.jpg");
            if(image.exists()) {
                BitmapFactory.decodeFile(image.getAbsolutePath());
                Bitmap bm = BitmapFactory.decodeFile(image.getAbsolutePath());
                profilePic.setImageBitmap(bm);
            } else {
                profilePic.setImageResource(R.drawable.login);
            }
        }
    }

    public void setVersion() {
        try {
            TextView versionView = (TextView) findViewById(R.id.app_version);
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionCode = pInfo.versionName;
            versionView.setText(getResources().getString(R.string.app_version) + versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setEmail() {
        try {
            TextView emailView = (TextView) findViewById(R.id.profile_mail);
            emailView.setText(ParseUser.getCurrentUser().getEmail());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
