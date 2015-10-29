package com.eric.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.eric.account.view.PageIndicitorView;
import com.eric.account.view.ScrollLayout;
import com.eric.account.view.ScrollLayout.OnScreenChangeListenerDataLoad;

public class MainActivity extends Activity {
	private Context mContext;
	private ScrollLayout mNavBannerLayout, mNavBannerBodyLayout;
	private PageIndicitorView mNavBannerIndicitor, mNavBannerBodyIndicitor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		mContext = this;
		mNavBannerLayout = (ScrollLayout) findViewById(R.id.nav_banner_main);
		mNavBannerBodyLayout = (ScrollLayout) findViewById(R.id.nav_banner_body_main);
		initNavData();
		initNavBodyData();
	}

	public OnItemClickListener navBannerListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			System.out.println("current & position="
					+ mNavBannerLayout.getCurScreen() + "   " + position);
		}

	};
	public OnItemClickListener navBannerBodyListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			System.out.println("current & position="
					+ mNavBannerBodyLayout.getCurScreen() + "   " + position);
		}

	};

	private void initNavData() {
		// do nothing
		List<BannerBean> list = new ArrayList<BannerBean>();
		list.add(new BannerBean(R.string.mms, R.drawable.icon_mms));
		list.add(new BannerBean(R.string.tiaoma, R.drawable.icon_tiaoma));
		list.add(new BannerBean(R.string.voice, R.drawable.icon_voice));
		list.add(new BannerBean(R.string.record, R.drawable.icon_record));
		list.add(new BannerBean(R.string.changyong, R.drawable.icon_changyong));

		for (int i = 0; i < 2; i++) {
			GridView bannerView = new GridView(mContext);
			// get the "i" page data
			bannerView.setAdapter(new BannerAdapter(mContext, list, i , 4));
			bannerView.setNumColumns(4);
			bannerView.setOnItemClickListener(navBannerListener);
			mNavBannerLayout.addView(bannerView);
		}
		// 加载分页
		mNavBannerIndicitor = (PageIndicitorView) findViewById(R.id.nav_banner_indicator);
		mNavBannerIndicitor.bindScrollViewGroup(mNavBannerLayout);
		// 加载分页数据
		BannerDataChange mNavBannerDataChange = new BannerDataChange();
		mNavBannerDataChange.bindScrollViewGroup(mNavBannerLayout);
	}

	private void initNavBodyData() {
		// do nothing
		List<BannerBean> list = new ArrayList<BannerBean>();
		list.add(new BannerBean(R.string.personal_income, R.drawable.icon_personal_income));
		list.add(new BannerBean(R.string.mortgage_costs, R.drawable.icon_mortgage_costs));
		list.add(new BannerBean(R.string.medical_expenses, R.drawable.icon_medical_expenses));
		list.add(new BannerBean(R.string.household_expenses, R.drawable.icon_household_expenses));
		list.add(new BannerBean(R.string.other_income, R.drawable.icon_other_income));
		list.add(new BannerBean(R.string.communication_fee, R.drawable.icon_communication_fee));
		list.add(new BannerBean(R.string.public_utility_fee, R.drawable.icon_public_utility_fee));
		list.add(new BannerBean(R.string.monthly_note, R.drawable.icon_monthly_note));

		GridView bannerBodyView = new GridView(mContext);
		// get the "i" page data
		bannerBodyView.setAdapter(new BannerAdapter(mContext, list, 0, 8));
		bannerBodyView.setNumColumns(4);
		bannerBodyView.setOnItemClickListener(navBannerBodyListener);
		mNavBannerBodyLayout.addView(bannerBodyView);

		// 加载分页
		mNavBannerBodyIndicitor = (PageIndicitorView) findViewById(R.id.nav_banner_body_indicator);
		mNavBannerBodyIndicitor.bindScrollViewGroup(mNavBannerBodyLayout);
		// 加载分页数据
		BannerDataChange mNavBannerDataChange = new BannerDataChange();
		mNavBannerDataChange.bindScrollViewGroup(mNavBannerBodyLayout);
	}

	// 分页数据
	class BannerDataChange {
		public void bindScrollViewGroup(ScrollLayout scrollViewGroup) {
			scrollViewGroup
					.setOnScreenChangeListenerDataLoad(new OnScreenChangeListenerDataLoad() {
						public void onScreenChange(int currentIndex) {
						}
					});
		}

	}
}
