package com.sammy.payoneer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.sammy.payoneer.data.models.PaymentListResponse;
import com.sammy.payoneer.network.ApiService;
import com.sammy.payoneer.ui.PaymentRepository;
import com.sammy.payoneer.ui.PaymentViewModel;

import io.reactivex.Observer;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    ApiService apiService;

    private PaymentViewModel viewModel;

    @Mock
    Observer<PaymentListResponse> observer;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        viewModel = new PaymentViewModel(paymentRepository);
        viewModel.getPaymentList();
    }

    @Test
    public void testForNull(){
       when(viewModel).thenReturn(null);
    }
}