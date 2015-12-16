<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOH服务</title>
    <link rel="stylesheet" type="text/css" href="./themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./themes/icon.css">
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
   	<script>
    $(document).ready(function(){
    	json=$.getJSON("./tspinformation",function(data){
    		$.each(data.rows,function(i,value){
    			var trtd='<tr><td><label>'+value.name+ '</label></td><td><input name="'+'tsp_'+value.id + '"    required="true"></td></tr>';
    			$("#tspTable").append(trtd);
    		});
	
    	})});
   	</script>
</head>
<body>
	<!-- show boh datagrid -->
    <table id="showbohdlg" class="easyui-datagrid" fit="true" singleSelect="true"
            url="./getBohTspInformation"
            toolbar="#tb"
            rownumbers="true" pagination="true">
        <thead>
            <tr>
            	<th field="ID" width="80">ID</th>
                <th field="BohName" >BohName</th>
                <th field="BohMethod" >BohMethod</th>
                <th field="BohRoutePath"  align="right">BohRoutePath</th>
                <th field="Name">Name</th>
            </tr>
        </thead>
    </table>
    
    <!-- define toolbar for datagrid -->
    <div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newBoh()">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editBoh()">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletezBoh()">删除</a>
	</div>
	
	<!-- define boh add/modify dialog -->
		<div id="modifydlg" class="easyui-dialog" fit="true" closed="true" buttons="#modifydlgbtn">
		<form id="fm" method="post">
			 <table cellpadding="5" >
				<tr>
					<td><label>接口功能概要：</label></td>
					<td><input name="Name"   class="easyui-validatebox" required=true></td>
				</tr>
				<tr>
					<td><label>BOH接口</label></td>
					<td><input name="BohName"  ></td>
				</tr>
				<tr>
					<td><label>BOH方法</label></td>
					<td><input name="BohMethod"   class="easyui-validatebox" required=true></td>
				</tr>
				<tr>
					<td><label>BOH接口路由</label></td>
					<td><input name="BohRoutePath" class="easyui-validatebox"    required=true></td>
				</tr>
				<tr>
					<td><label>BOH入参</label></td>
					<td><input name="BohParameter"  class="easyui-validatebox"   ></td>
				</tr>
				<tr>
					<td><label>描述信息</label></td>
					<td ></td>
				</tr>
			</table>
			<input  name="SampleTxt" class="easyui-textbox" data-options="multiline:true" style="width:100%;height:100px" >
			<!-- tsp module -->
			 <table id="tspTable" cellpadding="5">
<!-- 				<tr>
					<td><label>tsp1_name</label></td>
					<td><input name="tsp_1"    required="true"></td>
				</tr>
				<tr>
					<td><label>tsp2_name</label></td>
					<td><input name="tsp_2"   required="true"></td>
				</tr>
				<tr>
					<td><label>tsp3_name</label></td>
					<td><input name="tsp_3"   required="true"></td>
				</tr> -->
			</table>
		</form>
	</div>
	
	<div id="modifydlgbtn">
		<a  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveBoh()">Save</a>
		<a  class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$(modifydlg).dialog('close')">Cancel</a>
	</div>
	
	<script type="text/javascript">
	var url;
	function newBoh(){
		$('#modifydlg').dialog('open').dialog('setTitle','添加项目');
		$('#fm').form('clear');
		url='./addboh';
	}
	
	function editBoh(){
		var row=$('#showbohdlg').datagrid('getSelected');
		alert(JSON.stringify(row));
		if(row){
			$('#modifydlg').dialog('open').dialog('setTitle','修改项目');
			$('#fm').form('load',row);
			url='../updateproj?id='+row.id;
		}
	}
	
	function saveBoh(){
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
					$('#showbohdlg').datagrid('reload');
				}
			}
		}
	);}
	
	function deleteBoh(){
		var row=$('#showbohdlg').datagrid('getSelected');
		if(row){
			$.messager.confirm('Confirm','确定删除项目?',function(r){
				if(r){
					$.post('../deleteproj',{id:row.id},function(result){
						if(result.success){
							$('#showbohdlg').datagrid('reload');
						}else{
							$.messager.show({
								title:'error',
								msg:result.errorMsg
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