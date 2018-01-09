package com.ccy.janurary08;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.ccy.janurary08.TitlePopup.OnItemOnClickListener;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private TitlePopup titlePopup;
    private int index;
    private HomeFragment homefragment;
    private Fragment_Friends contactlistfragment;
    private RelativeLayout weixin, contactlist, find, mine;
    private Fragment[] fragments;
    private TextView title_text;
    private ImageView head_menu;
    private FrameLayout frameLayout;
    private ImageView[] imagebuttons;
    private TextView[] textviews;
    // 当前fragment的index
    private int currentTabIndex;
    private View view_head;
    private View view_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_head = findViewById(R.id.head_main);
        view_bottom = findViewById(R.id.head_main);
        initView();
    }

    private void initView() {
        title_text = view_head.findViewById(R.id.txt_title);
        weixin = view_bottom.findViewById(R.id.re_weixin);
        contactlist = view_bottom.findViewById(R.id.re_contactlist);
        find = view_bottom.findViewById(R.id.re_find);
        mine = view_bottom.findViewById(R.id.re_mine);
        head_menu = view_head.findViewById(R.id.head_menu);
        frameLayout=findViewById(R.id.pageFragment);
        frameLayout.setOnClickListener(this);

//        head_menu.setOnClickListener(new ClickEvent());
//        weixin.setOnClickListener(new ClickEvent());
//        contactlist.setOnClickListener(new ClickEvent());
//        find.setOnClickListener(new ClickEvent());
//        mine.setOnClickListener(new ClickEvent());
//        imagebuttons = new ImageView[4];
//        imagebuttons[0] = findViewById(R.id.bottom_home_image);
//        imagebuttons[1] = findViewById(R.id.bottom_category_image);
//        imagebuttons[2] = findViewById(R.id.bottom_service_image);
//        imagebuttons[3] = findViewById(R.id.bottom_mine_image);
//        imagebuttons[0].setSelected(true);
//        textviews = new TextView[4];
//        textviews[0] = findViewById(R.id.bottom_home_text);
//        textviews[1] = findViewById(R.id.bottom_category_text);
//        textviews[2] = findViewById(R.id.bottom_service_text);
//        textviews[3] = findViewById(R.id.bottom_mine_text);
//        textviews[0].setTextColor(0xFF45C01A);
    }

    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.head_menu:
                    initPopWindow();
                    break;
                case R.id.re_weixin:
                    index = 0;
                    if (homefragment != null) {
                        homefragment.refresh();
                    }
                    title_text.setText(R.string.app_name);
                    break;
                case R.id.re_contactlist:
                    index = 1;
                    title_text.setText(R.string.contacts);
                    break;
                case R.id.re_find:
                    index = 2;
                    title_text.setText(R.string.discover);
                    break;
                case R.id.re_mine:
                    index = 3;
                    title_text.setText(R.string.mine);
                    break;
                default:
                    break;
            }
        }
    }

    private void initPopWindow() {
        titlePopup = new TitlePopup(this, ActionBar.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titlePopup.setItemOnClickListener(onitemClick);
        titlePopup.addAction(new ActionItem(this, R.string.menu_groupchat, R.drawable.icon_menu_group));
        titlePopup.addAction(new ActionItem(this, R.string.menu_addfriend, R.drawable.icon_menu_addfriend));
        titlePopup.addAction(new ActionItem(this, R.string.menu_qrcode, R.drawable.icon_menu_sao));
        titlePopup.addAction(new ActionItem(this, R.string.menu_money, R.drawable.icon_menu_abv));

    }

    private OnItemOnClickListener onitemClick = new OnItemOnClickListener() {

        @Override
        public void onItemClick(ActionItem item, int position) {
            // mLoadingDialog.show();
            switch (position) {
//                case 0:// 发起群聊
//                    Utils.start_Activity(MainActivity.this,
//                            AddGroupChatActivity.class);
//                    break;
//                case 1:// 添加朋友
//                    Utils.start_Activity(MainActivity.this, PublicActivity.class,
//                            new BasicNameValuePair(Constants.NAME, "添加朋友"));
//                    break;
//                case 2:// 扫一扫
//                    Utils.start_Activity(MainActivity.this, CaptureActivity.class);
//                    break;
//                case 3:// 收钱
//                    Utils.start_Activity(MainActivity.this, GetMoneyActivity.class);
//                    break;
                default:
                    break;

            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pageFragment:
                Toast.makeText(this,"OK",Toast.LENGTH_LONG).show();
                break;
            case R.id.head_menu:
                initPopWindow();
                break;
            case R.id.re_weixin:
                index = 0;
                if (homefragment != null) {
                    homefragment.refresh();
                }
                title_text.setText(R.string.app_name);
                break;
            case R.id.re_contactlist:
                index = 1;
                title_text.setText(R.string.contacts);
                break;
            case R.id.re_find:
                index = 2;
                title_text.setText(R.string.discover);
                break;
            case R.id.re_mine:
                index = 3;
                title_text.setText(R.string.mine);
                break;
            default:
                break;
        }
    }
}
