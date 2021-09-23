package com.sammy.payoneer.network;

import com.sammy.payoneer.data.models.PaymentListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("listresult.json")
    Observable<PaymentListResponse> getPaymentList();
}
