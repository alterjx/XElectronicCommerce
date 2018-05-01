package com.example.xjj.core.net.callback;

import android.os.Handler;

import com.example.xjj.core.ui.Loader;
import com.example.xjj.core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public class RequestCallback
        implements Callback<String>
{
    private static final Handler HANDLER = new Handler();
    private final IRequest    REQUEST;
    private final ISuccess    SUCCESS;
    private final IFailure    FAILURE;
    private final IError      ERROR;
    private final LoaderStyle LOADER_STYLE;

    public RequestCallback(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        //TODO
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Loader.stopLoading();
                }
            }, 4000);
        }
    }
}
