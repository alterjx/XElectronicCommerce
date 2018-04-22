package com.example.xjj.xelectroniccommerce;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.xjj.core.delegates.EquinoxDelegate;
import com.example.xjj.core.net.RestClient;
import com.example.xjj.core.net.callback.IError;
import com.example.xjj.core.net.callback.IFailure;
import com.example.xjj.core.net.callback.ISuccess;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public class ExampleDelegate extends EquinoxDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                //.params("","")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
