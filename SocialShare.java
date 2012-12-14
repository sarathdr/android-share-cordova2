package com.sarathdr.plugins.SocialShare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

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
		
		this.ctx.startActivity(Intent.createChooser(emailIntent, "Share Dead Tone in:")); 
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
