Phonegap Social Share plugin for Android
========================================

Social Share plugin for Android Cordova 2.2.0 - Email, Sms ,Facebook Twitter share

Fore mode details please refer the below link. 

http://tech.sarathdr.com/?p=785


Author: Sarath D R

Web: http://tech.sarathdr.com 


Edited By: link-me 
Website: www.link-me.it

How To Use:
========================================

1. In your phongap project (Android) create the folder src/com/linkme/plugins/SocialShare and copy there the SocialShare.java file.

2. In your config.xml insert:   

            <plugin name="SocialShare" value="com.linkme.plugins.SocialShare.SocialShare"/>

3. In your index.html insert:

            <script type="text/javascript" src="js/social.js"></script>

4. On document.ready call:

            window.SocialShare = new SocialShare(); 

5. Whenever you need call this js functions:

Share to Twitter:

        function share_to_TW(message){
          window.SocialShare.twitter({message:message},
              function(msg) {},
      	      function(fail) {}
	       );  
        }

Share to Mail:

        function share_to_MAIL(body,subject){
          window.SocialShare.email({message:body, subject: subject},
            function(msg) {},
            function(fail) {}
          );
        }

Share to SMS:

        function share_to_SMS(message){
          window.SocialShare.sms({message:message},
            function(msg) {},
            function(fail) {}
          );
        }

Share to any App:

        function share_to_Social(message){
           window.ShareSocial.social({message:message},
      	      function(msg) {},
	    	      function(fail) {}
	          );           
        }
