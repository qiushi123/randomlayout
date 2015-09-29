package com.shitou.randomlayout;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shitou.googleplay.lib.randomlayout.StellarMap;
import com.shitou.googleplay.lib.randomlayout.StellarMap.Adapter;
import com.shitou.randomlayou.R;

public class MainActivity extends Activity {
	private StellarMap stellarMap;
	private ArrayList<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 简单的设置要显示的文字源
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 11; j++) {
				list.add("第" + i + "组" + "文字" + j);
			}
		}

		stellarMap = new StellarMap(this);
		// 1.设置内部的TextView距离四周的内边距
		int padding = 15;
		stellarMap.setInnerPadding(padding, padding, padding, padding);
		stellarMap.setAdapter(new StellarMapAdapter());
		// 设置默认显示第几组的数据
		stellarMap.setGroup(0, true);// 这里默认显示第0组
		// 设置x和y方向上的显示的密度
		stellarMap.setRegularity(15, 15);// 如果值设置的过大，有可能造成子View摆放比较稀疏

		// 把fragment显示至界面,new出fragment对象
		FrameLayout fl = (FrameLayout) findViewById(R.id.fl);
		fl.addView(stellarMap);
	}

	class StellarMapAdapter implements Adapter {
		/**
		 * 返回多少组数据
		 */
		@Override
		public int getGroupCount() {
			return 3;
		}

		/**
		 * 每组多少个数据
		 */
		@Override
		public int getCount(int group) {
			return 11;
		}

		/**
		 * group: 当前是第几组 position:是当前组的position
		 */
		@Override
		public View getView(int group, int position, View convertView) {
			final TextView textView = new TextView(MainActivity.this);
			// 根据group和组中的position计算出对应的在list中的位置
			int listPosition = group * getCount(group) + position;
			textView.setText(list.get(listPosition));

			// 1.设置随机的字体大小(随机大小)
			Random random = new Random();
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,
					random.nextInt(15) + 14);// 14-29
			// 2.上色，设置随机的字体颜色
			// 如果三原色的值过大会偏白色，过小会偏黑色，所以应该随机一个中间的颜色的值
			int red = random.nextInt(150) + 50;// 50-199
			int green = random.nextInt(150) + 50;// 50-199
			int blue = random.nextInt(150) + 50;// 50-199
			int textColor = Color.rgb(red, green, blue);// 在rgb三原色的基础上混合出一种新的颜色
			textView.setTextColor(textColor);
			return textView;
		}

		/**
		 * 虽然定义了，但是并没有什么乱用
		 */
		@Override
		public int getNextGroupOnPan(int group, float degree) {
			return 0;
		}

		/**
		 * 当前组缩放完成之后下一组加载哪一组的数据 group： 表示当前是第几组
		 */
		@Override
		public int getNextGroupOnZoom(int group, boolean isZoomIn) {
			// 0->1->2->0
			return (group + 1) % getGroupCount();
		}

	}

}
