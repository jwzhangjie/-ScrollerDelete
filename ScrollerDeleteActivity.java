package com.jwzhangjie.scrollview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollerDeleteActivity extends FragmentActivity implements
		OnItemClickListener {

	private Toast mToast;
	private ScrollListviewDelete listviewDelete;
	private DeleteAdapter adapter;
	private String[] datas = { "第一项", "第二项", "第三项", "第四项", "第五项", "第六项", "第七项",
			"第八项", "第九项", "第十项" };
	private List<String> listDatas = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_scroller_delete);
		int len = datas.length;
		for (int i = 0; i < len; i++) {
			listDatas.add(datas[i]);
		}
		listviewDelete = (ScrollListviewDelete) findViewById(android.R.id.list);
		adapter = new DeleteAdapter();
		listviewDelete.setAdapter(adapter);
		listviewDelete.setOnItemClickListener(this);
	}

	class DeleteAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listDatas.size();
		}

		@Override
		public Object getItem(int position) {
			return listDatas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_delete, null);
				holder.itemData = (TextView) convertView
						.findViewById(R.id.itemData);
				holder.btnDelete = (Button) convertView
						.findViewById(R.id.btnDelete);
				holder.btnNao = (Button) convertView.findViewById(R.id.btnNao);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.btnDelete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showInfo("点击删除了");
				}
			});
			holder.itemData.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					showInfo("点击了数据： " + listDatas.get(position));
				}
			});
			holder.btnNao.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showInfo("点击了闹铃");
				}
			});
			holder.itemData.setText(listDatas.get(position));
			return convertView;
		}

		class ViewHolder {
			TextView itemData;
			Button btnDelete;
			Button btnNao;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		showInfo("onItemClick : " + position);
	}

	public void showInfo(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

}
