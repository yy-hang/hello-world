var lxfsbc=function(){
	var newqqlx=$("#qqlx").val();
	var newwelx=$("#welx").val();
	var newtellx=$("#tellx").val();
	var newbklx=$("#bklx").val();
	if(oldqqlx==newqqlx && oldwelx==newwelx && oldtellx==newtellx && oldbklx==newbklx){
		alert("您并没有数据改动，无需保存！");
		return;
	}else{
	$.ajax({
		   type: "post",
		   url: "updateuserlx",
		   data:{"userId":id,"qqlx":newqqlx,"welx":newwelx,"tellx":newtellx,"bklx":newbklx},
		   dataType : "json",
		   beforeSend:function(){
  	  	           $("#vvv").append('<img src="static/image/loading.gif"/>').append('<p>请稍等...<br><br>');
	        },
		   error : function(){
			   alert("保存失败");
		   },
		   success: function(msg){
			   console.log(msg);
			   if(msg.result){
				   alert("保存成功");
				   setTimeout(location.reload(), 4000);
			   }
		   },
		   complete: function() {$("#vvv").remove();
	        }
		});
	}
};


var bcjb1=function(){
	   var newINTRODUCE=$("#introduce").val().replace(/(^s*)|(s*$)/g, "");
	   var newMOTTO=$("#motto").val().replace(/(^s*)|(s*$)/g, "");
	   var newLIKESKILL=$("#likeskill").val().replace(/(^s*)|(s*$)/g, "");
	   if(newINTRODUCE==oldINTRODUCE && (newMOTTO=="null"?"":newMOTTO)==oldMOTTO && newLIKESKILL==oldLIKESKILL ){
		   alert("您没有数据改动，无需保存！");
			return;
	   }
	   else{
		   $.ajax({
			   type: "post",
			   url: "updatejb1",
			   data:{"userId":id,"introduce":newINTRODUCE,"motto":newMOTTO,"likeskill":newLIKESKILL},
			   dataType : "json",
			   beforeSend:function(){
	  	  	           $("#vvv").append('<img src="static/image/loading.gif"/>');
		        },
			   error : function(){
				   alert("保存失败");
			   },
			   success: function(msg){
				   console.log(msg);
				   if(msg.result){
					   alert("保存成功");
					   window.location.href="userinfoupdate.html";
				   }
			   },
			   complete: function() {$("#vvv").remove();
		        }
			});
	   }
};



	var  bcjb=function(){
	var newZuserName=$("#zusername").val()=="null"?"":$("#zusername").val().replace(/(^s*)|(s*$)/g, "");
	var newZpassword=$("#zpassword").val()=="null"?"":$("#zpassword").val().replace(/(^s*)|(s*$)/g, "");
	var newCzpassword=$("#czpassword").val()=="null"?"":$("#czpassword").val().replace(/(^s*)|(s*$)/g, "");
	var newSex=$("#sex").val()=="null"?"":$("#sex").val().replace(/(^s*)|(s*$)/g, "");
	var newBirthDate=$("#birthdate").val()=="null"?"":$("#birthdate").val().replace(/(^s*)|(s*$)/g, "");
	var newUnitName=$("#unitname").val()=="null"?"":$("#unitname").val().replace(/(^s*)|(s*$)/g, "");
	var newJobPost=$("#jobpost").val()=="null"?"":$("#jobpost").val().replace(/(^s*)|(s*$)/g, "");
	var newMaritalStatus=$("#maritalstatus").val()=="null"||$("#maritalstatus").val()==null?"":$("#maritalstatus").val().replace(/(^s*)|(s*$)/g, "");
	console.log(newZuserName+newZpassword+newSex+newBirthDate+newUnitName+newJobPost+newMaritalStatus);
	if(newZpassword!=newCzpassword){
		alert("密码输入不一致请认真填写！！！");
		return;
	}
	if(newZuserName==oldName && newZpassword==oldPasseord 
			&& newSex==oldSex && newBirthDate==oldBirthdate
			&& newUnitName==oldUnitName && newJobPost==oldJobPost
			&& newMaritalStatus==oldMARITALSTATUS){
		alert("您没有数据改动，无需保存！");
		return;
	}else{
		$.ajax({
			   type: "post",
			   url: "updatejb",
			   data:{"userId":id,"name":newZuserName,"password":newZpassword,
				   "sex":newSex,"birthDate":newBirthDate,
				   "unitName":newUnitName,"jobPost":newJobPost,
				   "maritalStatus":newMaritalStatus},
			   dataType : "json",
			   beforeSend:function(){
	  	  	           $("#vvv").append('<img src="static/image/loading.gif"/>').append('<p>请稍等...<br><br>');
		        },
			   error : function(){
				   alert("保存失败");
			   },
			   success: function(msg){
				   console.log(msg);
				   if(msg.result){
					   alert("保存成功");
					   window.location.href="userinfoupdate.html";
				   }
			   },
			   complete: function() {$("#vvv").remove();
		        }
			});
	}
};
	