package com.example.xjj.core.net;

import android.content.Context;

import com.example.xjj.core.net.callback.IError;
import com.example.xjj.core.net.callback.IFailure;
import com.example.xjj.core.net.callback.IRequest;
import com.example.xjj.core.net.callback.ISuccess;
import com.example.xjj.core.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public class RestClientBuilder {

    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private RequestBody mRequestBody;
    private  LoaderStyle mLoaderStyle;
    private  Context mContext;

    RestClientBuilder() {
    }

    public RestClientBuilder url(String url) {
        mUrl = url;
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public RestClientBuilder raw(String raw) {
        mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public RestClientBuilder onRequest(IRequest request) {
        mRequest = request;
        return this;
    }

    public RestClientBuilder success(ISuccess success) {
        mSuccess = success;
        return this;
    }

    public RestClientBuilder failure(IFailure failure) {
        mFailure = failure;
        return this;
    }

    public RestClientBuilder error(IError error) {
        mError = error;
        return this;
    }

    public RestClientBuilder loader(Context context,LoaderStyle loaderStyle) {
        mContext = context;
        mLoaderStyle = loaderStyle;
        return this;
    }

    public RestClientBuilder loader(Context context) {
        mContext = context;
        mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mSuccess, mFailure, mError, mRequestBody,mLoaderStyle,mContext);
    }
}
