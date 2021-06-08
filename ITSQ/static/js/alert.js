	function alert(e){
    	$("body").append("<div id='alertMsg'><span>"+e+"</span></div>");
   		 //clearmsg();
   		setTimeout(clearmsg, 1000);
		};
	    function clearmsg(){
    	var t = setTimeout(function(){
       	 $("#alertMsg").remove();
    	},1000)
	    };