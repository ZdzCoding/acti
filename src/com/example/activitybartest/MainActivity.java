package com.example.activitybartest;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	private float startX,startY,offsetX,offsetY;
	private Fragment_B fragmentb;
	private Fragment_A fragmenta;
	private Tab edit;
	private Tab computer;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = event.getX();
				startY = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				offsetX = event.getX()-startX;
				offsetY = event.getY()-startY;
				FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
				if (offsetX<-5) {
					//swipeLeft();
					
					fragmentTransaction.replace(android.R.id.content, fragmentb);
					edit.select();
				}else if (offsetX>5) {
					//swipeRight();
					fragmentTransaction.replace(android.R.id.content, fragmenta);
					computer.select();
				}
				fragmentTransaction.commit();
				break;
			}
			return true;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		// 得到Activity的ActionBar
        ActionBar actionBar = getActionBar();
        // 设置AcitonBar的操作模型
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // 将Activity的头部去掉
        actionBar.setDisplayShowTitleEnabled(false);
        // 生成Tab
         edit = actionBar.newTab().setText("Tab B");
         computer = actionBar.newTab().setText("Tab A");
        // 为每个Tab添加Listener
        fragmentb = new Fragment_B();
        fragmenta = new Fragment_A();
        MyTabListener editListener = new MyTabListener(fragmentb);
        edit.setTabListener(editListener);
        MyTabListener computerListener = new MyTabListener(fragmenta);
        computer.setTabListener(computerListener);
        // 将Tab加入ActionBar中
        actionBar.addTab(edit);
        actionBar.addTab(computer);

	}
	@Override
    protected void onStop()
    {
        System.out.println("MainActivity--->onStop");
        super.onStop();
    }

    /**
     * 实现ActionBar.TabListener接口，构造函数中把fragment传进来了，为了更好的控制fragment
     */
    class MyTabListener implements TabListener
    {
        // 接收每个Tab对应的Fragment，操作
        private Fragment fragment;


        public MyTabListener(Fragment fragment)
        {
            this.fragment = fragment;
        }
        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft)
        {
        }
        @Override
        // 当Tab被选中的时候添加对应的Fragment
        public void onTabSelected(Tab tab, FragmentTransaction ft)
        {
            ft.replace(android.R.id.content, fragment, null);//这里要不然使用 ft.replace(R.id.context, fragment, null)，之后不能使用commit(),要不然会报错。或者注释这条语句，使用下面注释的语句
            
           // FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    //试用fragment替换activity中的main组件
    //fragmentTransaction.replace(R.id.context, fragment);
    //提交事物
    //fragmentTransaction.commit();
    //提交事物
    
        }


        // 当Tab没被选中的时候删除对应的此Tab对应的Fragment
        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft)
        {
            ft.remove(fragment);
        }

    }


	
}
