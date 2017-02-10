package me.ele.weexlpd;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import java.io.UnsupportedEncodingException;

import me.ele.weexlpd.https.WXHttpManager;
import me.ele.weexlpd.https.WXHttpTask;
import me.ele.weexlpd.https.WXRequestListener;

/**
 * Created by mobilexu on 2017/2/8.
 */

public abstract class BaseWeexActivity extends AppCompatActivity implements IWXRenderListener {
    public static final String TAG = "BaseWeexActivity";
    private static final String DEFAULT_IP = "10.12.73.21";
    private WXSDKInstance mWXSDKInstance;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton refreshActionButton;
    private FloatingActionButton connectDebugActionButton;
    private FloatingActionButton moreActionButton;
    private FrameLayout contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setTitle("Weex 页面");
        setContentView(R.layout.acitivity_base_weex);
        init();
        weexInit();
    }

    private void init() {

        contentView = (FrameLayout) findViewById(R.id.content_view);

        if (false) {
            return;
        }
        ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub);
        viewStub.inflate();
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.menu_float_action);
        refreshActionButton = (FloatingActionButton) findViewById(R.id.fab_refresh);
        connectDebugActionButton = (FloatingActionButton) findViewById(R.id.fab_connect_debug);
        moreActionButton = (FloatingActionButton) findViewById(R.id.fab_more);
        floatingActionMenu.setClosedOnTouchOutside(true);
        refreshActionButton.setLabelText("刷新页面");
        refreshActionButton.setVisibility(View.VISIBLE);
        connectDebugActionButton.setLabelText("连接debug");
        moreActionButton.setLabelText("更多");
        floatingActionMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionMenu.toggle(true);
            }
        });
        refreshActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWXfromService(getIndexUrl());
            }
        });

        connectDebugActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDebugEnvironment(true, DEFAULT_IP);
                WXSDKEngine.reload();
            }
        });

        moreActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "开发中", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 连接debug 服务器
     *
     * @param enable
     * @param host
     */
    private void initDebugEnvironment(boolean enable, String host) {
        WXEnvironment.sRemoteDebugMode = enable;
        WXEnvironment.sRemoteDebugProxyUrl = "ws://" + host + ":8088/debugProxy/native";
    }

    private void weexInit() {
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        mWXSDKInstance.render(WXFileUtils.loadAsset(getJsNameFromAsset(), this));
    }

    protected void destoryWeexInstance() {
        if (mWXSDKInstance != null) {
            mWXSDKInstance.registerRenderListener(null);
            mWXSDKInstance.destroy();
            mWXSDKInstance = null;
        }
    }

    protected void createWeexInstance() {
        destoryWeexInstance();

        Rect outRect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
    }

    protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(
                    ActionBar.DISPLAY_HOME_AS_UP
                            | ActionBar.DISPLAY_SHOW_HOME
                            | ActionBar.DISPLAY_USE_LOGO
                            | ActionBar.DISPLAY_SHOW_TITLE);
        }
    }

    protected abstract String getJsNameFromAsset();

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        Log.i(TAG, "view渲染成功");
        contentView.removeAllViews();
        contentView.addView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        Log.e(TAG, msg);
    }

    @Override
    public void onBackPressed() {
        Log.e("USER ACTION", "BACK");
        WXSDKManager.getInstance().fireEvent(mWXSDKInstance.getInstanceId(), "_root", "androidback");
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * http://10.12.73.21:12580/dist/index.weex.js
     *
     * @return
     */
    private static String getIndexUrl() {
        return "http://" + DEFAULT_IP + ":12580/dist/index.weex.js";
    }

    private void loadWXfromService(final String url) {

        if (mWXSDKInstance != null) {
            mWXSDKInstance.destroy();
        }

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);

        WXHttpTask httpTask = new WXHttpTask();
        httpTask.url = url;
        httpTask.requestListener = new WXRequestListener() {

            @Override
            public void onSuccess(WXHttpTask task) {
                Log.i(TAG, "into--[http:onSuccess] url:" + url);
                try {
                    mWXSDKInstance.render(TAG, new String(task.response.data, "utf-8"), null, null, WXRenderStrategy.APPEND_ASYNC);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(WXHttpTask task) {
                Toast.makeText(getApplicationContext(), "network error!", Toast.LENGTH_SHORT).show();
            }
        };

        WXHttpManager.getInstance().sendRequest(httpTask);
    }
}
