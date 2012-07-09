package com.ocb.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class Http {
	private final static String Charset = "utf-8";
	// protected static final String FILENAME = "";
	private String fileSourceURL = "http://pic.yesky.com/imagelist/09/01/11277904_7147.jpg";

	private String filePath;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileUrl) {
		String strs[] = fileUrl.split("/");
		this.fileName = strs[strs.length - 1];
	}

	private String fileFormat;
	private String message;
	private ProgressDialog myDialog = null;
	private Runnable saveFileRunnable;
	// private static final String PACKAGE_NAME = "com.myWebview";
	// private static final String URI_PREFIX = "file:///data/data/"
	// + PACKAGE_NAME + "/files/";
	private Bitmap bitmap;

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public ProgressDialog getMyDialog() {
		return myDialog;
	}

	public void setMyDialog(ProgressDialog myDialog) {
		this.myDialog = myDialog;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSourceURL() {
		return fileSourceURL;
	}

	public void setFileSourceURL(String fileSourceURL) {
		this.fileSourceURL = fileSourceURL;
	}

	public static String HttpGet(String url) {
		String res = null;

		try {
			HttpGet httpRequest = new HttpGet(url);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				res = EntityUtils.toString(httpResponse.getEntity(), Charset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static String HttpPost(String url, String data) {
		String res = null;

		try {
			HttpPost httpRequest = new HttpPost(url);
			httpRequest.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
			httpRequest.setEntity(new StringEntity(data, Charset));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				res = EntityUtils.toString(httpResponse.getEntity(), Charset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static String HttpPost(String url, byte[] data) {
		String res = null;

		try {
			HttpPost httpRequest = new HttpPost(url);
			httpRequest.setEntity(new ByteArrayEntity(data));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				res = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static String HttpPost(String url, Map<String, String> data) {
		String res = null;

		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
			DefaultHttpClient httpClient = new DefaultHttpClient();

			ArrayList<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> m : data.entrySet()) {
				postData.add(new BasicNameValuePair(m.getKey(), m.getValue()));
			}

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData,
					HTTP.UTF_8);
			httpPost.setEntity(entity);
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				res = EntityUtils.toString(httpResponse.getEntity(), Charset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public void LoadFile(String url) {

		try {
			URL u = new URL(url);
			HttpURLConnection c = (HttpURLConnection) u.openConnection();
			c.setRequestMethod("GET");
			c.setDoOutput(true);
			c.connect();
			// 保存文件的文件夹必须存在
			File file = new File(getFilePath());
			if (!file.exists()) {
				file.mkdir();

			}
			FileOutputStream f = new FileOutputStream(getFilePath() + fileName);

			InputStream in = c.getInputStream();

			int data = in.read();
			while (data != -1) {
				f.write(data);
				data = in.read();
			}
			f.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get image from newwork
	 * 
	 * @param path
	 *            The path of image
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] getImageByteData(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return readStream(inStream);
		}
		return null;
	}

	/**
	 * Get image from newwork
	 * 
	 * @param path
	 *            The path of image
	 * @return InputStream
	 * @throws Exception
	 */
	public InputStream getImageStream(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return conn.getInputStream();
		}
		return null;
	}

	/**
	 * Get bitmap from Image's URL
	 * 
	 * @param inStream
	 * @return byte[]
	 * @throws Exception
	 */
	public Bitmap getImageBitmap(String path) throws Exception {
		Bitmap bitmap = null;
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			bitmap = BitmapFactory.decodeStream(conn.getInputStream());
			return bitmap;
		}
		return null;
	}

	/**
	 * Get data from stream
	 * 
	 * @param inStream
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	/**
	 * 保存文件
	 * 
	 * @param bm
	 * @param fileName
	 * @throws IOException
	 */
	public void savePic(Bitmap bm, String fileName) throws IOException {
		File dirFile = new File(getFilePath());
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		File myCaptureFile = new File(getFilePath() + fileName);
		FileOutputStream fos = new FileOutputStream(myCaptureFile);
		bm.compress(Bitmap.CompressFormat.JPEG, 80, fos);
		fos.flush();
		fos.close();

	}

	public boolean isPic(String filename) {
		filename = filename.toLowerCase();
		if (filename.contains("png") || filename.contains("bmp")
				|| filename.contains("jpg") || filename.contains("jpeg")
				|| filename.contains("gif"))
			return true;
		else
			return false;
	}

	public void saveFile(String fileSourceUrl) {
		setFileSourceURL(fileSourceUrl);
		setFileName(fileSourceUrl);
		if (isPic(getFileName())) {
			try {
				Bitmap bitmapTemp = getImageBitmap(getFileSourceURL());
				setBitmap(bitmapTemp);
				savePic(getBitmap(), fileName);
				message = "图片保存成功！";
			} catch (IOException e) {
				message = "图片保存失败！";
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			LoadFile(getFileSourceURL());
		}
	}

	public static boolean HttpDownloadFile(String url, String savePath) {
		boolean res = false;

		try {
			URL sourceUrl = new URL(url);
			URLConnection conn = sourceUrl.openConnection();
			InputStream inputStream = conn.getInputStream();

			int fileSize = conn.getContentLength();

			FileOutputStream outputStream = new FileOutputStream(savePath);

			byte[] buffer = new byte[1024];
			int downSize = 0;
			int readLen = 0;
			while (downSize < fileSize && readLen != -1) {
				readLen = inputStream.read(buffer);
				if (readLen > -1) {
					outputStream.write(buffer, 0, readLen);
					downSize = downSize + readLen;

				}
			}
			outputStream.close();
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static String HttpSOAP(String url, String action, String message) {
		String res = null;

		try {
			HttpPost httpRequest = new HttpPost(url);
			httpRequest.setHeader("Content-Type", "text/xml; charset="
					+ Charset);
			httpRequest.setHeader("SOAPAction", action);
			httpRequest.setEntity(new StringEntity(message, Charset));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				res = EntityUtils.toString(httpResponse.getEntity(), Charset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

}
