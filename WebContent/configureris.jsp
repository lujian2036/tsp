<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tsp服务</title>
    <link rel="stylesheet" type="text/css" href="./themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./themes/icon.css">
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
<!--    	<script>
    $(document).ready(function(){
    	$.getJSON("./tspinformation",function(data){
    		$.each(data.rows,function(i,value){
    			var trtd='<tr><td><label>'+value.name+ '</label></td><td><input name="'+'tsp_'+value.id + '"    required="true"></td></tr>';
    			$("#tspTable").append(trtd);
    		});
	
    	})});
   	</script> -->
</head>
<body>
	<!-- show tsp datagrid -->
    <table id="showtspdlg" class="easyui-datagrid" fit="true" singleSelect="true"
            url="./gettspTspInformation"
            toolbar="#tb"
            rownumbers="true" pagination="true">
        <thead>
            <tr>
            	<th field="ID" width="80">ID</th>
                <th field="tspName" >tspName</th>
                <th field="tspMethod" >tspMethod</th>
                <th field="tspRoutePath"  align="right">tspRoutePath</th>
                <th field="Name">Name</th>
            </tr>
        </thead>
    </table>
    
    <!-- define toolbar for datagrid -->
    <div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newtsp()">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edittsp()">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletetsp()">删除</a>
	</div>
	
	<!-- define tsp add/modify dialog -->
		<div id="modifydlg" class="easyui-dialog" fit="true" closed="true" buttons="#modifydlgbtn">
		<form id="fm" method="post">
			 <table cellpadding="5"  width="50%" >
				<tr>
					<td><label>接口功能概要：</label></td>
					<td><input name="Name"   class="easyui-validatebox" required=true style="width:100%" ></td>
				</tr>
				<tr>
					<td><label>tsp接口：</label></td>
					<td><input name="tspName" style="width:100%" ></td>
				</tr>
				<tr>
					<td><label>tsp方法：</label></td>
					<td>
					<select class="easyui-combobox" name="tspMethod"  editable="false"  required=true >
						<option value="GET" >GET</option>
						<option value="POST">POST</option>
					</select>
					</td>
					
				</tr>
				<tr>
					<td><label>tsp接口路由：</label></td>
					<td><input name="tspRoutePath" class="easyui-validatebox"    required=true style="width:100%" ></td>
				</tr>
				<tr>
					<td><label>tsp入参：</label></td>
					<td>
					<input name="tspParameter"  class="easyui-textbox" data-options="multiline:true" style="width:100%;height:100px"  >
					</td>
				</tr>
				<tr>
					<td><label>描述信息：</label></td>
					<td ></td>
				</tr>
			</table>
			<input  name="SampleTxt" class="easyui-textbox" data-options="multiline:true" style="width:100%;height:100px" >
			<!-- tsp module -->
			 <table id="tspTable" cellpadding="5">
			</table>
		</form>
	</div>
	
	<div id="modifydlgbtn">
		<a  class="easyui-linkbutton" iconCls="icon-ok" onclick="savetsp()">Save</a>
		<a  class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$(modifydlg).dialog('close')">Cancel</a>
	</div>
	
	<script type="text/javascript">
	//add tsp dynamic
	$.getJSON("./tspinformation",function(data){
		$.each(data.rows,function(i,value){
			var trtd='<tr><td><label>'+value.name+'：'+ '</label></td><td><input name="'+'tsp_'+value.id + '" class="easyui-textbox"></td></tr>';
			$("#tspTable").append(trtd);
		});

	});
	
	//forbidden auto complete
	$('input').attr("AUTOCOMPLETE","off");
	
	var url;
	function newtsp(){
		$('#modifydlg').dialog('open').dialog('setTitle','添加tsp服务');
		$('#fm').form('clear');
		url='./addtsp';
	}
	
	function edittsp(){
		//clear before data 
		$('#fm').form('clear');
		var row=$('#showtspdlg').datagrid('getSelected');
		if(row){
			$('#modifydlg').dialog('open').dialog('setTitle','修改tsp服务');
			$('#fm').form('load',row);
			url='./updatetsp?id='+row.ID;
		}
	}
	
	function savetsp(){
		$('#fm').form('submit',{
			url:url,
			onSubmit:function(){
				return $(this).form('validate')
			},
			success:function(result){
				var result=eval('('+result+')');
				if(!result.success){
					$.messager.show({
						title:'error',
						msg:result.info
					});
				}else{
					$('#modifydlg').dialog('close');
					$('#showtspdlg').datagrid('reload');
				}
			}
		}
	);}
	
	function deletetsp(){
		var row=$('#showtspdlg').datagrid('getSelected');
		if(row){
			$.messager.confirm('Confirm','确定删除?',function(r){
				if(r){
					$.post('./deletetsp',{id:row.ID},function(result){
						if(result.success){
							$('#showtspdlg').datagrid('reload');
						}else{
							$.messager.show({
								title:'error',
								msg:result.info
							});
						}
					},'json')
				}
			});
		}
	}
</script>
</body>
</html>