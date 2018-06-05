package com.example.ymeng.tomorrowhelper.view.activity;

import android.os.AsyncTask;
import android.os.Environment;

import com.example.ymeng.tomorrowhelper.view.interfacefolder.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:YMeng
 * Time:2018/6/5  15:11
 * This is DownLoadTask
 * 下载，断点续传
 */
public class DownLoadTask extends AsyncTask<String, Integer, Integer> {
    public static final int TYPE_SUCCESS = 0;//成功
    public static final int TYPE_FAILED = 1;//失败
    public static final int TYPE_PAUSED = 2;//暂停
    public static final int TYPE_CANCLED = 3;//取消

    private DownloadListener mDownloadListener;

    private boolean isPaused = false;
    private boolean isCancled = false;

    private int lastProgress;

    public DownLoadTask(DownloadListener mDownloadListener) {
        this.mDownloadListener = mDownloadListener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;

        try {
            long downLoadedLength = 0;//记录已下载的文件长度
            String downloadUrl = strings[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);

            if (file.exists()) {
                downLoadedLength = file.length();
            }

            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {//失败
                return TYPE_FAILED;
            } else if (contentLength == downLoadedLength) {//成功
                return TYPE_SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downLoadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downLoadedLength);
                byte[] b = new byte[1024];

                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCancled) {
                        return TYPE_CANCLED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        //计算下载百分比
                        int progress = (int) ((total + downLoadedLength) * 100 / contentLength);
                        publishProgress(progress);

                    }
                }
                //关闭资源
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (savedFile != null)
                    savedFile.close();
                if (isCancled && file != null)
                    file.delete();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            mDownloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYPE_SUCCESS:
                mDownloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                mDownloadListener.onFailed();
                break;
            case TYPE_PAUSED:
                mDownloadListener.onPaused();
                break;
            case TYPE_CANCLED:
                mDownloadListener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void puaseDownload() {
        isPaused = true;
    }

    public void canceledDownload() {
        isCancled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();

        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }

}
