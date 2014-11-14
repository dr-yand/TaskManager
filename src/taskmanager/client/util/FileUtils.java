package taskmanager.client.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log; 

public class FileUtils { 
  
     
    public static void downloadFile2(String urlString, String filename) throws Exception{ 
		 URL url = new URL(urlString); 
		 File file = new File(filename);
		 URLConnection ucon = url.openConnection();
		 InputStream is = ucon.getInputStream();
		 BufferedInputStream bis = new BufferedInputStream(is);
		 ByteArrayBuffer baf = new ByteArrayBuffer(50);
		 int current = 0;
		 while ((current = bis.read()) != -1) {
			 baf.append((byte) current);
		 }
		 FileOutputStream fos = new FileOutputStream(file);
		 fos.write(baf.toByteArray());
		 fos.close();
	}
     
    public static String getImagesPath(Context context){
    	String destinationFile = context.getExternalFilesDir(null).getPath()  + File.separator;
    	return destinationFile;
    }
}
