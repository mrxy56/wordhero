package com.example.my_first_game;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class MenuLayer extends CCLayer{
	   CCSprite bg;
	   CGSize size;
       public MenuLayer(){
       }
	  @Override
	   public void onEnter() {
		   super.onEnter();
		   
		   Music musiclayer=new Music();
		   this.addChild(musiclayer);
  	       
		   size = CCDirector.sharedDirector().winSize();
		   bg=CCSprite.sprite("bj.png");
  	       bg.setScaleX(size.width/320f);
  	       bg.setScaleY(size.height/480f);
  	       CGPoint bg_point=CGPoint.ccp(size.width/2,size.height/2);
  	       bg.setPosition(bg_point);
  	       this.addChild(bg);
 
  	       CCMenuItemImage newGameItem = CCMenuItemImage.item("newGame.png", "brick2.png",this,"menunewGameCallback");
  	       CCMenuItemImage LoginItem = CCMenuItemImage.item("Login.png", "brick2.png",this,"menuLoginCallback");
  	       CCMenuItemImage RankItem = CCMenuItemImage.item("Rank.png", "brick2.png",this,"menuRankCallback");
	       CCMenuItemImage ExitItem = CCMenuItemImage.item("Exit.png", "brick2.png",this,"menuExitCallback");
  	       
  	       CGPoint newGame_point=CGPoint.ccp(size.width/4,size.height/2+100);
  	       CGPoint Login_point=CGPoint.ccp(size.width/4,size.height/2);
  	       CGPoint Rank_point=CGPoint.ccp(size.width/4,size.height/2-100);
  	       CGPoint Exit_point=CGPoint.ccp(size.width/4,size.height/2-200);
  	    
  	       CCMenu menu = CCMenu.menu(newGameItem,LoginItem,RankItem,ExitItem);
		   menu.setPosition(CGPoint.zero());
		   
		   newGameItem.setPosition(newGame_point);
		   newGameItem.setScale(1.5f);
		   LoginItem.setPosition(Login_point);
		   LoginItem.setScale(1.5f);
		   RankItem.setPosition(Rank_point);
		   RankItem.setScale(1.5f);
		   ExitItem.setPosition(Exit_point);
		   ExitItem.setScale(1.5f);
		   
		   this.addChild(menu, 1);
		   
	  }
	  public void menunewGameCallback(Object sender){
		  SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
		  CCDirector.sharedDirector().replaceScene(new GameLayer().scene());
	  }
	  public void menuLoginCallback(Object sender){
		  SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
	  }
	  public void menuRankCallback(Object sender){
		  SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
		  CCDirector.sharedDirector().replaceScene(new RankLayer().scene3());
	  }
	  public void menuExitCallback(Object sender){
		  SoundEngine.sharedEngine().playEffect(MainActivity.app, R.raw.effect);
		  System.exit(0);
	  }
	  @Override
	  public void onExit() {
		// TODO Auto-generated method stub
		 super.onExit();
	  }
	  CCScene scene2(){
		    CCScene scene=CCScene.node();
		    MenuLayer menulayer=new MenuLayer();
		    scene.addChild(menulayer);
		    return scene;
	   }
}