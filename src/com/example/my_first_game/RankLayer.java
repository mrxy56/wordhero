package com.example.my_first_game;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.view.MotionEvent;

public class RankLayer extends CCLayer{
	   CCSprite bg,box;
	   CGSize size;
       public RankLayer(){
    	   super();
    	   
    	   Music musiclayer=new Music();
		   this.addChild(musiclayer);
           
		   this.setIsTouchEnabled(true);
    	   size = CCDirector.sharedDirector().winSize();
   	       
    	   bg=CCSprite.sprite("bj.png");
   	       bg.setScaleX(size.width/320f);
   	       bg.setScaleY(size.height/480f);
   	       CGPoint bg_point=CGPoint.ccp(size.width/2,size.height/2);
   	       bg.setPosition(bg_point);
   	       this.addChild(bg);
   	       
   	       box=CCSprite.sprite("tb.png");
	       box.setScaleX(size.width/583f*3/4);
	       box.setScaleY(size.height/797f*3/4);
	       CGPoint box_point=CGPoint.ccp(size.width/2,size.height/2);
	       box.setPosition(bg_point);
	       this.addChild(box);
   	       
	       CCMenuItemImage BackItem = CCMenuItemImage.item("backA.png", "backB.png",this,"menuBackCallback");
   	       CGPoint back_point=CGPoint.ccp(size.width/2,size.height/8);
   	       
   	       BackItem.setScale(2.0f);
   	       BackItem.setPosition(back_point);
   	       
   	       CCMenu menu = CCMenu.menu(BackItem);
		   menu.setPosition(CGPoint.zero());
		   
		   this.addChild(menu, 1);
       }
   
	   public void menuBackCallback(Object sender){
			  SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
			  CCDirector.sharedDirector().replaceScene(new MenuLayer().scene2());
	   }
	   
	   CCScene scene3(){
		    CCScene scene=CCScene.node();
		    RankLayer ranklayer=new RankLayer();
		    scene.addChild(ranklayer);
		    return scene;
	   }
}