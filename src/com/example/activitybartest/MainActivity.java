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
		// �õ�Activity��ActionBar
        ActionBar actionBar = getActionBar();
        // ����AcitonBar�Ĳ���ģ��
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // ��Activity��ͷ��ȥ��
        actionBar.setDisplayShowTitleEnabled(false);
        // ����Tab
         edit = actionBar.newTab().setText("Tab B");
         computer = actionBar.newTab().setText("Tab A");
        // Ϊÿ��Tab���Listener
        fragmentb = new Fragment_B();
        fragmenta = new Fragment_A();
        MyTabListener editListener = new MyTabListener(fragmentb);
        edit.setTabListener(editListener);
        MyTabListener computerListener = new MyTabListener(fragmenta);
        computer.setTabListener(computerListener);
        // ��Tab����ActionBar��
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
     * ʵ��ActionBar.TabListener�ӿڣ����캯���а�fragment�������ˣ�Ϊ�˸��õĿ���fragment
     */
    class MyTabListener implements TabListener
    {
        // ����ÿ��Tab��Ӧ��Fragment������
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
        // ��Tab��ѡ�е�ʱ����Ӷ�Ӧ��Fragment
        public void onTabSelected(Tab tab, FragmentTransaction ft)
        {
            ft.replace(android.R.id.content, fragment, null);//����Ҫ��Ȼʹ�� ft.replace(R.id.context, fragment, null)��֮����ʹ��commit(),Ҫ��Ȼ�ᱨ������ע��������䣬ʹ������ע�͵����
            
           // FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    //����fragment�滻activity�е�main���
    //fragmentTransaction.replace(R.id.context, fragment);
    //�ύ����
    //fragmentTransaction.commit();
    //�ύ����
    
        }


        // ��Tabû��ѡ�е�ʱ��ɾ����Ӧ�Ĵ�Tab��Ӧ��Fragment
        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft)
        {
            ft.remove(fragment);
        }

    }


	
}
