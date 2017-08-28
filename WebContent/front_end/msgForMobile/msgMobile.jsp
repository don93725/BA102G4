<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>

<!DOCTYPE html>
<html>

<head>

<title>WebRTC Reference App</title>

<meta charset="utf-8">
<meta name="description" content="WebRTC reference app">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<link rel="canonical" href="https://apprtc-m.appspot.com/room/06267036">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/msgForMobile/css/main.css">

</head>

<body>

<div id="videos">
<video id="mini-video" autoplay muted></video>
<canvas id="remote-canvas"></canvas>
<video id="remote-video" autoplay></video>
<video id="local-video" autoplay muted></video>
</div>

<footer>
<div id="sharing">
<div id="room-link">Waiting for someone to join this room: <a href="https://apprtc-m.appspot.com/room/06267036" target="_blank">https://apprtc-m.appspot.com/room/06267036</a></div>
</div>
<div id="info"></div>
<div id="status"></div>
</footer>

<div id="icons">

<svg id="mute-audio" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewbox="-10 -10 68 68">
<title>title</title>
<circle cx="24" cy="24" r="34" fill="#666" fill-opacity="0.6">
<title>Mute audio</title>
</circle>
<path id="mute-audio-on" class="hidden" transform="scale(0.6), translate(17,18)" d="M38 22h-3.4c0 1.49-.31 2.87-.87 4.1l2.46 2.46C37.33 26.61 38 24.38 38 22zm-8.03.33c0-.11.03-.22.03-.33V10c0-3.32-2.69-6-6-6s-6 2.68-6 6v.37l11.97 11.96zM8.55 6L6 8.55l12.02 12.02v1.44c0 3.31 2.67 6 5.98 6 .45 0 .88-.06 1.3-.15l3.32 3.32c-1.43.66-3 1.03-4.62 1.03-5.52 0-10.6-4.2-10.6-10.2H10c0 6.83 5.44 12.47 12 13.44V42h4v-6.56c1.81-.27 3.53-.9 5.08-1.81L39.45 42 42 39.46 8.55 6z" fill="white"/>
<path id="mute-audio-off" transform="scale(0.6), translate(17,18)" d="M24 28c3.31 0 5.98-2.69 5.98-6L30 10c0-3.32-2.68-6-6-6-3.31 0-6 2.68-6 6v12c0 3.31 2.69 6 6 6zm10.6-6c0 6-5.07 10.2-10.6 10.2-5.52 0-10.6-4.2-10.6-10.2H10c0 6.83 5.44 12.47 12 13.44V42h4v-6.56c6.56-.97 12-6.61 12-13.44h-3.4z" fill="white"/>
</svg>

<svg id="mute-video" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewbox="-10 -10 68 68">
<circle cx="24" cy="24" r="34" fill="#666" fill-opacity="0.6">
<title>Mute video</title>
</circle>
<path id="mute-video-on" class="hidden" transform="scale(0.6), translate(17,16)" d="M40 8H15.64l8 8H28v4.36l1.13 1.13L36 16v12.36l7.97 7.97L44 36V12c0-2.21-1.79-4-4-4zM4.55 2L2 4.55l4.01 4.01C4.81 9.24 4 10.52 4 12v24c0 2.21 1.79 4 4 4h29.45l4 4L44 41.46 4.55 2zM12 16h1.45L28 30.55V32H12V16z" fill="white"/>
<path id="mute-video-off" transform="scale(0.6), translate(17,16)" d="M40 8H8c-2.21 0-4 1.79-4 4v24c0 2.21 1.79 4 4 4h32c2.21 0 4-1.79 4-4V12c0-2.21-1.79-4-4-4zm-4 24l-8-6.4V32H12V16h16v6.4l8-6.4v16z" fill="white"/>
</svg>

<svg id="switch-video" class="hidden" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewbox="-10 -10 68 68">
<circle cx="24" cy="24" r="34" fill="#666" fill-opacity="0.6">
<title>Switch video camera</title>
</circle>
<path transform="scale(0.7), translate(13,11)" d="M36 19v-7c0-1.1-.89-2-2-2H6c-1.11 0-2 .9-2 2v24c0 1.1.89 2 2 2h28c1.11 0 2-.9 2-2v-7l8 8V11l-8 8zM26 31v-5H14v5l-7-7 7-7v5h12v-5l7 7-7 7z" fill="white"/>
</svg>

