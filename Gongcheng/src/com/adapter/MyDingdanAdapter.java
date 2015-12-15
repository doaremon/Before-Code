nupackage com.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gongcheng.R;
import com.work.Work3;

public class MyDingdanAdapter extends BaseAdapter{
	TextView textView1;
	TextView textView2;
	TextView textView3;
	TextView textView4;
	TextView textView5;
	TextView textView6;
	Context context;
	List<Work3> list;
	LayoutInflater inflater;


	public MyDingdanAdapter(Context context, List<Work3> list) {
		super();
		this.context = context;
		this.list = list;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
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
		// TODO Auto-generated method stub
		convertView=inflater.inflate(R.layout.fifteen, null);
		textView1=(TextView)convertView.findViewById(R.id.dingdanhaoid);
		textView2=(TextView)convertView.findViewById(R.id.yiyuanmingchengid);
		textView3=(TextView)convertView.findViewById(R.id.keshimingchengid);
		textView4=(TextView)convertView.findViewById(R.id.huanzhemingchengid);
		textView5=(TextView)convertView.findViewById(R.id.dingdanzhuangtaiid);
		textView6=(TextView)convertView.findViewById(R.id.jiuzhengshijianid);

		textView1.setText(list.get(position).getOrderid());
		textView2.setText(list.get(position).getHospitalname());
		textView3.setText(list.get(position).getDeptname());
		textView4.setText(list.get(position).getDoctorname());
		textView5.setText(list.get(position).getState());
		textView6.setText(list.get(position).getNoonname());
		return convertView;
	}

}
