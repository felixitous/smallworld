package com.example.briantruong626.smallworldmobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felixliu on 12/7/15.
 */
public class GridView extends Activity {
    private List<QuoteList> mQuoteLists = new ArrayList<QuoteList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        setupData();
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new GridPage(this, mQuoteLists, getFragmentManager()));
    }

    private void setupData() {
        setupDataDrax();
        setupDataGamora();
        setupDataGroot();
    }

    private void setupDataDrax() {
        List<String> quotes = new ArrayList<String>();
        quotes.add(getString(R.string.Text));
        mQuoteLists.add(new QuoteList(getString(R.string.Interest1), R.drawable.dogs, quotes));
    }

    private void setupDataGamora() {
        List<String> quotes = new ArrayList<String>();
        quotes.add(getString(R.string.Text));
        mQuoteLists.add(new QuoteList(getString(R.string.Interest2), R.drawable.tea, quotes));
    }

    private void setupDataGroot() {
        List<String> quotes = new ArrayList<String>();
        quotes.add(getString(R.string.Text));
        mQuoteLists.add(new QuoteList(getString(R.string.Interest3), R.drawable.soccer, quotes));
    }

    public static class QuoteList {
        private int mImageResource;
        private List<String> mQuotes;
        private String mName;

        public QuoteList(String name, int imageResource, List<String> quotes) {
            mName = name;
            mImageResource = imageResource;
            mQuotes = quotes;
        }

        public String getTitle(int page) {
            // Only the first page has a title
            if (page == 0) {
                return mName;
            } else {
                return null;
            }
        }

        public String getText(int page) {
            // First has no text
            if (page == 0) {
                return null;
            } else {
                return mQuotes.get(page - 1);
            }
        }

        public int getPageCount() {
            return mQuotes.size() + 1;
        }

        public int getImageResource() {
            return mImageResource;
        }
    }

}