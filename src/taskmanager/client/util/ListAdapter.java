package taskmanager.client.util;

import java.util.List;

import taskmanager.client.R;
import taskmanager.client.entity.Task;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Task>{
  
	public ListAdapter(Context context, List<Task> items) {
		super(context, R.layout.item_task, items); 
	}
 
	@Override
	public View getView(int position, View convertView,
			ViewGroup parent) {
		 
		LayoutInflater li = LayoutInflater.from(getContext());
		 
		if(convertView == null) 		
			convertView = li.inflate(R.layout.item_task, parent, false);
 
		TextView name = (TextView)convertView.findViewById(R.id.text1);
		TextView text = (TextView)convertView.findViewById(R.id.text2);
		ImageView image = (ImageView)convertView.findViewById(R.id.image); 
		
		Task item = getItem(position);
		    
		String file = FileUtils.getImagesPath(getContext()) + item.getIcon();
		
		Bitmap bitmap = BitmapFactory.decodeFile(file);
		image.setImageBitmap(bitmap);
		
		name.setText(item.getName());
		text.setText(item.getText());
		 
		return convertView;
	}
	 
}
