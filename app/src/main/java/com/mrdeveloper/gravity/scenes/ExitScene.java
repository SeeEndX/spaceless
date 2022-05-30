package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;

import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class ExitScene extends SceneGame {

    public ExitScene(CoreGame coreGame) {
        super(coreGame);
    }

    @Override
    protected void update() {
        if (pCoreGame.getTouchListenerFW().getTouchUp(200,200,100,35)){
            pCoreGame.finish();
        }
        if (pCoreGame.getTouchListenerFW().getTouchUp(500,200,100,35)){
            pCoreGame.setScene(new MainMenuScene(pCoreGame));
        }


    }

    @Override
    protected void drawing() {
        pCoreGame.getGraphicsFW().clearScene(Color.BLACK);
        pCoreGame.getGraphicsFW().drawText("Вы точно хотите выйти?",10,100
        ,Color.MAGENTA,60, ResourceGame.mainMenuFont);
        pCoreGame.getGraphicsFW().drawText("ДА",200,200
                ,Color.RED,50, ResourceGame.mainMenuFont);
        pCoreGame.getGraphicsFW().drawText("НЕТ",500,200
                ,Color.GREEN,50, ResourceGame.mainMenuFont);

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
