var formData=null;
var filesize=null;
    /*
     * 分段读取文件为blob ，并使用ajax上传到服务器
     * 分段上传exe文件会抛出异常
     */
    var fileBox = document.getElementById('file');
    file.onchange = function() {
    	//显示出所选的图片
    	var oFReader = new FileReader();
        var file = document.getElementById('file').files[0];
        oFReader.readAsDataURL(file);
        oFReader.onloadend = function(oFRevent){
            var src = oFRevent.target.result;
            $("img").attr("src",src);
        }
        
        
        // 获取文件对象
        var file = this.files[0];
        var reader = new FileReader();
        var step = 1024 * 1024 * 1024;
        var total = file.size;
        var cuLoaded = 0;
        filesize=total;
        console.info("文件大小：" + file.size);
        var startTime = new Date();
        // 读取一段成功
        reader.onload = function(e) {
            // 处理读取的结果
            var loaded = e.loaded;
            // 将分段数据上传到服务器
            uploadFile(reader.result, cuLoaded, function() {
                console.info('loaded:' + cuLoaded + 'current:' + loaded);
                // 如果没有读完，继续
                cuLoaded += loaded;
                if (cuLoaded < total) {
                    readBlob(cuLoaded);
                } else {
                    console.log('总共用时：'
                            + (new Date().getTime() - startTime.getTime())
                            / 1000);
                    cuLoaded = total;
                }
            });
        }
        // 指定开始位置，分块读取文件
        function readBlob(start) {
            // 指定开始位置和结束位置读取文件
            var blob = file.slice(start, start + step);
            reader.readAsArrayBuffer(blob);
        }
        // 开始读取
        readBlob(0);
        // 关键代码上传到服务器
        function uploadFile(result, startIndex, onSuccess) {
            var blob = new Blob([ result ]);
            // 提交到服务器
            var fd = new FormData();
            fd.append('file', blob);
            fd.append('filename', file.name);
            fd.append('loaded', startIndex);
            // 保存数据到全局变量中
            formData=fd;
        }
    }
    
    var hibc=function(){
    	$.ajax({
    		 url: 'uploadfile',
    		 type: 'POST',
    		 cache: false, //上传文件不需要缓存
    		 data: formData,
    		 processData: false, // 告诉jQuery不要去处理发送的数据
    		 contentType: false, // 告诉jQuery不要去设置Content-Type请求头
    		 beforeSend:function(){
    			 if(filesize>999999){
    				 $("#vvv").append('<img src="static/image/loading.gif"/>').append('<p>文件过大可能导致失败.请稍等...<br><br>');
    			 }else{
    	  	           $("#vvv").append('<img src="static/image/loading.gif"/>').append('<p>请稍等...<br><br>');
    			 }
  	        },
    		 success: function (data) {
    			 if("1"==data){
    				 alert("保存头像成功");
    				 $("#file").val("");
    				 location.reload();
    			 }else{
    				 alert("保存头像失败");
    				 $("#file").val("");
    			 }
    		 },
    		 error: function (data) {
    		alert("保存头像失败");
    		$("#file").val("");
    		 },
    		 complete: function() {$("#vvv").remove();
 	        }
    		})
    }
    