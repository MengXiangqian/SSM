$(document).ready(function() {
	$("#queryTable").datagrid({
		toolbar : "#tb",// 增加操作的界面，通过id选择器获取。
	})
	$.ajax({
			url: '/SSM/queryUserByName',
			type: 'post',
			data:{"message":""},
			dataType: 'json',
			success: function (result) {
				showData(result);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
	            alert("数据获取失败");
			}
	})
	// 查询的绑定
	$("#query").bind({
		click : function() {
			$.messager.prompt("操作提示", "请输入要搜索的姓名！",function(message){
				$.ajax({
					  url: '/SSM/queryUserByName',
					  type: 'post',
					  data:{"message":message},
					  dataType: 'json',
					  success: function (result) {
					    showData(result);
					  },
					  error: function(XMLHttpRequest, textStatus, errorThrown){
	                        alert("数据获取失败");
					  }
					})
			});
			
		}
	});
	
	
	// 增加用户的js打开
	$("#add").bind({
		click : function() {
			$("#registerDialog").dialog("open").dialog("setTitle", "新增用户");
			$("#userForm").form("clear");
			// 保存用户
			$("#save").bind({
				click : function() {
					// 将form改为ajaxform
					$("#userForm").form("submit", {
						url : '/SSM/addUser',
						onSubmit : function() {
							var isValid = $(this).form("validate");// 验证表单中的一些控件的值是否填写正确，比如某些文本框中的内容必须是数字
							if (!isValid) {
								$.messager.alert("提示操作", "请检查", "error");
							}
							return isValid;
						},
						success : function(result) {
								$.messager.alert("提示操作", "添加成功！", "error");
								$("#registerDialog").dialog("close");
								$("#queryTable").datagrid("reload");
						}
					});
				}
			});
		}
	});
	// 退出用户
	$("#cancel").bind({
		click : function() {
			$("#registerDialog").dialog("close");
		}
	});
	
	//删除用户
	$("#delete").bind({
		click : function() {
			var row = $("#queryTable").datagrid("getSelected");
			if (row) {
				$.messager.confirm("提示","确认是否删除？",function(r){
					if(r){
						$.ajax({
							  url: '/SSM/deleteUser',
							  type: 'post',
							  data:{"message":row.id},
							  dataType: 'json',
							  success: function (result) {
								  
								  $.messager.alert("提示操作", result, "info");
							  },
							  error: function(){
								  $.messager.alert("提示操作", "删除操作失败！", "info");
							  }
							})
					}
				})
			} else {
				$.messager.alert("提示操作", "请选择一条需要删除的记录", "error");
			}
		}
	})
		
	// 修改用户的js打开
	$("#update").bind({
		click : function() {
			var row = $("#queryTable").datagrid("getSelected");
			if (row) {
				$.ajax({
					  url: '/SSM/queryUserById',
					  type: 'post',
					  data:{"message":row.id},
					  dataType: 'json',
					  success: function (result) {
						  $("#userForm").form("clear");
							$("#registerDialog").dialog("open").dialog("setTitle", "修改用户");
							$("#userForm").form("load",result);
					  },
					  error: function(XMLHttpRequest, textStatus, errorThrown){
	                        alert("数据获取失败");
					  }
					})
				// 保存用户
				$("#save").bind({
					click : function() {
						// 将form改为ajaxform
						$("#userForm").form("submit", {
							url : '/SSM/updateUser',
							onSubmit : function() {
								var isValid = $(this).form("validate");// 验证表单中的一些控件的值是否填写正确，比如某些文本框中的内容必须是数字
								if (!isValid) {
									$.messager.alert("提示操作", "请检查", "error");
								}
								return isValid;
							},
							success : function(result) {
									$.messager.show("提示操作", "添加成功！", "error");
									$("#registerDialog").dialog("close");
									$("#queryTable").datagrid("reload");
							}
						});
					}
				});
				
			} else {
				$.messager.alert("提示操作", "请选择一条需要修改的记录", "error");
			}
		}
	});
	
	function showData(result){
		$("#queryTable").datagrid({
			data:result,
			toolbar : "#tb",// 增加操作的界面，通过id选择器获取。
			fitColumns : true,// 让表格列自动适应查询结果的宽度，其中必须指定一列的宽度
			rownumbers : true,// 显示行号
			singleSelect : true,// 只允许选择一行
			pagination : true,// 展现分页
			collapsible : true,// 收起查询结果集
			method : "get",// 默认为post
			columns : [ [ {
				field : "id",
				title : "用户号",
				align : "center"
			}, {
				field : "name",
				title : "名字",
				align : "center"
			}, {
				field : "birthday",
				title : "生日",
				align : "center",
				formatter:formatDate//不能加双引号
			}, {
				field : "age",
				title : "年龄",
				align : "center"
			},{
				field : "gender",
				title : "性别",
				align : "center"
			},{
				field : "loves",
				title : "爱好",
				align : "center"
			},{
				field : "country",
				title : "国家",
				align : "center"
			}, {
				field : "address",
				title : "地址",
				align : "center",
				width : 100
			} ] ]
		});
	}

});