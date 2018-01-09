package com.ccy.janurary08;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccy.janurary08.TitlePopup.OnItemOnClickListener;


/**
 * Created by chanchaoyue on 2018/1/8.
 */

public class HomeFragment extends Fragment implements View.OnClickListener, OnItemOnClickListener {

    private Activity activity;
    private MainActivity parentActivity;
    private View layout, layout_head;
    ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (layout == null) {
            activity = this.getActivity();
            parentActivity = (MainActivity) getActivity();
            layout = activity.getLayoutInflater().inflate(R.layout.fragment_msg, null);
        } else {
            ViewGroup viewGroup = (ViewGroup) layout.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(layout);
            }
        }
        return layout;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(ActionItem item, int position) {

    }

    public void refresh() {
        initViews();
    }

    private void initViews() {

    }
}
