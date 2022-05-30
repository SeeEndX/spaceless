package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

import java.util.Arrays;

public class TopDistanceScene extends SceneGame {

    private String[] mNumbers = new String[5];

    TopDistanceScene(CoreGame coreGame) {
        super(coreGame);
        for (int i = 0; i < 5; i++) {
            this.mNumbers[i] = " " + (i + 1) + "." + SettingsGame.mDistance[i];
        }
    }

    @Override
    public void update() {
        if (pCoreGame.getTouchListenerFW().getTouchUp(0, pSceneHeight, pSceneWidth, pSceneHeight)) {
            pCoreGame.setScene(new MainMenuScene(pCoreGame));
        }
    }

    @Override
    public void drawing() {
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_top_distance), 100, 100, Color.MAGENTA, 60, ResourceGame.mainMenuFont);
        pGraphicsGame.drawText(String.valueOf(mNumbers[0]), 120, 250, Color.YELLOW, 35, ResourceGame.mainMenuFont);
        pGraphicsGame.drawText(String.valueOf(mNumbers[1]), 120, 300, Color.LTGRAY, 35, ResourceGame.mainMenuFont);
        pGraphicsGame.drawText(String.valueOf(mNumbers[2]), 120, 350, Color.DKGRAY, 35, ResourceGame.mainMenuFont);
        pGraphicsGame.drawText(String.valueOf(mNumbers[3]), 120, 400, Color.DKGRAY, 35, ResourceGame.mainMenuFont);
        pGraphicsGame.drawText(String.valueOf(mNumbers[4]), 120, 450, Color.DKGRAY, 35, ResourceGame.mainMenuFont);
    }

    //region @Override
    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        pGraphicsGame.clearScene(Color.BLACK);
    }

    @Override
    public void dispose() {

    }
    //endregion
}
