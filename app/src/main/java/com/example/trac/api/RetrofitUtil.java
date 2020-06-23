package com.example.trac.api;

import androidx.annotation.NonNull;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitUtil {

    public static ServiceError parseError(@NonNull Throwable throwable) {
        return parseErrorBody(Retro.getRetrofitInstance(), throwable);
    }

    private static ServiceError parseErrorBody(Retrofit retrofit, Throwable throwable) {
        ServiceError error = null;
        if (throwable instanceof HttpException) {
            Response<?> response = ((HttpException) throwable).response();

            try {
                if (response != null) {
                    Converter<ResponseBody, ServiceError> responseBodyServiceErrorConverter =
                            retrofit.responseBodyConverter(ServiceError.class, new Annotation[0]);
                    ServiceError serviceError = responseBodyServiceErrorConverter.convert(response.errorBody());
                    error = serviceError;
                }
            } catch (Exception e) {
                error = new ServiceError(500, "", "");
            }
        }
        return error;
    }
}
