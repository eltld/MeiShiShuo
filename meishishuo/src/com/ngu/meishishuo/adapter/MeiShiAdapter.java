package com.ngu.meishishuo.adapter;

import java.util.List;

import com.ngu.meishishuo.utils.AllUrl;
import com.ngu.meishishuo.utils.SettingsUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.ngu.meishishuo.R;
import com.ngu.meishishuo.bean.MeiShi;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @author zhoufeng06@qq.com
 * @time 2016年4月19日-下午3:44:43
 */
public class MeiShiAdapter extends BaseAdapter {
	private Context context;
	private List<MeiShi> list;
	private DisplayImageOptions options;
	private boolean noImage;//无图模式
	
	public MeiShiAdapter(Context context, List<MeiShi> list) {
		this.context = context;
		this.list = list;
		noImage=SettingsUtil.get(context, SettingsUtil.NO_IMAGE);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.loading)
				.showImageForEmptyUri(R.drawable.loading)
				.showImageOnFail(R.drawable.loading)
				.cacheInMemory(true)
				.cacheOnDisk(false)
				.considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
	}
	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (list != null) {
			return list.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder hold;
		
		if (convertView == null) {
			hold = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.fragment_meishi_item, null);
			convertView.setTag(hold);
		}else {
			hold=(ViewHolder) convertView.getTag();
		}
		
		hold.imageView=(ImageView) convertView.findViewById(R.id.item_meishi_imageview);
		hold.tv_name=(TextView) convertView.findViewById(R.id.item_meishi_textview_name);
		hold.tv_description=(TextView) convertView.findViewById(R.id.item_meishi_textview_description);
		hold.tv_count=(TextView) convertView.findViewById(R.id.item_meishi_textview_count);
		hold.tv_comment=(TextView) convertView.findViewById(R.id.item_meishi_textview_comment);
		//listview滑动过程中，值不变
		MeiShi item=list.get(position);
		 
		String url=AllUrl.imageUrl+item.getImg()+AllUrl.imageSize;
		if(!noImage){
			ImageLoader.getInstance()
			.displayImage(url, hold.imageView, options, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingStarted(String imageUri, View view) {
					
				}
	
				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					
				}
	
				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					
				}
			}, new ImageLoadingProgressListener() {
				@Override
				public void onProgressUpdate(String imageUri, View view, int current, int total) {
					
				}
			});
		}
		hold.tv_name.setText(item.getName());
		hold.tv_description.setText(item.getDescription());
		hold.tv_count.setText(item.getCount()+"人浏览");
		hold.tv_comment.setText(item.getRcount());
		
		return convertView;
	}

	private class ViewHolder {
		public ImageView imageView;
		public TextView tv_name,tv_description,tv_count,tv_comment;
	}
}
