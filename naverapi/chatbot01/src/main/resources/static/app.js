var stompClient = null;

function setConnected(connected) {  // 연결버튼 누를 시 실행
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#msg").html("");
}

function connect() {
    // SockJS: websocket과 비슷한 기능을 제공하는 자바스크립트 라이브러리
    var socket = new SockJS('/ws'); // 웹소켓 생성
    stompClient = Stomp.over(socket);   // stomp 프로토콜 위에서 sockJS가 동작하도록 클라이언트 객체 생성

    // 서버 연결
    stompClient.connect({}, function (frame) {  //{}: 헤더 설정 부분
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function (message) { // sbscribe(): 상대방에게 메시지를 수신 시 구독 사용
            showMessage("받은 메시지: " + message.body); // 서버에 메시지 전달 후 리턴받는 메시지
        });
    });
}

function disconnect() { // 연결 종료
    if (stompClient !== null) {
        stompClient.disconnect();   // 서버와 연결 해제
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    let message = $("#msg").val()
    showMessage("보낸 메시지: " + message);
    stompClient.send("/app/sendMessage", {}, JSON.stringify(message));  // 클라이언트에서 서버(스브링부트 서버)로 데이터 전송
}

function showMessage(message) { // 메세지 출력
    $("#communicate").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendMessage();
    });

})
