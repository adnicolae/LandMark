package ro.dand.attractionsmate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import static android.R.color.transparent;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String extra_message = extras.getString("MARKER_DESCRIPTION");
        String marker_title = extras.getString("MARKER_TITLE");
        final Double marker_latitude = extras.getDouble("MARKER_LATITUDE");
        final Double marker_longitude = extras.getDouble("MARKER_LONGITUDE");
        int marker_photo = extras.getInt("MARKER_PHOTO");
       // ViewGroup layout = (ViewGroup) findViewById(R.id.toolbar_layout);
        int grey = Color.parseColor("#b3b3b3");
        final int black = Color.parseColor("#000000");
        //layout.setBackgroundResource(marker_photo);
        ImageView imgView = (ImageView) findViewById(R.id.imgHeader);
        imgView.setBackgroundResource(marker_photo);
        //collapsingToolbarLayout.setBackgroundResource(marker_photo);
        //collapsingToolbarLayout.setContentScrimColor(white);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setCollapsedTitleTextColor(grey);
//        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
//        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (verticalOffset == collapsingToolbarLayout.getHeight() + toolbar.getHeight()) {
//                    final Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.drawable.abc_ic_ab_back_material);
//                    upArrow.setColorFilter(black, PorterDuff.Mode.SRC_ATOP);
//                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
//                }
//            }
//        });
//        getSupportActionBar().setHomeAsUpIndicator();
//        collapsingToolbarLayout.setStatusBarScrimColor(white);
//        collapsingToolbarLayout.setCollapsedTitleTextColor(black);
//        collapsingToolbarLayout.setContentScrim(null);

        //String message = intent.getStringExtra(MapViewFragment.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.description_text_view);
        textView.setTextSize(20);
        textView.setText(extra_message);
        setTitle(marker_title);
        //String markerTitle = intent.getStringExtra(MapViewFragment.MARKER_TITLE);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(
                        "http://maps.google.com/maps?q=" + marker_latitude +","+ marker_longitude));
//                http://maps.google.com/maps?f=d&daddr=
                startActivity(intent);
            }
        });

        //ViewGroup layout = (ViewGroup) findViewById(R.id.toolbar_layout);
        //layout.setBackground();
    }
}
