package com.jwzhangjie.scrollview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.jwzhangjie.scrollview.ScrollListviewDelete.ItemClickListener;

public class ScrollerDeleteActivity extends FragmentActivity implements ItemClickListener {

	private Toast mToast;
	private ScrollListviewDelete listviewDelete;
	private DeleteAdapter adapter;
	private String[] datas = { "第一项", "第二项", "第三项", "第四项", "第五项", "第六项", "第七项",
			"第八项", "第九项", "第十项","第1一项", "第1二项", "第1三项", "第1四项", "第1五项", "第1六项", "第1七项",
			"第1八项", "第1九项" };
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
		adapter = new DeleteAdapter(this, listDatas);
		listviewDelete.setAdapter(adapter);
		listviewDelete.setOnItemClickListener(this);
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


	@Override
	public void onItemClick(int position) {
		showInfo("onItemClick: "+adapter.getItem(position));
	}

}
