package com.example.trac.repository;

import com.example.trac.api.ApiService;
import com.example.trac.api.Retro;

public class MoreRepository {

    private static MoreRepository instance;
    private static ApiService apiService;

    public static MoreRepository getInstance() {
        if (instance == null) {
            instance = new MoreRepository();
        }
        return instance;
    }

    private static ApiService getApiService() {
        if (apiService == null) {
            apiService = Retro.getApiService();
        }
        return apiService;
    }

}
