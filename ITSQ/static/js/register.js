var register=function(){
	$("#denglu").hide();
	$("#zhuce").show();
	$("#wangjimm").hide();
};

var registertj=function(){
	var zusername=$("#zusername").val();
	var password=$("#zpassword").val();
	var cpassword=$("#czpassword").val();
	var sex=$("#sex").val();
	var likeskill=$("#likeskill").val();
	if(zusername==""||password==""||cpassword=="")
		{
		alert("请将必要信息输入完整");
		return;
		}
	if(password!=cpassword){
		alert("输入密码不一致，请重新输入");
		return;
	}
	if(password.length<6){
		alert("密码不能小于6位,请重新填写");
		return;
	}
	$.ajax({
		   type: "post",
		   url: "userregister",
		   data:{
			   "zusername":zusername,
			   "password":password,
			   "sex":sex,
			   "likeskill":likeskill,
			   "birthdate":$("#birthdate").val(),
			   "unitname":$("#unitname").val(),
			   "jobpost":$("#jobpost").val(),
			   "maritalstatus":$("#maritalstatus").val()
			   },
		   dataType : "json",
		   error : function(){
			   console.log("失败");
		   },
		   success: function(msg){
			   console.log(msg);
			   if(msg.zresult==false||msg==""){
				   alert("注册失败");
			   }if(msg.zresult){
				   alert("注册成功!您的账号id为"+msg.userid+"请返回登录");
				   clearval();
					$("#denglu").show();
					$("#zhuce").hide();
					$("#id").val(msg.userid);
					$("#password").val(msg.password);
			   }
		   }
	});
};
