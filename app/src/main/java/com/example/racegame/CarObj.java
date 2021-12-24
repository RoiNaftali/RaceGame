package com.example.racegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class CarObj extends GameObject {
    private Game game;
   public CarObj(Game game,Bitmap image,int x , int y){
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
       Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), R.raw.car);
       canvas.drawBitmap(bitmap,x,y,null);
   }
   public void setMoving(int state){
       if(state==1) this.movingX=-10;
       else if(state==2)this.movingX=10;
   }



}
