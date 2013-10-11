package com.djangodemo.gcmclient.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.djangodemo.gcmclient.GCMDemoMainActivity;
import com.djangodemo.gcmclient.R;

public class GCMDemoMainActivityTest extends ActivityInstrumentationTestCase2<GCMDemoMainActivity> {
	
	private GCMDemoMainActivity mActivity;
	private EditText mTextDisplay;
	
	public GCMDemoMainActivityTest(){
		super(GCMDemoMainActivity.class);
	}
	
    @Override
    protected void setUp() throws Exception {
    	super.setUp();
    	setActivityInitialTouchMode(false);
    	
    	mActivity = getActivity();
    	
    	mTextDisplay = (EditText)mActivity.findViewById(R.id.textarea);
    }
    
    public void testGCMRegister() {
    	assertTrue(mTextDisplay.getText().toString() != null);
    }
}
