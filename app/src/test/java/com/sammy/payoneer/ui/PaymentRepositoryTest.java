package com.sammy.payoneer.ui;

import com.sammy.payoneer.data.models.PaymentListResponse;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;



public class PaymentRepositoryTest{

    @Test
    public void whenThereAreNoPaymentMethodsReturnEmptyList (){
        //dummy response, remember the same can be done if you want to mock non empty respone
        List<PaymentListResponse> dummyResponse = new ArrayList<>();
        PaymentRepository dummyRepository = Mockito.mock(PaymentRepository.class);
        Mockito.doReturn(Observable.just(dummyResponse)).when(dummyRepository).getPaymentNetworks();
        // Trigger
        TestObserver<PaymentListResponse> testObserver = dummyRepository.getPaymentNetworks().test();

        testObserver.assertValueCount(1);
        // clean up
        testObserver.dispose();
    }
}