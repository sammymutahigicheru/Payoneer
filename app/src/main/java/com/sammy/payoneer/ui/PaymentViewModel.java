package com.sammy.payoneer.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sammy.payoneer.data.error.ErrorHolder;
import com.sammy.payoneer.data.models.Applicable;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

@HiltViewModel
public class PaymentViewModel extends ViewModel {

    private final PaymentRepository paymentRepository;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(true);
    private final MutableLiveData<List<Applicable>> _applicables = new MutableLiveData<>();
    private final MutableLiveData<ErrorHolder> _errorBody = new MutableLiveData<>();
    public CompositeDisposable compositeDisposable = new CompositeDisposable();
    LiveData<Boolean> isLoading = _isLoading;
    LiveData<List<Applicable>> applicables = _applicables;
    LiveData<ErrorHolder> errorBody = _errorBody;

    @Inject
    public PaymentViewModel(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void getPaymentNetworks() {
        Disposable disposable = paymentRepository.getPaymentNetworks()
                .subscribeOn(Schedulers.io())
                .map(paymentListResponse -> paymentListResponse.getNetworks().getApplicable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(applicables -> {
                            _isLoading.postValue(false);
                            _applicables.postValue(applicables);
                        }, throwable -> {
                            _isLoading.postValue(false);
                            if (throwable instanceof IOException) {
                                _errorBody.postValue(new ErrorHolder("Unable to connect, turn on your internet connection", 1));
                            } else if (throwable instanceof HttpException) {
                                HttpException exception = (HttpException) throwable;
                                _errorBody.postValue(new ErrorHolder(exception.message(), exception.code()));
                            } else {
                                _errorBody.postValue(new ErrorHolder(throwable.getMessage(), 0));
                            }
                        }
                );
        compositeDisposable.add(disposable);
    }

}
