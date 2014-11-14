package taskmanager.client.task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import taskmanager.client.entity.Task;
import taskmanager.client.util.Config;
import taskmanager.client.util.FileUtils;
import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;

public class GetListTask<T extends Context&GetListTask.OnLoadListResult> extends AsyncTask<Void, Void, String>{
	
	private T mListener;
	 
	private List<Task> mTasks;
	
	public interface OnLoadListResult{
		public void onLoadListResult(List<Task> tasks);
	}
	
    public GetListTask(T context){
    	this.mListener=context;
    	this.mTasks = new ArrayList<Task>();
    }
    
	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpclient = new DefaultHttpClient();
		String url = Config.SITE_URL;
		HttpGet httpget = new HttpGet(url); 
        HttpResponse response;            
        String responseString = null;
        try {            	 
        	response = httpclient.execute(httpget);
                           
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString("windows-1251");
                Log.i("response", responseString);
                parseResponse(responseString);   
            } else{
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }    
        } catch (Exception e) {
        	e.printStackTrace();
        }                
		return responseString; 
	}
	
	private void parseResponse(String response){
		try{	        	
			JSONArray tasksArray= new JSONArray(response); 
        	for(int i=0;i<tasksArray.length();i++){
        		JSONObject taskObject = tasksArray.getJSONObject(i); 
        		String id = taskObject.getString("id");
        		String name = taskObject.getString("name");
        		String icon = taskObject.getString("icon");
        		String text = taskObject.getString("text");
        		loadImage(icon);
        		Task task = new Task(id, icon, text, name);
        		mTasks.add(task);
        		Log.i("", id+";"+name+";"+icon+";"+text);
        	} 
        }
        catch(Exception e){
        	e.printStackTrace();
        } 
	} 
	
	private boolean loadImage(String file) throws Exception{
		boolean result = false;
		try{ 	
			String imageUrl = Config.SITE_URL+file;
			String imageFile = FileUtils.getImagesPath(mListener)+file;
			FileUtils.downloadFile2(imageUrl, imageFile); 
	  	  	result=true;
		}
		catch(Exception e){  
			e.printStackTrace();
		}
	      
	    return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		 
		mListener.onLoadListResult(mTasks);
	}
	
}
