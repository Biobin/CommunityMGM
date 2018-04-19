/**
 * 
 */
function rand(mi,ma){   
	var range = ma - mi;
	var out = mi + Math.round( Math.random() * range) ;	
	return parseInt(out);
};

function getViewSize(){
	var de=document.documentElement;
	var db=document.body;
	var viewW=de.clientWidth==0 ?  db.clientWidth : de.clientWidth;
	var viewH=de.clientHeight==0 ?  db.clientHeight : de.clientHeight;
	return Array(viewW,viewH);
}

$(function(){
	airBalloon('div.air-balloon');
	
	$//管理员帐号验证
	$('#username').textbox({
		//value:'',
		required : true,
		missingMessage : '请输入用户名',
		invalidMessage : '用户名不得为空',
		iconCls:'icon-man'
	});
	
	//管理员密码验证
	$('#password').textbox({
		//value:'',
		required : true,
		validType : 'length[3,30]',
		missingMessage : '请输入密码',
		invalidMessage : '密码长度必须大于或等于3位',
		iconCls:'icon-lock'
	});
	
	//单击登录
	$('#btnLogin').click(function () {
		var username,password;
		if (!$('#username').textbox('isValid')) {
			$('#username').focus();
		} else if (!$('#password').textbox('isValid')) {
			$('#password').focus();
		} else {
			username=$('#username').val()
			password=$('#password').val()
			//password=MD5($('#password').val());
			$.ajax({
				url : basePath+'/loginValidate',
				type : 'post',
				data : {
					username : username,
					password : password,
				},
				success : function (data, response, status) {
					if (data == 1) {
						location.href = basePath+'/index';
					} else if(data == 2){
						$.messager.alert('错误', '用户名不存在！', 'info', function () {
							$('#username').focus().val("");
						});
					}else if(data == 3){
						$.messager.alert('错误', '密码错误！', 'info', function () {
							$('#password').focus().val("");
						});
					}
				}
			});
		}
	});

	$('#password').textbox('textbox').bind('keydown', function(e){
		if (e.keyCode == 13){    // 当按下回车键时接受输入的值。
			$('#btnLogin').click();
	    }
	});
	
});


/*
@function 热气球移动
*/
function airBalloon(balloon){
	var viewSize = [] , viewWidth = 0 , viewHeight = 0 ;
	resize();	
	$(balloon).each(function(){
		$(this).css({top: rand(40, viewHeight * 0.5 ) , left : rand( 10 , viewWidth - $(this).width() ) });
		fly(this);
	});	
	$(window).resize(function(){
		resize()
		$(balloon).each(function(){
			$(this).stop().animate({top: rand(40, viewHeight * 0.5 ) , left : rand( 10 , viewWidth - $(this).width() ) } ,1000 , function(){
				fly(this);
			});
		});
	});
	function resize(){
		viewSize = getViewSize();
		viewWidth = $(document).width() ;
		viewHeight = viewSize[1] ;
	}
	function fly(obj){
		var $obj = $(obj);
		var currentTop = parseInt($obj.css('top'));
		var currentLeft = parseInt($obj.css('left') );
		var targetLeft = rand( 10 , viewWidth - $obj.width() );
		var targetTop = rand(40, viewHeight /2 );
		/*求两点之间的距离*/
		var removing = Math.sqrt( Math.pow( targetLeft - currentLeft , 2 )  + Math.pow( targetTop - currentTop , 2 ) );		
		/*每秒移动24px ，计算所需要的时间，从而保持 气球的速度恒定*/
		var moveTime = removing / 24;		
		$obj.animate({ top : targetTop , left : targetLeft} , moveTime * 1000 , function(){
			setTimeout(function(){
				fly(obj);
			}, rand(1000, 3000) );
		});	
	}
};