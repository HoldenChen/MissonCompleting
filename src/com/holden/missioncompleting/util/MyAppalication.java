package com.holden.missioncompleting.util;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;

public class MyAppalication extends Application {

	  private ArrayList<Activity> list = new ArrayList<Activity>();
	  private static MyAppalication instance;

	  public MyAppalication() {
	  }

	  /**
	   * 
	   * @return
	   */
	  public static MyAppalication getInstance() {
	    if (null == instance) {
	      instance = new MyAppalication();
	    }
	    return instance;
	  }

	  /**
	   * 
	   * @param activity
	   */
	  public void addActivity(Activity activity) {
	    list.add(activity);

	  }

	  /**
	   */
	  public void exit() {
	    try {
	      for (Activity activity : list) {
	        activity.finish();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      System.exit(0);
	    }
	  }

	  @Override
	  public void onLowMemory() {
	    super.onLowMemory();
	    System.gc();
	  }
	}
