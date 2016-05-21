<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>目录列表</title>
    <link rel="stylesheet" type="text/css" href="./themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./themes/icon.css">
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
</head>
<body>
<!-- show tsp datagrid -->
    <table id="showtspdlg" class="easyui-datagrid" fit="true" fitColumns=“true” singleSelect="true"
            url="./treeviewinformation"
            toolbar="#tb"
            rownumbers="true" pagination="true">
        <thead>
            <tr>
            	<th field="id" width="80">ID</th>
                <th field="name" >TreeViewName</th>
                <th field="desc" >Summary</th>
    
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
		<div id="modifydlg" class="easyui-dialog" style="width:400px;padding:10px" closed="true" buttons="#modifydlgbtn">
		<form id="fm" method="post">
			 <table cellpadding="5" >
				<tr>
					<td><label>名称：</label></td>
					<td><input name="name"   class="easyui-validatebox" required=true></td>
				</tr>
				<tr>
					<td><label>概要</label></td>
					<td><input name="desc"  ></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="modifydlgbtn">
		<a  class="easyui-linkbutton" iconCls="icon-ok" onclick="savetsp()">Save</a>
		<a  class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$(modifydlg).dialog('close')">Cancel</a>
	</div>
	
	<script type="text/javascript">
	
	//forbidden auto complete
	$('input').attr("AUTOCOMPLETE","off");
	
	var url;
	function newtsp(){
		$('#modifydlg').dialog('open').dialog('setTitle','添加目录列表');
		$('#fm').form('clear');
		url='./addtrewview';
	}
	
	function edittsp(){
		$('#fm').form('clear');
		var row=$('#showtspdlg').datagrid('getSelected');
		if(row){
			$('#modifydlg').dialog('open').dialog('setTitle','修改目录列表');
			$('#fm').form('load',row);
			url='./updatetreeview?id='+ row.id;
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
					$.post('./deletetreeview',{id:row.id},function(result){
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