package com.example.watanabe_ta.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by watanabe_ta on 2015/12/26.
 */
public class MyAsync extends AsyncTask<String, Integer, String> implements DialogInterface.OnCancelListener {

    //フィールド変数
    ProgressDialog dialog;
    Context context;

    //コンストラクタ
    public MyAsync(Context context){ this.context = context; }

    @Override
    public void onCancel(DialogInterface dialog) {
        this.cancel(true);
    }

    @Override
    protected void onPreExecute() {

        dialog = new ProgressDialog(context);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading data...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(this);
        dialog.show();
    }
    /*
        ドット３つは引数を複数持つことができるようになる。
     */
    @Override
    protected String doInBackground(String... params) {
        Network nt = new Network();
        StringBuffer responseJSON = nt.login();

        String resultCode = null;

        try {
            JSONObject oJson = new JSONObject(responseJSON.toString());
            resultCode = oJson.getString("result_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultCode;
    }

    @Override protected void onProgressUpdate(Integer... values) { }
    @Override protected void onPostExecute(String result) {
        dialog.dismiss();
        Toast.makeText(this.context, result, Toast.LENGTH_SHORT).show();
    }
    @Override protected void onCancelled() { }
}
