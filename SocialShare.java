package com.linkme.plugins.SocialShare;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import org.apache.cordova.api.LegacyContext;
import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;


public class SocialShare extends Plugin {
	
	private String callback;
	public String smsCopy = ""; 

	@Override
	public PluginResult execute(String action, JSONArray args, String callbackId) {
			
		try
		{
			if (action.equals("startSmsActivity")) {
				
				JSONObject obj = args.getJSONObject(0);
				String type = obj.has("message") ? obj.getString("message") : "";
				startSmsActivity(type ); 
			}
			else if( action.equals("startEmailActivity") ) 
			{
				JSONObject obj = args.getJSONObject(0);
				String msg = obj.has("message") ? obj.getString("message") : "";
				String subject = obj.has("subject") ? obj.getString("subject") : "";
				
				startEmailActivity(msg, subject );
			}
			else if( action.equals("startSocialActivity") ) 
			{
				JSONObject obj = args.getJSONObject(0);
				String msg = obj.has("message") ? obj.getString("message") : "";
				
				startSocialActivity(msg);
			}
			else if( action.equals("startTwitterActivity") ) 
			{
				JSONObject obj = args.getJSONObject(0);
				String msg = obj.has("message") ? obj.getString("message") : "";
				
				startTwitterActivity(msg);
			}
			
		}
		catch (JSONException e) {
            e.printStackTrace();
            return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
        }
		
		
		PluginResult mPlugin = new PluginResult(PluginResult.Status.NO_RESULT);
		mPlugin.setKeepCallback(true);
		this.callback = callbackId;
		return mPlugin;
	}

	public void startSmsActivity( String msg ) {
		
		Uri uri = Uri.parse("smsto:"); 
		Intent it = new Intent(Intent.ACTION_SENDTO, uri); 
        it.putExtra("sms_body",msg ); 
        this.ctx.startActivityForResult( (Plugin) this, it, 1 );
        
	}
	
	public void startEmailActivity ( String msg, String subject )
	{
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		
		// emailIntent.setType("text/plain");
		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject );  
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg );  
		// this.ctx.startActivity(emailIntent);
		cordova.getActivity().startActivity(emailIntent);
		
		
	}
	
	public void startSocialActivity ( String msg )
	{
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("text/plain");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg );
		
		this.ctx.startActivity(Intent.createChooser(emailIntent, "Share in:")); 
	}
	
	public void startTwitterActivity ( String msg )
	{

		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");

		shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,msg);
		PackageManager pm = cordova.getActivity().getPackageManager();
		
		System.out.println("Messaggio da buttare a twetter: "+ msg);
		
		List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
		
		for (final ResolveInfo app : activityList) {
			System.out.println(app.activityInfo.name);
			if ((app.activityInfo.name).equals("com.twitter.android.PostActivity")) {
				System.out.println("Cazzo sono uguali!!");
				final ActivityInfo activity = app.activityInfo;
				final ComponentName name = new ComponentName(
						activity.applicationInfo.packageName, activity.name);
				shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				shareIntent.setComponent(name);
				cordova.getActivity().getBaseContext().startActivity(shareIntent);
//				this.ctx.startActivity(Intent.createChooser(shareIntent, "Share in:"));
				break;
			}
		}
	}
	

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		JSONObject smsObj = new JSONObject();
		try {
			smsObj.put("msg", "done");
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		this.success(new PluginResult(PluginResult.Status.OK,smsObj
				), this.callback);
	}
}
