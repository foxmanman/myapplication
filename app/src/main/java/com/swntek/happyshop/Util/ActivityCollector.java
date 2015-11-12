package com.swntek.happyshop.Util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * activity管理
 *
 */

public class ActivityCollector {
	
	public  static List<Activity> activitis = new ArrayList<Activity>();
	public static void addActivity(Activity activity){
		activitis.add(activity);
	}
	public static void removeActivity(Activity activity){
		activitis.remove(activity);
	}
	public static void finishAll(){
		for(Activity activity :activitis){
			if(!activity.isFinishing()){
				activity.finish();
			}
		}
	}

}
