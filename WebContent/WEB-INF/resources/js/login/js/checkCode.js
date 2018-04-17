// 在全局定义验证码
var code;
// 产生验证码
function createCode() {
	code = "";
	// 验证码的长度
	var codeLength = 4;
	var checkCode = document.getElementById("code");
	// 随机数
	var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	// 循环操作
	// 取得随机数的索引（0~35）
	// 根据索引取得随机数加到code上
	for ( var i = 0; i < codeLength; i++) {
		var index = Math.floor(Math.random() * 36);
		code += random[index];
	}
	// 把code值赋给验证码
	checkCode.value = code;
}
// 校验验证码
function validate() 
{
	// 取得输入的验证码并转化为大写
	var inputCode = document.getElementById("codeVal").value.toUpperCase(); 
	// 若输入的验证码长度为0
	if (""==inputCode) 
	{ 
		show_err_msg('请输入验证码！');
		 //$("#error").html("<span>请输入验证码！</span>");
		 //$("#error").show();
		// 则弹出请输入验证码
		return false;
		// 若输入的验证码与产生的验证码不一致时
	} 
	else if (inputCode != code) 
	{ 
		show_err_msg('验证码输入错误！'); 
		// 则弹出验证码输入错误
		/*showSighMark("验证码输入错误");
        setTimeout(hideSighMark, 1000);*/
		// 刷新验证码
		createCode();
		// 清空文本框
		document.getElementById("codeVal").value = "";
		return false;
	} 
	else 
	{ 
		// 输入正确时
		return true;
	}
}
