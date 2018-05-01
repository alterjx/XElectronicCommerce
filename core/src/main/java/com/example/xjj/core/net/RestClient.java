package com.example.xjj.core.net;

import android.content.Context;

import com.example.xjj.core.net.callback.IError;
import com.example.xjj.core.net.callback.IFailure;
import com.example.xjj.core.net.callback.IRequest;
import com.example.xjj.core.net.callback.ISuccess;
import com.example.xjj.core.net.callback.RequestCallback;
import com.example.xjj.core.ui.Loader;
import com.example.xjj.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public class RestClient {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest    REQUEST;
    private final ISuccess    SUCCESS;
    private final IFailure    FAILURE;
    private final IError      ERROR;
    private final RequestBody REQUESTBODY;
    private final LoaderStyle LOADER_STYLE;
    private final File        FILE;
    private final Context     CONTEXT;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody requestBody,
                      File file,
                      LoaderStyle loaderStyle,
                      Context context)
    {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUESTBODY = requestBody;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
        this.CONTEXT = context;

    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String>      call    = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            Loader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, REQUESTBODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, REQUESTBODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService()
                                  .upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (REQUESTBODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("if request body is not null,the params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (REQUESTBODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("if request body is not null,the params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
