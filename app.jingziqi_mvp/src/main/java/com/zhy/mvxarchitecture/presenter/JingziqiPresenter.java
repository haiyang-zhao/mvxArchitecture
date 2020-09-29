package com.zhy.mvxarchitecture.presenter;

import com.zhy.mvxarchitecture.model.Board;
import com.zhy.mvxarchitecture.model.Player;
import com.zhy.mvxarchitecture.view.IView;

public class JingziqiPresenter {
    private IView iView;
    private Board model;

    public JingziqiPresenter(IView iView, Board model) {
        this.iView = iView;
        this.model = model;
    }


    public void reset() {
        iView.clearView();
        model.reset();
    }

    public void markButton(int row, int col) {
        Player player = model.mark(row, col);
        if (null != player) {
            iView.showMovBtnText(row, col, player.name());
            if (null != model.getWinner()) {
                iView.showWinnerName(model.getWinner().name());
            }
        }
    }
}
