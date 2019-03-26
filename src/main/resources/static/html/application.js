layui.use(['table', 'form'], function(){
//  var form = layui.form;
//  form.render();
  var table = layui.table;
  var pageNo = 1;
  var applicationList = {
      elem: '#applicationList' //指定原始表格元素选择器（推荐id选择器）
      , url: '/resource/pageApplicationByConditions'
      , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
          layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
  		,curr: pageNo
      }
      , id: 'applicationList'
      , limits: [1, 10, 20, 30, 60, 90, 150, 300]
      , limit: 10 //默认采用60
      , cols: [[ //标题栏
          {field: 'name', title: '应用名称' }
          , {field: 'code', title: '应用编码'}
           , {field: 'code', title: '应用编码'}
            , {field: 'code', title: '应用编码'}
             , {field: 'code', title: '应用编码'}
              , {field: 'code', title: '应用编码'}
               , {field: 'code', title: '应用编码'}
                , {field: 'code', title: '应用编码'}
          , {title: '操作' ,fixed: 'right', width:250, align:'center', toolbar: '#barDemo'}
      ]]
      ,where: {
      }
      ,text: {
      	none: "暂无数据"
      }
      , contentType: 'application/json'
      , method: 'post'
      , request: {
    	  pageName: 'pageNo' //页码的参数名称，默认：page
          , limitName: 'pageSize' //每页数据量的参数名，默认：limit
      }
      , done: function (res) {
    	  pageNo = res.page.current;
      }
  };
  var applicationListRender = table.render(applicationList);
  
  //编辑/删除  监听
  table.on('tool(application)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	  var tr = obj.tr; //获得当前行 tr 的DOM对象
	 
	  if(layEvent === 'edit'){
		  //修改
		  editFunction(data);
	  } else if(layEvent === 'del'){
		  //删除
		  deleteFunction(data);
		  
	  }
	});
  
  //搜索框内容变化监听
  $("#searchText").bind('input propertychange',function(){
	  //重载table
	  reloadApplicationList($("#searchText").val());  
  	}
  );
  
  //重载table数据
  var reloadApplicationList = function (item) {
	  applicationListRender.reload({ //此处是上文提到的 初始化标识id
          where: {
        	  "name":item
          },
          page: {
              curr: pageNo //重新从第 1 页开始
          }
      });
  };
  
  //添加按钮事件
  $("#add").click(function(){
	  var url = "common/toHtml.do?view=resource/application/add";
	  var addPage = layer.open({
          type: 2,
          title: '添加应用',
          btn: ['提交'],
          yes: function(index, layero){
        	  
        	  //获取add页面的dom
              var body = layer.getChildFrame('body', index);
              //查找输入的信息
              var appName = body.find("#appName").val();
              var appCode = body.find("#appCode").val();
              
              /*
               * 表单校验
               */
              if(appName == ""){
            	  layer.msg("请输入应用名称");
            	  return false;
              }
              if(appCode == ""){
            	  layer.msg("请输入应用编码");
            	  return false;
              }
              if(appCode.length > 50){
            	  layer.msg("应用编码字符数应小于50");
            	  return false;
              }
              if(appName.length > 50){
            	  layer.msg("应用名称字数应小于50");
            	  return false;
              }
              //正则表达式判断中文
              var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;  
              if(reg.test(appCode)){
            	  layer.msg("应用编码不能为中文");
            	  return false;
              }
              
              //ajax请求接口
              $.ajax({
            	  url:"/resource/createApplication",
            	  type:"post",
            	  contentType:"application/json;charset=utf-8",
            	  data:JSON.stringify({
            		"name":appName,
            		"code":appCode
            	  }),
            	  success:function(result){
            		  if(result.code == 0){
            			  //关闭弹出层
            			  layer.close(addPage);
            			  //重载table
            			  reloadApplicationList();
            			  layer.msg("添加成功");
            		  }else{
            			  layer.msg("添加失败：" + result.msg);
            		  }
            	  }
              });
          },
          skin: 'layui-layer-rim', //加上边框
          closeBtn: 2,
          shadeClose: false,
          shade: 0.3,
          area: ['500px', '220px'],
          content: url
      });
  });
  
  //删除按钮点击事件
  function deleteFunction(data){
	  layer.confirm('真的要删除吗？', {icon:3, title:"提示"}, function(index){
		  //获取当前行的appCode
          var appCode = data.code;
		  $.ajax({
	    	  url:"/resource/removeApplication/" + appCode,
	    	  type:"delete",
	    	  success:function(res){
	    		  if(res.code == 0){
	    			  layer.close(index);
	    			  layer.msg("删除成功");
	    			  //重载table
        			  reloadApplicationList();
	    		  }else{
	    			  layer.close(index);
	    			  layer.msg(res.msg);
	    		  }
	    	  }
	      });
	  });
  }
  
  
  //修改按钮点击执行
  function editFunction(data){
	  var url = "common/toHtml.do?view=resource/application/add";
	  var addPage = layer.open({
          type: 2,
          title: '修改应用',
          btn: ['提交'],
          success: function(layero, index){
        	  //获取add页面的dom
        	  var body = layer.getChildFrame('body', index);
        	  /*
               * 数据回显
               */
        	  //获取当前行的appCode
              var appCode = data.code;
			  $.ajax({
		    	  url:"/resource/queryApplication/" + appCode,
		    	  type:"get",
		    	  success:function(res){
		    		  if(res.code == 0){
		    			  body.find("#appName").val(res.result.name);
		    			  body.find("#appCode").val(res.result.code);
		    			  body.find("#appCode").attr("readonly", "readonly");
		    			  body.find("#appCode").attr("");
		    		  }else{
		    			  layer.msg("数据回显失败");
		    		  }
		    	  }
		      });
          },
          yes: function(index, layero){
        	  //获取add页面的dom
              var body = layer.getChildFrame('body', index);
              //查找输入的信息
              var appName = body.find("#appName").val();
              var appCode = body.find("#appCode").val();
              /*
               * 表单校验
               */
              if(appName == ""){
            	  layer.msg("请输入应用名称");
            	  return false;
              }
              if(appName.length > 50){
            	  layer.msg("应用名称字数应小于50");
            	  return false;
              }
              /*
			   * 修改数据
			   */
              //ajax请求接口
              $.ajax({
            	  url:"/resource/modifyApplication",
            	  type:"put",
            	  contentType:"application/json;charset=utf-8",
            	  data:JSON.stringify({
            		"name":appName,
            		"code":appCode
            	  }),
            	  success:function(result){
            		  if(result.code == 0){
            			  //关闭弹出层
            			  layer.close(addPage);
            			  //重载table
            			  reloadApplicationList();
            			  layer.msg("修改成功");
            		  }else{
            			  layer.msg("修改失败: " + result.msg);
            		  }
            	  }
              });
              
          },
          skin: 'layui-layer-rim', //加上边框
          closeBtn: 2,
          shadeClose: false,
          shade: 0.3,
          area: ['500px', '220px'],
          content: url
      });
  }
  
});