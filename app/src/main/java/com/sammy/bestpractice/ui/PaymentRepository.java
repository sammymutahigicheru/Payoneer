package com.sammy.bestpractice.ui;

import com.sammy.bestpractice.data.models.PaymentListResponse;
import com.sammy.bestpractice.network.ApiService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PaymentRepository {
    private final ApiService apiService;

    @Inject
    public PaymentRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<PaymentListResponse> getPaymentList() {
        return apiService.getPaymentList();
    }
}
