package com.sammy.bestpractice.network;

import com.sammy.bestpractice.data.models.PaymentListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("listresult.json")
    Observable<PaymentListResponse> getPaymentList();
}
