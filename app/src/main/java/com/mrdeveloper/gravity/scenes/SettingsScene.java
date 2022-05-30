package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;

import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class SettingsScene extends SceneGame {

    SettingsScene(CoreGame coreGame) {
        super(coreGame);
    }

    @Override
    protected void update() {
        if (pCoreGame.isPressedKeyBack()){
            pCoreGame.setPressedKeyBack(false);
            pCoreGame.setScene(new MainMenuScene(pCoreGame));
        }
        if (pCoreGame.getTouchListenerFW().getTouchUp(300,350,300,45)){
            SettingsGame.sSoundOn =!SettingsGame.sSoundOn;
            SettingsGame.saveSettings(pCoreGame);
        }

        if (pCoreGame.getTouchListenerFW().getTouchUp(300,300,300,45)){
            SettingsGame.sMusicOn =!SettingsGame.sMusicOn;
            SettingsGame.saveSettings(pCoreGame);
        }
    }

    @Override
    protected void drawing() {
        pCoreGame.getGraphicsFW().clearScene(Color.BLACK);
        pCoreGame.getGraphicsFW().drawText("Настройки",
                100, 100, Color.MAGENTA, 60, ResourceGame.mainMenuFont);

        pCoreGame.getGraphicsFW().drawText("Музыка ",
                100, 300, Color.LTGRAY, 45, ResourceGame.mainMenuFont);
        pCoreGame.getGraphicsFW().drawText("Звук ",
                100, 350, Color.LTGRAY, 45, ResourceGame.mainMenuFont);

        if (SettingsGame.sMusicOn){
            pCoreGame.getGraphicsFW().drawText("Включено ",
                    300, 300, Color.GREEN, 45, ResourceGame.mainMenuFont);
        } else pCoreGame.getGraphicsFW().drawText("Выключено ",
                300, 300, Color.RED, 45, ResourceGame.mainMenuFont);

        if (SettingsGame.sSoundOn){
            pCoreGame.getGraphicsFW().drawText("Включено ",
                    300, 350, Color.GREEN, 45, ResourceGame.mainMenuFont);
        } else pCoreGame.getGraphicsFW().drawText("Выключено ",
                300, 350, Color.RED, 45, ResourceGame.mainMenuFont);


    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void dispose() {

    }
}
