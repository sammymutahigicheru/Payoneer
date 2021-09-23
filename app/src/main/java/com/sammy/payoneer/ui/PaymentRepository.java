package com.sammy.payoneer.ui;

import com.sammy.payoneer.data.models.PaymentListResponse;
import com.sammy.payoneer.network.ApiService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PaymentRepository {
    private final ApiService apiService;

    @Inject
    public PaymentRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<PaymentListResponse> getPaymentNetworks() {
        return apiService.getPaymentNetworks();
    }
}
