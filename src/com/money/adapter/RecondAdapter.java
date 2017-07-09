package com.money.adapter;

import java.util.ArrayList;
import java.util.List;

import com.gress.moneymanager.R;
import com.money.bean.Record;
import com.money.db.RecordDao;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecondAdapter  extends BaseAdapter{
	private Context context;
	
	List<Record> list = new ArrayList<Record>();
	
	public RecondAdapter(Context context2, List<Record> list) {
		this.context = context2;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (list.get(position).getType().equals("in")) {
			//这里是收入
			View view = View.inflate(context, R.layout.iteam_recond_zhichu, null);
		
			TextView iteam_recond_zhichuday = (TextView) view.findViewById(R.id.iteam_recond_zhichuday);
			TextView iteam_recond_zhichucontent = (TextView) view.findViewById(R.id.iteam_recond_zhichucontent);
			TextView iteam_recond_zhichumoney = (TextView) view.findViewById(R.id.iteam_recond_zhichumoney);
			ImageView iteam_recond_zhichuphoto = (ImageView) view.findViewById(R.id.iteam_recond_zhichuphoto);
			iteam_recond_zhichuday.setText(list.get(position).getDay());
			iteam_recond_zhichucontent.setText(list.get(position).getContent()+"  +");
			iteam_recond_zhichumoney.setText(list.get(position).getMoney()+"");
			iteam_recond_zhichuphoto.setImageResource(list.get(position).getPhoto());
			Log.i("in", "jizhang success");
			return view;
		} else {
			//这里是支出
		
			View view = View.inflate(context, R.layout.iteam_recond_shouru, null);
			TextView iteam_recond_shouruday = (TextView) view.findViewById(R.id.iteam_recond_shouruday);
			TextView iteam_recond_shourucontent = (TextView) view.findViewById(R.id.iteam_recond_shourucontent);
			TextView iteam_recond_shourumoney = (TextView) view.findViewById(R.id.iteam_recond_shourumoney);
			ImageView iteam_recond_shouruphoto = (ImageView) view.findViewById(R.id.iteam_recond_shouruphoto);
			iteam_recond_shouruday.setText(list.get(position).getDay());
			iteam_recond_shourucontent.setText(list.get(position).getContent()+ "  -");
			iteam_recond_shourumoney.setText(list.get(position).getMoney()+"");
			iteam_recond_shouruphoto.setImageResource(list.get(position).getPhoto());
			Log.i("out", "jizhang success");
			return view;
		}
		
		
		
		
	}

}
