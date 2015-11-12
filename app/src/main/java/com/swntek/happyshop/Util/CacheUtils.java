package com.swntek.happyshop.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * 缓存工具
 *
 */
public class CacheUtils {

	private static final String CACHE_FILE_NAME = "swntek";
	private static SharedPreferences mSharedPreferences;

	public static void putBoolean(Context context, String key, boolean value) {
		if (mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getBoolean(key, defValue);
	}

	/**
	 * 向SharedPreferences中存储一个字符串
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putString(Context context, String key, String value) {
		if (mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putString(key, value).commit();
	}

	/**
	 * 从SharedPreferences中取一个字符串
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 *            缺省值
	 */
	public static String getString(Context context, String key, String defValue) {
		if (mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME,
					Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getString(key, defValue);
	}

}
