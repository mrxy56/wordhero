package com.example.my_first_game;
import java.io.InputStream;
import java.util.Random;

import org.apache.http.util.EncodingUtils;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CGPoint;

import com.example.my_first_game.R;
import com.example.my_first_game.MySQLiteHelper;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {
	private CCGLSurfaceView mGLSurfaceView=null;
	public static MainActivity app;
    static MySQLiteHelper myHelper;
    static String [] strArray=new String[20];
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app=this;
		myHelper=new MySQLiteHelper(this,"my.db",null,1);
		//将文件中的内容保存到数据库中
		insertAndUpdateData(myHelper);
		int testnum=GetNum();
		testnum--;
	    String s=MainActivity.queryData(myHelper,testnum*20+1,testnum*20+20);
	    int cnt=0,num=0;
	    String temp="";
	    for(int i=0;i<s.length();i++){
	    	 if(s.charAt(i)=='\n'){
	    		 num++;
	    		 temp+=s.charAt(i);
	    		 if(num%2==0){
	    		     strArray[cnt++]=temp;
	    		     temp="";
	    		     num=0;
	    		 }
	    	 }else{
	    		 temp+=s.charAt(i);
	    	 }
	    }
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
	    		WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
	    mGLSurfaceView=new CCGLSurfaceView(this);
	    setContentView(mGLSurfaceView);
	    
	    CCDirector my_director = CCDirector.sharedDirector();
		my_director.attachInView(mGLSurfaceView);
		
		CCDirector.sharedDirector().setDisplayFPS(true);
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 30);
		
	    CCScene scene = CCScene.node();
		
		MenuLayer menuLayer = new MenuLayer();
		scene.addChild(menuLayer);
		
		CCDirector.sharedDirector().runWithScene(scene);
	}
    //初始化数据库
    public void insertAndUpdateData(MySQLiteHelper myHelper){
    	SQLiteDatabase db=myHelper.getWritableDatabase();
    	String result=this.readFileData("test1.txt");
    	String temp ="";
        int cnt=0;
        ContentValues values=new ContentValues();
    	for(int i=0;i<result.length();i++){
    		if(result.charAt(i)=='\n'){
    			    cnt++;
    			    if(cnt%2==1){
    				  values.clear();
    				  values.put("word",temp);
    				  temp="";
    			    }else{
    				  values.put("meaning",temp);
    				  db.insert("test_info","id",values);
    				  temp="";
    			    }
    		}else{
    			temp+=result.charAt(i);
    		}
    	}
    	db.close();
    }
    //查询up与down之间的数据记录，结果保存在result中
    static public String queryData(MySQLiteHelper myHelper,int up,int down){
    	String result="";
    	SQLiteDatabase db=myHelper.getReadableDatabase();
    	Cursor cursor=db.query("test_info",null,null,null,null,null,"id asc");
    	
    	int wordIndex=cursor.getColumnIndex("word");
    	int meaningIndex=cursor.getColumnIndex("meaning");
    	int cnt=0;
    	for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
    		cnt++;
    		if(cnt>=up&&cnt<=down){
    		    result=result+cursor.getString(wordIndex)+"\n";
    		    result=result+cursor.getString(meaningIndex)+"\n";
    		}
    	}
    	cursor.close();
    	db.close();
    	return result;
    }
    //从文件中读取数据，保存到数据库中
    public String readFileData(String fileName){
    	String result="";
    	InputStream in;
		try {
			in = getResources().getAssets().open(fileName);
    		
			int len=in.available();
    		byte[]buffer=new byte[len];
    		in.read(buffer);
    		result=EncodingUtils.getString(buffer,"UTF-8");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
    }
    int GetNum(){
		   int number = new Random().nextInt(25) + 1;
		   return number;
	 }
    static public MySQLiteHelper GetHelper(){
    	return myHelper;
    }
    public void onDestroy() {
    	SQLiteDatabase db=myHelper.getWritableDatabase();
		db.delete("test_info","1",null);
        super.onDestroy();
        CCDirector.sharedDirector().end();
    }
}
	
