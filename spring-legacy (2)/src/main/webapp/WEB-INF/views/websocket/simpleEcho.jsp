<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>WebSocket - Simple Echo Example</h1>
<hr>

<button type="button" id="btnConnect">Connect</button>
<button type="button" id="btnDisconnect" disabled>Disconnect</button>
<br><br>
<input type="text" id="textMessage">
<button type="button" id="btnSend">Send</button>
<br><br>
<textarea rows="10" cols="50" id="echoArea" readonly></textarea>

<script>
// 웹소켓 객체를 저장할 변수 선언
var webSocket;

var message = document.getElementById('textMessage'); // 텍스트 입력 상자
var taEchoArea = document.getElementById('echoArea'); // 에코메시지 출력 영역


// #btnConnect 버튼 클릭했을 때
document.getElementById('btnConnect').addEventListener('click', function () {
	connect();
});

function connect() {
	console.log('Connect 버튼 클릭됨...');
	
	console.log('location.host : ' + location.host); // localhost:8090
	
	// 연결할 url 주소 문자열
	var url = 'ws://' + location.host + '/echo';
	console.log('연결 url주소 : ' + url); // ws://localhost:8090/echo
	
	// 소켓서버에 연결하기. 연결되면 웹소켓(WebSocket) 객체가 준비됨
	webSocket = new WebSocket(url);
	
	// 웹소켓 객체에 이벤트 동작 4개 연결하기 (open, close, error, message)
	webSocket.onopen = function (event) {
		taEchoArea.value += '서버와 연결됨...\n';
	};
	webSocket.onclose = function (event) {
		taEchoArea.value += '서버와 연결이 끊어짐...\n';
	};
	webSocket.onerror = function (event) {
		taEchoArea.value += '서버와 연결된 와중에 에러가 발생됨...\n';
	};
	webSocket.onmessage = function (event) {
		taEchoArea.value += '서버로부터 받은 메시지 : ' + event.data + '\n';
		
		taEchoArea.scrollTop = taEchoArea.scrollHeight; // 스크롤바 내리기
	};
	
	document.getElementById('btnConnect').disabled = true; // Connect 버튼 비활성화
	document.getElementById('btnDisconnect').disabled = false; // Disconnect 버튼 활성화
} // connect


// #btnSend 버튼 클릭했을 때
document.getElementById('btnSend').addEventListener('click', function () {
	send();
});

document.getElementById('textMessage').addEventListener('keyup', function () {
	if (event.keyCode == 13) { // 엔터키(13)를 누르면
		send();
	}
});

function send() {
	if (message.value == '') {
		return;
	}
	
	// 에코영역에 사용자 입력 메시지를 출력하기
	taEchoArea.value += '서버로 전송된 메시지 : ' + message.value + '\n';
	
	webSocket.send(message.value); // 소켓서버에 메시지 전송하기
	
	message.value = ''; // 텍스트 입력상자 비우기
	message.focus();
} // send


//#btnDisconnect 버튼 클릭했을 때
document.getElementById('btnDisconnect').addEventListener('click', function () {
	disconnect();
});

function disconnect() {
	// 웹소켓 객체를 닫아서 서버 연결을 끊기
	webSocket.close();
	
	document.getElementById('btnDisconnect').disabled = true; // Disconnect 버튼 비활성화
	document.getElementById('btnConnect').disabled = false; // Connect 버튼 활성화
} // disconnect

</script>
</body>
</html>







