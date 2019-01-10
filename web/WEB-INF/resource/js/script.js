var chessBoard = [];
var me = true;//是否该我下
var over = true;//是否结束
var myColor = [];
var flag = true;
var otherColor = [];

var Chat = {};

var chess = document.getElementById('chess');
var context = chess.getContext('2d');


var drawChessBoard = function () {
    for (var i = 0; i < 15; i++) {
        context.moveTo(15 + i * 30, 15);
        context.lineTo(15 + i * 30, 435);
        context.stroke();//竖线
        context.moveTo(15, 15 + i * 30);
        context.lineTo(435, 15 + i * 30);
        context.stroke();//横线
    }
};

chess.onclick = function (e) {
    if (over) {
        return;
    }
    if (!me) {
        return;
    }
    var x = e.offsetX;
    var y = e.offsetY;
    var i = Math.floor(x / 30);
    var j = Math.floor(y / 30);
    if (chessBoard[i] [j] === 0) {
        // oneStep(i, j, me);

        Chat.sendMessage("{\"type\":1,\"x\":" + i + ",\"y\":" + j + "}")
        // Chat.sendMessage("{x=" + i + "&y=" + j);
        // me = false;
    }
};
//画棋子
var oneStep = function (i, j, isMe) {
    context.beginPath();
    context.arc(15 + i * 30, 15 + j * 30, 13, 0, 2 * Math.PI);
    context.closePath();
    var gradient = context.createRadialGradient(15 + i * 30 + 2, 15 + j * 30 - 2, 13, 15 + i * 30 + 2, 15 + j * 30 - 2, 0);
    if (isMe) {
        gradient.addColorStop(0, myColor[0]);
        gradient.addColorStop(1, myColor[1]);
        // gradient.addColorStop(0, "#0A0A0A");
        // gradient.addColorStop(1, "#636766");
    } else {
        gradient.addColorStop(0, otherColor[0]);
        gradient.addColorStop(1, otherColor[1]);
        // gradient.addColorStop(0, "#D1D1D1");
        // gradient.addColorStop(1, "#f6f9ab");
    }
    context.fillStyle = gradient;
    context.fill();
};


Chat.socket = null;
Chat.connect = function (host) {
    if ('WebSocket' in window) {
        Chat.socket = new WebSocket(host);
    } else if ('MozWebSocket' in window) {
        Chat.socket = new MozWebSocket(host);
    } else {
        // alert('Error: WebSocket is not supported by this browser.');
        return;
    }

    Chat.socket.onopen = function () {
        // alert('Info: WebSocket connection opened.');
        // document.getElementById('chat').onkeydown = function (event) {
        //     if (event.keyCode === 13) {
        //         Chat.sendMessage();
        //     }
        // };
        changeNetwork("连接中。。。");
    };

    Chat.socket.onclose = function () {
        // document.getElementById('chat').onkeydown = null;
        // alert('Info: WebSocket closed.');
        console.log('掉线')
        changeNetwork("连接已断开。。。");
    };

    Chat.socket.onmessage = function (message) {
        // alert(message.data);
        var json = eval('(' + message.data + ')');
        if (json.type === 0) {//棋局状态改变，棋局结束
            // alert("进去1");
            if (json.boardStatus === 2) {//白方胜
                if (flag) {//自己为白子
                    alert("你赢了");
                } else {
                    alert("你输了")
                }
            } else if (json.boardStatus === 3) {//黑方胜
                if (flag) {//自己为白子
                    alert("你输了")
                } else {
                    alert("你赢了");
                }
            } else if (json.boardStatus === 1) {//对手加入游戏
                over = false;
                me = true;
                alert('对手加入游戏，可以开始本局');
                exchange();
            }
        }
        else if (json.type === 1) {//棋局未结束，落子
            // alert("进去2");
            oneStep(json.x, json.y, json.userFlag);
            chessBoard[json.x] [json.y] = 1;
            me = !json.userFlag;
            exchange();
        }
    };
};

Chat.initialize = function () {
    if (window.location.protocol === 'http:') {
        Chat.connect('ws://' + window.location.host + '/flag');
    } else {
        Chat.connect('wss://' + window.location.host + '/flag');
    }
};

Chat.sendMessage = (function (message) {
    // var message = document.getElementById('chat').value;
    if (message !== '') {
        Chat.socket.send(message);
        // document.getElementById('chat').value = '';
    }
});
function exchange() {
    $('.show>span:eq(2)>span:eq(0)').html(me ? '自己' : '对方');
}
function changeNetwork(message) {
    $('.show>span:eq(3)>span:eq(0)').html(message);
}
$(function () {
    //获取canvas 划线颜色

    context.strokeStyle = "#BFBFBF";

    var logo = new Image();//声明新的图片
    logo.src = "resource/quartz/007.jpg";//获取图片地址
    logo.onload = function () {
        context.drawImage(logo, 0, 0, 450, 450);//在图片载入完成之后绘制图片
        drawChessBoard();//绘制棋盘

        init();

    };

    for (var i = 0; i < 15; i++) {
        chessBoard [i] = [];
        for (var j = 0; j < 15; j++) {
            chessBoard [i] [j] = 0;
        }
    }

    Chat.initialize();
})

function init() {
    var colors = ["#0A0A0A", "#636766", "#D1D1D1", "#f6f9ab"];

    $.ajax({
        url: 'init',
        type: 'get',
        dataType: 'json',
        error: function () {
            alert("error");
        },
        success: function (result) {
            if (result.status) {
                if (result.userFlag === 1) {//自己为白子
                    myColor.push(colors[2]);
                    myColor.push(colors[3]);

                    otherColor.push(colors[0]);
                    otherColor.push(colors[1]);

                    flag = true;
                    $('.show>span:eq(0)>span:eq(0)').html('白色');
                    $('.show>span:eq(1)>span:eq(0)').html('黑色');
                } else if (result.userFlag === 2) {//自己为黑子
                    myColor.push(colors[0]);
                    myColor.push(colors[1]);

                    otherColor.push(colors[2]);
                    otherColor.push(colors[3]);

                    flag = false;
                    $('.show>span:eq(0)>span:eq(0)').html('黑色');
                    $('.show>span:eq(1)>span:eq(0)').html('白色');
                }
                me = result.isMe;
                $('.show>span:eq(2)>span:eq(0)').html(me ? '自己' : '对方');
                //画棋子
                for (var i = 0; i < result.chesses.length; i++) {
                    oneStep(result.chesses[i].x, result.chesses[i].y, result.chesses[i].flag);
                    // me = !result.chesses[i].flag;
                }
                over = !(result.boardStatus === 1);
                if (result.boardStatus === 0) {
                    alert('暂时没有对手哦！请等待别人加入棋局');
                }
                // alert("is me = " + me);
            }
        }
    });
}
//    document.addEventListener("DOMContentLoaded", function() {
//        // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
//        var noscripts = document.getElementsByClassName("noscript");
//        for (var i = 0; i < noscripts.length; i++) {
//            noscripts[i].parentNode.removeChild(noscripts[i]);
//        }
//    }, false);

// document.getElementById('send').onclick = Chat.sendMessage;