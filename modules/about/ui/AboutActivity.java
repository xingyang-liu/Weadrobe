package com.xiecc.Weadrobe.modules.about.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.xiecc.Weadrobe.R;
import com.xiecc.Weadrobe.base.BaseActivity;
import com.xiecc.Weadrobe.common.utils.StatusBarUtil;
import com.xiecc.Weadrobe.common.utils.Util;

public class AboutActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @Bind(R.id.tv_version)
    TextView mTvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        StatusBarUtil.setImmersiveStatusBar(this);
        StatusBarUtil.setImmersiveStatusBarToolbar(mToolbar,this);
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        mTvVersion.setText(String.format("当前版本: %s (Build %s)", Util.getVersion(this), Util.getVersionCode(this)));
        mToolbarLayout.setTitleEnabled(false);
        mToolbar.setTitle(getString(R.string.app_name));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void goToHtml(String url) {
        Uri uri = Uri.parse(url);   //指定网址
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);           //指定Action
        intent.setData(uri);                            //设置Uri
        startActivity(intent);        //启动Activity
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }
}
