package com.sammy.payoneer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.sammy.payoneer.R;
import com.sammy.payoneer.commons.NetworkManager;
import com.sammy.payoneer.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PaymentViewModel viewModel;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new MaterialAlertDialogBuilder(this).create();

        viewModel = new ViewModelProvider(this).get(PaymentViewModel.class);

        NetworkManager networkManager = new NetworkManager();
        boolean isNetworkAvailable = networkManager.isNetworkAvailable(this);
        viewModel.getPaymentNetworks();
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, loading -> {
            if (loading) {
                dialog.setCancelable(false);
                dialog.setMessage("Please wait...");
                dialog.show();
            } else {
                dialog.dismiss();
            }
        });
        viewModel.applicables.observe(this, applicables -> {
            if (applicables.isEmpty()) {
                showRetryErrorSnackbar("No Payment Methods");
            } else {
                PaymentsAdapter paymentsAdapter = new PaymentsAdapter();
                binding.paymentsRecycler.setAdapter(paymentsAdapter);
                binding.paymentsRecycler.hasFixedSize();
                paymentsAdapter.submitList(applicables);
            }
        });

        viewModel.errorBody.observe(this, errorHolder -> {
            switch (errorHolder.getCode()){
                case 0:
                    showErrorSnackbar(errorHolder.getMessage());
                    break;
                case 1:
                    showRetryErrorSnackbar(errorHolder.getMessage());
                case 400:
                    showRetryErrorSnackbar(getString(R.string.no_payment_methods));
                case 500:
                    showRetryErrorSnackbar(getString(R.string._500));
            }
            showRetryErrorSnackbar(errorHolder.getMessage());

        });
    }

    private void showRetryErrorSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_red_light));
        snackbar.show();
        snackbar.setAction("RETRY", v -> {
            snackbar.dismiss();
            viewModel.getPaymentNetworks();
        });
    }

    private void showErrorSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_red_light));
        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.compositeDisposable.clear();
    }
}