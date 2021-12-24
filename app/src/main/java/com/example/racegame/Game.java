package com.example.racegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private int amountOfLife;
    private int money;
    private GameThread gameThread;
    private CarObj car;
    private ArrayList<GameObject> arr;
    public Game(Context context){
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
        arr=new ArrayList<GameObject>();
        amountOfLife=3;
        money=0;
    }
    public void update(){
        this.car.update();
        for (GameObject item:arr) {
            item.update();
        }
        crushCheck();
    }

    public void crushCheck(){
        for (GameObject item:arr) {
           if((Math.abs(this.car.getX()-item.getX())<10)&&(Math.abs(this.car.getY()-item.getY())<10))
           {
               if(item instanceof BoxObj){
                   amountOfLife--;
                   if(amountOfLife==0){}//game over
               }
               else if(item instanceof CoinObj){
                   money+=10;
               }
           }
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        this.car.draw(canvas);
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Bitmap car = BitmapFactory.decodeResource(this.getResources(),R.drawable.car);
        this.car = new CarObj(this,car,20,30);
        this.gameThread = new GameThread(this,holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean again = true;
        while(again){
            try{
                this.gameThread.join();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            again=true;
        }
    }
}
