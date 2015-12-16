<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="./themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./themes/icon.css">
<!--     <link rel="stylesheet" type="text/css" href="../demo.css"> -->
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
    <script >
    $(document).ready(function(){
    	/* htmlobj=$.ajax({
    		url:"./getBohTspInformation?page=1&rows=10",
    		async:false});
    	var json=eval(htmlobj);
    	*/
    /* 	json=$.getJSON("./getBohTspInformation",{page:1,rows:10},function(data){
    		alert(data.total);
    		for(var i=0;i<data.total;i++){
    			var input="<input id="+i+" name= " +i +"><br>";
    			$("#input").after(input);
    		}
	
    	}); */
    	$.getJSON("./getBohTspInformation_new",{page:1,rows:10},function(data){
    		$.each(data.rows,function(i,value){
    			var str='<div>'+JSON.stringify(value)+'</div></br>';
    			$("#input").after(str);
    			$.each(value.arrtsp,function(ii,valuee){
    				//var str='<div>'+JSON.stringify(valuee)+ ':'+ valuee.Tsp_server_name+'</div></br>';
        			//$("#input").after(str);
    			})
    			
    		});
	
    	});
    	
    } 
    
    );
    
    </script>
    
</head>
<body>
<input id="input" value="test">
</body>
</html>