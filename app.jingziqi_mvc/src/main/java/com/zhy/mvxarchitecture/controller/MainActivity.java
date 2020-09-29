package com.zhy.mvxarchitecture.controller;

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
import com.zhy.mvxarchitecture.model.Player;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();
    /* Views */
    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jingziqi);
        buttonGrid = findViewById(R.id.buttonGrid);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        winnerPlayerLabel = findViewById(R.id.winnerPlayerLabel);
        board = new Board();
        resetView();
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
            resetView();
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

        Player playerThatMoved = board.mark(row, col);
        if (null != playerThatMoved) {
            moveBtn.setText(playerThatMoved.name());
            if (null != board.getWinner()) {
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
                winnerPlayerLabel.setText(board.getWinner().name());
            }

        }
    }


    private void resetView() {
        /*Reset View*/
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
        board.reset();
    }


}