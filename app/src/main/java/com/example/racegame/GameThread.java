package com.example.racegame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.widget.EditText;

public class GameThread extends Thread {
    private boolean running;
    private Game game;
    private SurfaceHolder surfaceHolder;

    public GameThread(Game game,SurfaceHolder surfaceHolder){
        this.game=game;
        this.surfaceHolder=surfaceHolder;
    }
    public void setRunning(boolean b) {
        this.running=b;
    }

    @Override
    public void run(){
        while(running){
            Canvas canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (canvas) {
                    this.game.update();
                    this.game.draw(canvas);
                }
            }catch (Exception e){}
            finally {
                if(canvas!=null){
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            try{
                this.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