<svg id="fullscreen" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewbox="-10 -10 68 68">
<circle cx="24" cy="24" r="34" fill="#666" fill-opacity="0.6">
<title>Enter fullscreen</title>
</circle>
<path id="fullscreen-on" transform="scale(0.8), translate(7,6)" class="hidden" d="M10 32h6v6h4V28H10v4zm6-16h-6v4h10V10h-4v6zm12 22h4v-6h6v-4H28v10zm4-22v-6h-4v10h10v-4h-6z" fill="white"/>
<path id="fullscreen-off" transform="scale(0.8), translate(7,6)" d="M14 28h-4v10h10v-4h-6v-6zm-4-8h4v-6h6v-4H10v10zm24 14h-6v4h10V28h-4v6zm-6-24v4h6v6h4V10H28z" fill="white"/>
</svg>

<svg id="hangup" class="hidden" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewbox="-10 -10 68 68">
<circle cx="24" cy="24" r="34" fill="#666" fill-opacity="0.6">
<title>Toodle-oo!</title>
</circle>
<path transform="scale(0.7), translate(11,10)" d="M24 18c-3.21 0-6.3.5-9.2 1.44v6.21c0 .79-.46 1.47-1.12 1.8-1.95.98-3.74 2.23-5.33 3.7-.36.35-.85.57-1.4.57-.55 0-1.05-.22-1.41-.59L.59 26.18c-.37-.37-.59-.87-.59-1.42 0-.55.22-1.05.59-1.42C6.68 17.55 14.93 14 24 14s17.32 3.55 23.41 9.34c.37.36.59.87.59 1.42 0 .55-.22 1.05-.59 1.41l-4.95 4.95c-.36.36-.86.59-1.41.59-.54 0-1.04-.22-1.4-.57-1.59-1.47-3.38-2.72-5.33-3.7-.66-.33-1.12-1.01-1.12-1.8v-6.21C30.3 18.5 27.21 18 24 18z" fill="white"/>
</svg>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/front_end/msgForMobile/_ah/channel.js"></script>
<script src="${pageContext.request.contextPath}/front_end/msgForMobile/js/stats.js"></script>
<script src="${pageContext.request.contextPath}/front_end/msgForMobile/js/signaling.js"></script>
<script src="${pageContext.request.contextPath}/front_end/msgForMobile/js/infobox.js"></script>
<script src="${pageContext.request.contextPath}/front_end/msgForMobile/js/sdputils.js"></script>
<script src="${pageContext.request.contextPath}/front_end/msgForMobile/js/util.js"></script>
<script src="${pageContext.request.contextPath}/front_end/msgForMobile/js/adapter.js"></script>
<script src="${pageContext.request.contextPath}/front_end/msgForMobile/js/main.js"></script>

<script type="text/javascript">
var params = {
errorMessages: [],
isLoopback: false,

roomId: '06267036',
roomLink: 'https://apprtc-m.appspot.com/room/06267036',

mediaConstraints: {"audio": true, "video": {"optional": [{"minWidth": "1280"}, {"minHeight": "720"}], "mandatory": {}}},
offerConstraints: {"optional": [], "mandatory": {}},
peerConnectionConfig: {"iceServers": [{"urls": "stun:stun.l.google.com:19302"}]},
peerConnectionConstraints: {"optional": [{"googImprovedWifiBwe": true}]},
turnRequestUrl: 'https://computeengineondemand.appspot.com/turn?username=964418935&key=4080218913',

audioSendBitrate: '',
audioSendCodec: '',
audioRecvBitrate: '',
audioRecvCodec: '',
isStereoscopic: '',
opusMaxPbr: '',
opusFec: '',
opusStereo: '',
videoSendBitrate: '',
videoSendInitialBitrate: '',
videoSendCodec: '',
videoRecvBitrate: '',
videoRecvCodec: '',
wssUrl: 'wss://apprtc-ws.webrtc.org:8089/ws',
wssPostUrl: 'https://apprtc-ws.webrtc.org:8089'
};

initialize();
</script>

<script>
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

ga('create', 'UA-48530561-2', 'auto');
ga('send', 'pageview');
</script>

</body>
</html>