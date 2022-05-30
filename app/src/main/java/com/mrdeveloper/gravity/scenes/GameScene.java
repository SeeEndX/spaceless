package com.mrdeveloper.gravity.scenes;

import android.graphics.Color;
import android.support.annotation.ColorLong;

import com.mrdeveloper.gravity.R;
import com.mrdeveloper.gravity.clases.GameManager;
import com.mrdeveloper.gravity.utilits.SettingsGame;
import com.mrdeveloper.gravity.utilits.ResourceGame;
import com.mrdeveloper.my_framework.core.CoreGame;
import com.mrdeveloper.my_framework.core.SceneGame;

public class GameScene extends SceneGame {

    //region Fields
    private GameState mGameState;
    private GameManager mGameManager;

    //Состояния игры
    enum GameState {
        READY, RUNNING, PAUSE, GAME_OVER
    }
    //endregion

    //region Main methods
    public GameScene(CoreGame coreGame) {
        super(coreGame);
        init(coreGame);
        if (SettingsGame.sMusicOn){
            ResourceGame.sMainMusicGame.play(true, 0.5f);
        }

    }

    private void init(CoreGame coreGame) {
        mGameState = GameState.READY;
        mGameManager = new GameManager(coreGame, pSceneWidth, pSceneHeight);
    }

    @Override
    public void update() {
        //Обновление игры
        if (mGameState == GameState.READY) {
            updateStateReady();
        }
        if (mGameState == GameState.RUNNING) {
            updateStateRunning();
        }
        if (mGameState == GameState.PAUSE) {
            updateStatePause();
        }
        if (mGameState == GameState.GAME_OVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        //Перерисовка игры
        pGraphicsGame.clearScene(Color.BLACK);

        if (mGameState == GameState.READY) {
            drawingStateReady();
        }
        if (mGameState == GameState.RUNNING) {
            drawingStateRunning();
        }
        if (mGameState == GameState.PAUSE) {
            drawingStatePause();
        }
        if (mGameState == GameState.GAME_OVER) {
            drawingStateGameOver();
        }

    }
    //endregion

    //region Update state
    private void updateStateGameOver() {
        SettingsGame.addDistance(mGameManager.getPassedDistance());
        if (pCoreGame.getTouchListenerFW().getTouchUp(250, 360, 300, 35)) {
            pCoreGame.setScene(new GameScene(pCoreGame));
        }
        if (pCoreGame.getTouchListenerFW().getTouchUp(250, 420, 300, 35)) {
            pCoreGame.setScene(new MainMenuScene(pCoreGame));
        }

    }

    private void updateStatePause() {
        if (pCoreGame.getTouchListenerFW().getTouchUp(0, pSceneHeight, pSceneWidth, pSceneHeight)) {
            mGameState = GameState.RUNNING;
        }

    }

    private void updateStateRunning() {
        mGameManager.update();
        if (GameManager.gameOver) {
            mGameState = GameState.GAME_OVER;
        }
        if (pCoreGame.isPressedKeyBack()){
            mGameState = GameState.PAUSE;
            pCoreGame.setPressedKeyBack(false);
        }
    }

    private void updateStateReady() {
        if (pCoreGame.getTouchListenerFW().getTouchUp(0, pSceneHeight, pSceneWidth, pSceneHeight)) {
            mGameState = GameState.RUNNING;
        }
    }
    //endregion

    //region Drawing state
    private void drawingStateGameOver() {
        pGraphicsGame.clearScene(Color.BLACK);
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_gameScene_stateGameOver_gameOver),
                100, 200, Color.RED, 80, ResourceGame.mainMenuFont);
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_gameScene_stateGameOver_distance) + ":" + mGameManager.getPassedDistance(),
                100, 300, Color.GREEN, 40, ResourceGame.mainMenuFont);
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_gameScene_stateGameOver_restart),
                100, 360, Color.WHITE, 40, ResourceGame.mainMenuFont);
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_gameScene_stateGameOver_exit),
                100, 420, Color.WHITE, 40, ResourceGame.mainMenuFont);
    }

    private void drawingStatePause() {
        pCoreGame.getGraphicsFW().drawText("ПАУЗА", 300,300, Color.MAGENTA,70,ResourceGame.mainMenuFont);

    }

    private void drawingStateRunning() {
        pGraphicsGame.clearScene(Color.BLACK);
        mGameManager.drawing(pGraphicsGame);
    }

    private void drawingStateReady() {
        pGraphicsGame.clearScene(Color.BLACK);
        pGraphicsGame.drawText(pCoreGame.getString(R.string.txt_gameScene_stateReady_ready),
                250, 300, Color.MAGENTA, 60, ResourceGame.mainMenuFont);
    }
    //endregion

    //region @Override
    @Override
    public void pause() {
        ResourceGame.sMainMusicGame.stop();
    }

    @Override
    public void resume() {
        if (SettingsGame.sMusicOn){
            ResourceGame.sMainMusicGame.play(true, 0.5f);
        }
    }

    @Override
    public void dispose() {

    }
    //endregion
}
