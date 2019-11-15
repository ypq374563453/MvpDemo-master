package com.wenjie.mvp.view.login;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenjie.R;
import com.wenjie.base.BaseMvpActivity;
import com.wenjie.entity.MilitaryNews;
import com.wenjie.mvp.presenter.news.NewsPresenter;
import com.wenjie.mvp.view.news.GirlBean;
import com.wenjie.mvp.view.news.GirlsAdapter;
import com.wenjie.mvp.view.news.GirlsAdapter2;
import com.wenjie.mvp.view.news.NewsContract;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends BaseMvpActivity<NewsContract.View , NewsContract.Presenter> implements NewsContract.View , SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView_girls;
    private List<GirlBean> girlBeans = new ArrayList<>();
    private StaggeredGridLayoutManager layoutManager;
    private GridLayoutManager layoutManager2;
    private GirlsAdapter girlsAdapter;
    private GirlsAdapter2 girlsAdapter2;
    private Button btn_change;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isGrid = false;
    private boolean isLoadMore;//是否是底部加载更多
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mSwipeRefreshLayout = findViewById(R.id.type_item_swipfreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(NewsActivity.this);
        //实现首次自动显示加载提示
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        recyclerView_girls = this.findViewById(R.id.recyclerView_girls);
        layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView_girls.setLayoutManager(layoutManager);
        recyclerView_girls.setPadding(0, 0, 0, 0);
        recyclerView_girls.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        layoutManager2 = new GridLayoutManager(this,3);

        girlsAdapter2 = new GirlsAdapter2(this);
        mPresenter.getNews();
        initData();
        btn_change = this.findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGrid) {
                    recyclerView_girls.setLayoutManager(layoutManager);
                    recyclerView_girls.setAdapter(girlsAdapter);
                }else{
                    recyclerView_girls.setLayoutManager(layoutManager2);
                    recyclerView_girls.setAdapter(girlsAdapter2);
                }
                isGrid = !isGrid;
            }
        });

        girlsAdapter2.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
//                if (PAGE_COUNT == mTempPageCount) {
//                    return;
//                }
//                isLoadMore = true;
//                PAGE_COUNT = mTempPageCount;
                initData();
            }
        });
    }

    private void initData() {
        for(int i = 1;i < 70;i ++) {
            GirlBean girlBean = new GirlBean();
            girlBean.setDesc(i+"");
            girlBean.setUrl("file:///android_asset/" + i + ".jpg");
            girlBeans.add(girlBean);
        }

        girlsAdapter = new GirlsAdapter(this);
        recyclerView_girls.setAdapter(girlsAdapter);
        girlsAdapter.setGirlBeans(girlBeans);
        girlsAdapter2.replaceData(girlBeans);
        recyclerView_girls.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //防止第一行到顶部有空白区域
                layoutManager.invalidateSpanAssignments();
            }
        });
        mSwipeRefreshLayout.setRefreshing(false);
//        girlsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                if (PAGE_COUNT == mTempPageCount) {
//                    return;
//                }
//                isLoadMore = true;
////                PAGE_COUNT = mTempPageCount;
//                initData();
//            }
//        });
    }
    @Override
    protected NewsContract.Presenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void setData(List<MilitaryNews> result) {
        mSwipeRefreshLayout.setRefreshing(false);
        girlsAdapter.notifyItemRangeInserted(1, 20);
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
//        PAGE_COUNT = 1;
//        mTempPageCount = 2;
//        mAllList.clear();
//        initData();
    }

    @Override
    public void onError() {
//        if (isLoadMore) {
//            mAdapter.loadMoreFail();
//        } else {
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
    }
}
