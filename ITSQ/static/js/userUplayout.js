	var id=null;
	var fileName=null;
	//联系信息
	var oldqqlx=null;
	var oldwelx=null;
	var oldtellx=null;
	var oldbklx=null;

	//基本信息
	var oldName=null;
	var oldPasseord=null;
	var oldSex=null;
	var oldBirthdate=null;
	var oldUnitName=null;
	var oldJobPost=null;
	var oldMARITALSTATUS=null;
	var oldINTRODUCE=null
	var oldMOTTO=null;
	var oldLIKESKILL=null;

$(function(){
	$.ajax({
		   type: "post",
		   url: "homepageuser",
		   dataType : "json",
		   beforeSend:function(){
	  	           $("#vvv").append('<img src="static/image/loading.gif"/>').append('<p>请稍等...<br><br>');
        	},
		   error : function(){
			   alert("获取用户信息失败");
		   },
		   success: function(msg){
			   console.log(msg);
			   if(msg == null || !msg.result){
				   alert("获取用户信息失败");
				   return;
			   }
			 id=msg.id;
			  $("#zusername").val(msg.name);
			  $("#zpassword").val(msg.password);
			  $("#czpassword").val(msg.password);
			  $("#sex").val(msg.sex);
			  $("#birthdate").val(msg.birthTime=="null"?"":msg.birthTime);
			  $("#unitname").val(msg.unitName=="null"?"":msg.unitName);
			  $("#jobpost").val(msg.jobPost=="null"?"":msg.jobPost);
			  $("#maritalstatus").val(msg.maritalStatus=="null"?"":msg.maritalStatus);
			  $("#introduce").val(msg.introduce=="null"?"":msg.introduce);
			  $("#motto").val(msg.motto=="null"?"":msg.motto);
			  $("#likeskill").val(msg.likeSkill=="null"?"":msg.likeSkill);
			  $("#imghead").attr("src",msg.headImage);
			  
			   oldName=msg.name;
			   oldPasseord=msg.password;
			   msg.sex=="null" || msg.sex==null?oldSex="":oldSex=msg.sex;
			   msg.birthTime=="null" || msg.birthTime==null?oldBirthdate="":oldBirthdate=msg.birthTime;
			   msg.unitName=="null" || msg.unitName==null?oldUnitName="":oldUnitName=msg.unitName;
			   msg.jobPost=="null" || msg.jobPost==null?oldJobPost="":oldJobPost=msg.jobPost;
			   msg.maritalStatus==null || msg.maritalStatus=="null"?oldMARITALSTATUS="":oldMARITALSTATUS=msg.maritalStatus;
			   msg.introduce==null || msg.introduce=="null"?oldINTRODUCE="":oldINTRODUCE=msg.introduce;
			   msg.motto=="null" || msg.motto==null?oldMOTTO="":oldMOTTO=msg.motto;
			   msg.likeSkill=="null" || msg.likeSkill==null?oldLIKESKILL="":oldLIKESKILL=msg.likeSkill;
			   
			  
			  $.ajax({
				   type: "post",
				   url: "getuserlx",
				   data:{"userId":id},
				   dataType : "json",
				   beforeSend:function(){
		  	           $("#vvv").append('<img src="static/image/loading.gif"/>').append('<p>请稍等...<br><br>');
	        		},
				   error : function(){
					   alert("获取用户信息失败");
				   },
				   success: function(msg){
					   console.log(msg);
					   if(msg == null){
						   alert("获取用户信息失败");
						   return;
					   }
					  $("#qqlx").val(msg.qqLx);
					  $("#welx").val(msg.weLx);
					  $("#tellx").val(msg.telLx);
					  $("#bklx").val(msg.bkLx);
					  oldqqlx=msg.qqLx;
					  oldwelx=msg.weLx;
					  oldtellx=msg.telLx;
					  oldbklx=msg.bkLx;
				   },
				   complete: function() {$("#vvv").remove();
			        }
				});
		   },
		   complete: function() {$("#vvv").remove();
	        }
		});

	$("#jb").show();
	$("#jb1").hide();
	$("#lxfs").hide();
	$("#userhead").hide();
	$("#jbbu").click(function(){
		$("#jb").show();
		$("#jb1").hide();
		$("#lxfs").hide();
		$("#userhead").hide();
	});
	$("#xqbu").click(function(){
		$("#jb").hide();
		$("#jb1").show();
		$("#lxfs").hide();
		$("#userhead").hide();
	});
	$("#lxbu").click(function(){
		$("#jb").hide();
		$("#jb1").hide();
		$("#lxfs").show();
		$("#userhead").hide();
	});
	$("#userheadima").click(function(){
		$("#jb").hide();
		$("#jb1").hide();
		$("#lxfs").hide();
		$("#userhead").show();
	});
});
