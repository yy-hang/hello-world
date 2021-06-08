var login=function(){
	$("#denglu").show();
	$("#zhuce").hide();
	$("#wangjimm").hide();
};
var logintj=function(){
	var id=$("#id").val();
	var password=$("#password").val();
	if(id==""||password=="")
		{
		alert("请将信息输入完整");
		return;
		}
	$.ajax({
		   type: "post",
		   url: "userlogincheck",
		   data:{"id":id,"password":password},
		   dataType : "json",
		   error : function(){
			   console.log("失败");
			   alert("用户名或密码输入错误,请重新输入");
		   },
		   success: function(msg){
			   console.log(msg);
			   if(msg.result==false||msg==""){
				   alert("用户名或密码输入错误,请重新输入");
			   }if(msg.result){
				   window.location.href="list.html";
			   }
		   }
	});
};