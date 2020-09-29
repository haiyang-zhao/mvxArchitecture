package com.zhy.mvxarchitecture.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zhy.mvxarchitecture.R;
import com.zhy.mvxarchitecture.model.Board;
import com.zhy.mvxarchitecture.presenter.JingziqiPresenter;

public class MainActivity extends AppCompatActivity implements IView {

    private static String TAG = MainActivity.class.getName();
    /* Views */
    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;
    private JingziqiPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jingziqi);
        buttonGrid = findViewById(R.id.buttonGrid);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        winnerPlayerLabel = findViewById(R.id.winnerPlayerLabel);
        presenter = new JingziqiPresenter(this, new Board());
        presenter.reset();
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
            presenter.reset();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void onCellClicked(View view) {
        Button moveBtn = (Button) view;
        String tag = moveBtn.getTag().toString();
        int row = Integer.parseInt(tag.substring(0, 1));
        int col = Integer.parseInt(tag.substring(1, 2));
        Log.d(TAG, "Click At (" + row + "," + col + ")");
        presenter.markButton(row, col);
    }


    @Override
    public void showWinnerName(String name) {
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
        winnerPlayerLabel.setText(name);
    }

    @Override
    public void clearView() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    @Override
    public void showMovBtnText(int row, int col, String name) {
        View button = buttonGrid.findViewWithTag(row + "" + col);
        if (null != button) {
            ((Button) button).setText(name);
        }
    }

}