package com.zhy.mvxarchitecture.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.zhy.mvxarchitecture.R;
import com.zhy.mvxarchitecture.databinding.JingziqiBinding;
import com.zhy.mvxarchitecture.viewmodel.JingziqiViewModel;

public class MainActivity extends AppCompatActivity {
    JingziqiViewModel viewModel = new JingziqiViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JingziqiBinding binding = DataBindingUtil.setContentView(this, R.layout.jingziqi);
        binding.setVm(viewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_jingziqi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_reset) {
            viewModel.resetSelect();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}