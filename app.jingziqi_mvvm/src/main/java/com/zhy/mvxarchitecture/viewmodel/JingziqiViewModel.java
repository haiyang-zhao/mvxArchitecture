package com.zhy.mvxarchitecture.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;

import com.zhy.mvxarchitecture.model.Board;
import com.zhy.mvxarchitecture.model.Player;

public class JingziqiViewModel {

    private ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    private ObservableField<String> winner = new ObservableField<>();
    private Board model;

    public JingziqiViewModel() {
        this.model = new Board();
    }

    public void resetSelect() {
        this.model.reset();
        winner.set(null);
        cells.clear();
    }

    public void onClickedAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        if (null != playerThatMoved) {
            cells.put("" + row + col, playerThatMoved.toString());
            winner.set(model.getWinner() == null ? null : model.getWinner().toString());
        }
    }

    public ObservableArrayMap<String, String> getCells() {
        return cells;
    }

    public ObservableField<String> getWinner() {
        return winner;
    }
}
