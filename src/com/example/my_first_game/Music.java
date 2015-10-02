package com.example.my_first_game;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.sound.SoundEngine;

import android.view.MotionEvent;

public  class Music extends CCLayer {
	    public Music() {
        	super();
        	this.setIsTouchEnabled(true);
        }
        @Override
        public void onEnter() {
        	super.onEnter();
        	SoundEngine.sharedEngine().playSound(MainActivity.app, R.raw.backsound, true);
        }
        @Override
        public void onExit() {
        	SoundEngine.sharedEngine().pauseSound();
        	super.onExit();
        }
    }