package com.example.briantruong626.smallworldmobile;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.view.Gravity;

import java.util.List;

/**
 * Created by felixliu on 12/7/15.
 */
public class GridPage extends FragmentGridPagerAdapter {

    private final Context mContext;
    private List<GridView.QuoteList> mData;
    private static final float MAXIMUM_CARD_EXPANSION_FACTOR = 3.0f;

    public GridPage(Context ctx, List<GridView.QuoteList> quoteLists, FragmentManager fm) {
        super(fm);
        mContext = ctx;
        mData = quoteLists;
    }


    @Override
    public Fragment getFragment(int row, int column) {
        GridView.QuoteList quoteList = mData.get(row);
        CardFragment fragment = CardFragment.create(quoteList.getTitle(column), quoteList.getText(column));
        fragment.setCardGravity(Gravity.BOTTOM);
        fragment.setExpansionEnabled(true);
        fragment.setExpansionDirection(CardFragment.EXPAND_DOWN);
        fragment.setExpansionFactor(MAXIMUM_CARD_EXPANSION_FACTOR);
        return fragment;
    }

    @Override
    public int getRowCount() {
        return mData.size();
    }

    @Override
    public int getColumnCount(int row) {
        return mData.get(row).getPageCount();
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        return mContext.getResources().getDrawable(mData.get(row).getImageResource());
    }



}
