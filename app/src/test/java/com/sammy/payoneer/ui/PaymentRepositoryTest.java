package com.sammy.payoneer.ui;

import com.sammy.payoneer.data.models.PaymentListResponse;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;


public class PaymentRepositoryTest{

    @Test
    public void successResponseTest (){
        //dummy response, remember the same can be done if you want to mock non empty respone
        List<PaymentListResponse> dummyResponse = new ArrayList<>();
        PaymentRepository dummyRepository = Mockito.mock(PaymentRepository.class);
        Mockito.doReturn(Observable.just(dummyResponse)).when(dummyRepository).getPaymentNetworks();
        // Trigger
        TestObserver<PaymentListResponse> testObserver = dummyRepository.getPaymentNetworks().test();

        testObserver.assertValueCount(1);
        testObserver.assertComplete();
        // clean up
        testObserver.dispose();
    }

    @Test
    public void failureResponseTest(){
        PaymentRepository dummyRepository = Mockito.mock(PaymentRepository.class);
        Mockito.doReturn(Observable.error(new RuntimeException("error in Observable"))).when(dummyRepository).getPaymentNetworks();
        // Trigger
        TestObserver<PaymentListResponse> testObserver = dummyRepository.getPaymentNetworks().test();

        testObserver.assertError(RuntimeException.class);
        testObserver.assertNotComplete();
        // clean up
        testObserver.dispose();
    }
}