package com.jwzhangjie.scrollview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteAdapter extends BaseAdapter {

	public static ListItemDelete itemDelete = null;
	private List<String> listDatas;
	private LayoutInflater mInflater;
	private Context context;

	public DeleteAdapter(Context context, List<String> listDatas) {
		mInflater = LayoutInflater.from(context);
		this.listDatas = listDatas;
		this.context = context;
	}

	@Override
	public int getCount() {
		return listDatas == null ? 0 : listDatas.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_delete, null);
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

	private Toast mToast;

	public void showInfo(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public static void ItemDeleteReset() {
		if (itemDelete != null) {
			itemDelete.reSet();
		}
	}
}
