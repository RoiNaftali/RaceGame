package com.example.racegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.content.res.Resources;



public class CoinObj extends GameObject {
    private Game game;
    public CoinObj(Game game, Bitmap image, int x , int y){
        super(image,x,y);
        this.game = game;
    }
    public void update(){
        this.x += this.movingX;
        if(this.x < 0) this.x = 0;
        else if(this.x > this.game.getWidth()-width)
            this.x=this.game.getWidth()-width;
    }
    public void draw(Canvas canvas){
//        Bitmap bitmap=null;// =  R.raw.car;
        Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.raw.coin);
        canvas.drawBitmap(bitmap,x,y,null);
    }
}
