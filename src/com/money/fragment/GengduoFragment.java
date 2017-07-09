package com.money.fragment;

import com.book.utils.ToastUtils;
import com.gress.moneymanager.LoginActivity;
import com.gress.moneymanager.MycenterActivity;
import com.gress.moneymanager.R;
import com.gress.moneymanager.WarnActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GengduoFragment extends Fragment{
	private ImageView gengduo_touxiang;  //点击头像的跳转
	private TextView gengduo_yijianfankui;//点击意见反馈的跳转
	private TextView gengduo_TJHY;//推荐好友
	private TextView gengduo_hpds;//好评打赏5
	private TextView fg_gengduo_tv; //用户名
	private TextView gengduo_gywm; //关于我们
	private TextView gengduo_tixing;//记账提醒
	private SharedPreferences sp;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_gengduo, container, false);
		initView(view);
		inDate();
		initListen(view);
		return view;
	}
	
	private void inDate() {
		if (!sp.getString("user", "").equals("")) {
			fg_gengduo_tv.setText(sp.getString("user", ""));
			gengduo_touxiang.setImageResource(R.drawable.login_mine_pic);
		} 
	}

	//从登陆界面或者个人界面跳转回更多界面的反馈
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==1&&resultCode==2) {
			//从登陆哪里反馈回来的数据进行处理
				final String name = data.getStringExtra("name");
				fg_gengduo_tv.setText(name);
				gengduo_touxiang.setImageResource(R.drawable.login_mine_pic);
		}else if ((requestCode==1&&resultCode==3)) {
			//从个人中心的退出登录退出登陆反馈的信息
			final String name = data.getStringExtra("name");
			fg_gengduo_tv.setText(name);
			gengduo_touxiang.setImageResource(R.drawable.mine_pic_nor);
		}
		
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
	private void initListen(final View view1) {
		//点击头像的跳转到登录界面或者个人中心
		gengduo_touxiang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (fg_gengduo_tv.getText().toString().equals("请登录")) {
					//跳转到登录界面 请求码requestCode=1
					Intent intent = new Intent();
					intent.setClass(getActivity(), LoginActivity.class);
					startActivityForResult(intent, 1);
				} else {
					//跳转到个人中心请求码requestCode=1
					Intent intent = new Intent();
					intent.setClass(getActivity(), MycenterActivity.class);
					intent.putExtra("name", fg_gengduo_tv.getText().toString());
					startActivityForResult(intent, 1);
				}
			}
		});
		//点击意见反馈的跳转
		gengduo_yijianfankui.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); 
				View view = View.inflate(getActivity(), R.layout.itemgengduo_yijifankui, null);
		       // View view = View.inflate(this, R.layout.itemgengduo_yijifankui, null);  
		        final AlertDialog alertDialog = builder.create();  
		        alertDialog.setView(view);  
		        alertDialog.show(); 
		        Button iteam_yijian_submit = (Button) view.findViewById(R.id.iteam_yijian_submit);
				Button iteam_yijian_cancel = (Button) view.findViewById(R.id.iteam_yijian_cancel);
				iteam_yijian_cancel.setOnClickListener(new OnClickListener() {  
			            @Override  
			            public void onClick(View v) {  
			                alertDialog.dismiss();  
			            }  
			        });  
				iteam_yijian_submit.setOnClickListener(new OnClickListener() {  
		            @Override  
		            public void onClick(View v) {  
		                // TODO Auto-generated method stub  
		            	ToastUtils.showToast(getActivity(), "Thank you for your valuable opinions");
		                alertDialog.dismiss();  
		            }  
		        });  
			}
		});
		//推荐好友
		gengduo_TJHY.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); 
				View view = View.inflate(getActivity(), R.layout.itemgengduo_tjhy, null);
		       // View view = View.inflate(this, R.layout.itemgengduo_yijifankui, null);  
		        final AlertDialog alertDialog = builder.create();  
		        alertDialog.setView(view);  
		        alertDialog.show(); 
		        ImageView qq = (ImageView) view.findViewById(R.id.itemgengduo_tjhy_qq);
		        ImageView wx = (ImageView) view.findViewById(R.id.itemgengduo_tjhy_wx);
		        ImageView pyq = (ImageView) view.findViewById(R.id.itemgengduo_tjhy_pyq);
		        ImageView wb = (ImageView) view.findViewById(R.id.itemgengduo_tjhy_wb);
		         qq.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ToastUtils.showToast(getContext(), "请连接到网络");
					}
				});
				 wx.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										ToastUtils.showToast(getContext(), "请连接到网络");
									}
								});
				 pyq.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							ToastUtils.showToast(getContext(), "请连接到网络");
						}
					});
				 wb.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							ToastUtils.showToast(getContext(), "请连接到网络");
						}
					});
			}
		});
		///好评打赏
		gengduo_hpds.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showRewardDialog(v);
			}
			private void showRewardDialog(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				v=View.inflate(getActivity(), R.layout.show_reward_dialog, null);
				ViewGroup parent = (ViewGroup) v.getParent();
				if (parent != null) {
					parent.removeAllViews();
				}
				builder.setView(v);
				final AlertDialog alertDialog = builder.create();
				alertDialog.show();
				
				Button mBtn_sure = (Button)v.findViewById(R.id.reward_btn_sure);
				Button mBtn_cancel = (Button)v.findViewById(R.id.reward_btn_cancel);
				mBtn_sure.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.cancel();
						ToastUtils.showToast(getContext(), "感谢您的评价！！");
					}
				});
				mBtn_cancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ToastUtils.showToast(getContext(), "客官，您忘了评价~~");
						alertDialog.cancel();
					}
				});
			}
		});
		//关于我们
		gengduo_gywm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); 
				View view = View.inflate(getActivity(), R.layout.itemgengduo_gywm, null);
		        final AlertDialog alertDialog = builder.create();  
		        alertDialog.setView(view);  
		        alertDialog.show(); 
			}
		});
		//记账提醒
		gengduo_tixing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//ToastUtils.showToast(getContext(), "敬请期待");
				Intent intent = new Intent();
				intent.setClass(getActivity(), WarnActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initView(View view) {
		sp=getActivity().getSharedPreferences("user_info",Context.MODE_APPEND);
		fg_gengduo_tv = (TextView) view.findViewById(R.id.fg_gengduo_tv);
		gengduo_touxiang =  (ImageView) view.findViewById(R.id.gengduo_touxiang);
		gengduo_yijianfankui = (TextView) view.findViewById(R.id.gengduo_yijianfankui);
		gengduo_TJHY = (TextView) view.findViewById(R.id.gengduo_TJHY);
		gengduo_hpds = (TextView) view.findViewById(R.id.gengduo_hpds);
		gengduo_gywm = (TextView) view.findViewById(R.id.gengduo_gywm);
		gengduo_tixing = (TextView) view.findViewById(R.id.gengduo_tixing);
	}
	
	

}
