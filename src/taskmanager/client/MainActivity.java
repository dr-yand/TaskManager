package taskmanager.client;

import java.util.ArrayList;
import java.util.List;

import taskmanager.client.entity.Task;
import taskmanager.client.task.GetListTask;
import taskmanager.client.util.ListAdapter;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements GetListTask.OnLoadListResult{

	private ListView mTasks;
	private ProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initDlg();
	}
	
	private void initDlg(){
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("Данные загружаются");
	}
	
	private void initView(){
		mTasks = (ListView)findViewById(R.id.tasks_listview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 
		int id = item.getItemId();
		if (id == R.id.action_refresh) {
			mProgressDialog.show();
			new GetListTask<MainActivity>(this)
				.execute(new Void[]{});
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLoadListResult(List<Task> tasks) {
		mProgressDialog.dismiss();
		ListAdapter adapter = new ListAdapter(this, tasks);
		mTasks.setAdapter(adapter);
	}
}
