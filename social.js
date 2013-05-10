 
 var SocialShare = function() {};

SocialShare.sms = function(param, successCallback, failCallback) {

    function success(args) {
        successCallback(args);
    }

    function fail(args) {
    	failCallback(args);
    }

	return PhoneGap.exec(function(args) {
		success(args);
	}, function(args) {
		fail(args);
	}, 'SocialShare', 'startSmsActivity', [param]);
};
 
 
 SocialShare.email = function(param, successCallback, failCallback) {

    function success(args) {
        successCallback(args);
    }

    function fail(args) {
    	failCallback(args);
    }

	return PhoneGap.exec(function(args) {
		success(args);
	}, function(args) {
		fail(args);
	}, 'SocialShare', 'startEmailActivity', [param]);
};
 
 
  SocialShare.social = function(param, successCallback, failCallback) {

    function success(args) {
        successCallback(args);
    }

    function fail(args) {
    	failCallback(args);
    }

	return PhoneGap.exec(function(args) {
		success(args);
	}, function(args) {
		fail(args);
	}, 'SocialShare', 'startSocialActivity', [param]);
};
 
  SocialShare.twitter = function(param, successCallback, failCallback) {

    function success(args) {
        successCallback(args);
    }

    function fail(args) {
    	failCallback(args);
    }

	return PhoneGap.exec(function(args) {
		success(args);
	}, function(args) {
		fail(args);
	}, 'SocialShare', 'startTwitterActivity', [param]);
}; 
 
