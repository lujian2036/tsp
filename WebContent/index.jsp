<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试组-无线中心/品类研发部</title>
    <link rel="stylesheet" type="text/css" href="/tspweb/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/tspweb/themes/icon.css">
    <script type="text/javascript" src="/tspweb/js/jquery.min.js"></script>
    <script type="text/javascript" src="/tspweb/js/jquery.easyui.min.js"></script>

</head>
<body>

    <div class="easyui-layout"   fit="true"  >
<!--         <div data-options="region:'north'" style="height:50px"></div>
        <div data-options="region:'south',split:true" style="height:50px;"></div> -->
        <div data-options="region:'west',title:'导航区',split:true"  style="width:200px;">
        	<!-- accordion -->
		     <div id="aa" class="easyui-accordion" fit="true"   >
				<div title="业务功能">	
					<ul class="easyui-tree">
						<li><a onclick="addMultiTab('前台网站tsp服务接口','showservice.html')">前台网站tsp服务接口</a></li>
						<li><a onclick="alert('新功能正在开发中，如果你有需求添加或者bug报告，请联系无线中心/品类研发部/测试组')">待扩展服务...</a></li>
					</ul>
				</div>
		        <div title="系统配置">
		            <ul class="easyui-tree">
						<li><a onclick='addTab("配置Tsp服务中心","showtsp.jsp")'>配置Tsp服务中心</a></li>
						<li><a onclick="addTab('配置BOH服务','configureboh.jsp')">配置BOH服务</a></li>
						<li><a onclick="alert('新功能正在开发中，如果你有需求添加或者bug报告，请联系无线中心/品类研发部/测试组')">待扩展服务...</a></li>
					</ul>
		        </div>
		    </div>
        
        
        </div>
        <div data-options="region:'center',iconCls:'icon-ok'">
        	<div id="tt" class="easyui-tabs" fit="true">
        	</div>
   		</div>
   
   		
  <script type="text/javascript">
    
    <!--only one tab-->
	    function addTab(title, url){
	        if ($('#tt').tabs('exists', title)){
	            $('#tt').tabs('select', title);
	        } else {
	            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	            $('#tt').tabs('add',{
	                title:title,
	                content:content,
	                closable:true
	            });
	        }
	    }
	    
	     /* multi tab */
	    function addMultiTab(title, url){
	            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	            $('#tt').tabs('add',{
	                title:title,
	                content:content,
	                closable:true
	            });
	        
	    }
	    
	     
    </script>
    
</body>
</html>