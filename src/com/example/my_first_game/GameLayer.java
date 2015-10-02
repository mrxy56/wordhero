package com.example.my_first_game;

import java.util.Random;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.instant.CCPlace;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.view.MotionEvent;

public class GameLayer extends CCLayer{
	   CCSprite bg;
	   CGSize size;
	   int point,cnt,hehe=0;
	   final int ALL=20;
	   MySQLiteHelper myHelper=MainActivity.GetHelper();
	   static public CCLabel label = CCLabel.makeLabel(" ","battle",30);
	   CCMenuItemImage BrickA=CCMenuItemImage.item("A.png","brick2.png",this,"TouchA");
	   CCMenuItemImage BrickB=CCMenuItemImage.item("B.png","brick2.png",this,"TouchB");
       CCMenuItemImage BrickC=CCMenuItemImage.item("C.png","brick2.png",this,"TouchC"); 
       CCMenuItemImage BrickD=CCMenuItemImage.item("D.png","brick2.png",this,"TouchD");
       CCMenuItemImage BackItem = CCMenuItemImage.item("back.png", "back.png",this,"menuBackCallback");
       public GameLayer(){
    	   super();
    	  
    	   point=0;
    	   cnt=0;
    	   Music musiclayer=new Music();
		   this.addChild(musiclayer);
           
		   this.setIsTouchEnabled(true);
    	   size = CCDirector.sharedDirector().winSize();
    	   
   	       //设置背景
    	   bg=CCSprite.sprite("bj2.png");
   	       bg.setScaleX(size.width/320f);
   	       bg.setScaleY(size.height/480f);
   	       CGPoint bg_point=CGPoint.ccp(size.width/2,size.height/2);
   	       bg.setPosition(bg_point);
   	       this.addChild(bg);
   	       //设置砖块
   	       BrickA.setScale(1.5f);
   	       BrickA.setPosition(size.width/5-60, size.height-100);
   	       BrickB.setScale(1.5f);
   	       BrickB.setPosition(size.width*2/5-20, size.height-100);
   	       BrickC.setScale(1.5f);
   	       BrickC.setPosition(size.width*3/5+20, size.height-100);
   	       BrickD.setScale(1.5f);
   	       BrickD.setPosition(size.width*4/5+60, size.height-100);
   	     
   	   }
       @Override
	   public void onEnter() {
		   // TODO Auto-generated method stub
		   super.onEnter();
		   //设置砖块动作
   	       CGSize size = CCDirector.sharedDirector().winSize();
   	       CGPoint brick_p=CGPoint.ccp(0, -size.height-200);
   	       CCMoveBy moveby=CCMoveBy.action(3,brick_p);
   	       CCPlace place=CCPlace.action(CGPoint.ccp(0,0));
   	       //设置标签
		   label.setPosition(CGPoint.ccp(size.width/2,size.height*19/20));
	       this.addChild(label);
	       
	       new Thread(new MyThread(myHelper)).start();
	      
	       CCSequence seq=CCSequence.actions(place,moveby);
   	       CCRepeat repeat=CCRepeat.action(seq,20);
   	       CCMenu menu = CCMenu.menu(BrickA,BrickB,BrickC,BrickD);
	       menu.setPosition(CGPoint.zero());
	       this.addChild(menu,1);   
   	       menu.runAction(repeat); 
	   }


	   public void menuBackCallback(Object sender){
			  SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
			  CCDirector.sharedDirector().replaceScene(new MenuLayer().scene2());
	   }
	   
	   CCScene scene(){
		    CCScene scene=CCScene.node();
		    GameLayer gamelayer=new GameLayer();
		    scene.addChild(gamelayer);
		    return scene;
	   }
	   //按钮触发事件
	   public void TouchA(Object sender){
		    cnt++;
			SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
			if(getAnswer()=="A"){
				 point++;
			}
	   }
	   public void TouchB(Object sender){
		    cnt++;
		    SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
			if(getAnswer()=="B"){
				 point++;
			}
	   }
	   public void TouchC(Object sender){
		    cnt++;
			SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
			if(getAnswer()=="C"){
				 point++;
			}
	   }
	   public void TouchD(Object sender){
			SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
			cnt++;
			if(getAnswer()=="D"){
				 point++;
			}
	   }
	   //上传分数
	   void POSTPoint(int point){
		    
	   }
	   void Back(){
		     CGPoint back_point=CGPoint.ccp(size.width/2,size.height/2);
			 BackItem.setScale(2.0f);
	   	     BackItem.setPosition(back_point);
	   	     BackItem.setVisible(true);
	   	     BackItem.setIsEnabled(true);
	   	       
	   	     CCMenu menu1 = CCMenu.menu(BackItem);
			 menu1.setPosition(CGPoint.zero());
			   
			 this.addChild(menu1, 2);
			 CGSize s = CCDirector.sharedDirector().winSize();
			 CCLabel label = CCLabel.makeLabel("您的正确答题数为"+point,"answer",40);
			 label.setPosition(CGPoint.ccp( s.width/2,s.height*2/3));
		     this.addChild(label);
			 
		     POSTPoint(point);
	   }
	   String getAnswer(){
		     return "A";
	   }

	   @Override
	   public boolean ccTouchesBegan(MotionEvent event) {
		     cnt++;
		     if(cnt>=ALL) Back();
		     return super.ccTouchesBegan(event);
	   }
}