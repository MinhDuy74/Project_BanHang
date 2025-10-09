const reelVideos = [
  "videos/1.mp4",
  "videos/2.mp4",
  "videos/3.mp4",
  "videos/4.mp4",
  "videos/5.mp4",
  "videos/6.mp4",
  "videos/7.mp4",
  "videos/8.mp4",
  "videos/9.mp4",
  "videos/10.mp4",
  "videos/11.mp4",
  "videos/12.mp4",
  "videos/13.mp4",
  "videos/14.mp4",
  "videos/15.mp4",
  "videos/16.mp4",
  "videos/17.mp4",
  "videos/18.mp4",
  "videos/19.mp4",
  "videos/20.mp4",
  "videos/21.mp4",
  "videos/22.mp4",
  "videos/23.mp4",
  "videos/24.mp4",
  "videos/25.mp4",
  "videos/26.mp4",
  "videos/27.mp4",
  "videos/28.mp4",
  "videos/29.mp4",
  "videos/30.mp4",
  // ... thêm video khác
];
let currentReel = 0;

// --- MINI POPUP --- //
function openReelFull() {
  document.getElementById("full-reel-overlay").style.display = "flex";
  setFullReel(currentReel);
}
function closeMiniReelPopup() {
  document.getElementById("mini-reel-popup").style.display = "none";
}
function setMiniReel(idx) {
  document.getElementById("mini-reel-video").src = reelVideos[idx];
}
function setFullReel(idx) {
  if (idx < 0) idx = reelVideos.length-1;
  if (idx >= reelVideos.length) idx = 0;
  currentReel = idx;
  document.getElementById("full-reel-video").src = reelVideos[idx];
  document.getElementById("full-reel-index").innerText = (idx+1) + "/" + reelVideos.length;
}
function prevReel() { setFullReel(currentReel-1); }
function nextReel() { setFullReel(currentReel+1); }
function closeReelFull() {
  document.getElementById("full-reel-overlay").style.display = "none";
  document.getElementById("full-reel-video").pause();
}

// --- DRAG MINI POPUP --- //
function enableMiniReelDraggable() {
  const popup = document.getElementById('mini-reel-popup');
  let isDrag = false, startX=0, startY=0, offsetX=0, offsetY=0, moved=false;

  popup.addEventListener('mousedown', function(e){
    if (e.target.tagName === "BUTTON" || e.target.classList.contains("mini-reel-btn")) return; // không drag khi click nút
    isDrag = true; moved = false;
    startX = e.clientX; startY = e.clientY;
    offsetX = popup.offsetLeft; offsetY = popup.offsetTop;
    popup.style.transition = 'none';
    document.body.style.userSelect = 'none';
  });
  document.addEventListener('mousemove', function(e){
    if (!isDrag) return;
    let x = offsetX + (e.clientX - startX);
    let y = offsetY + (e.clientY - startY);
    x = Math.max(0, Math.min(window.innerWidth - popup.offsetWidth, x));
    y = Math.max(0, Math.min(window.innerHeight - popup.offsetHeight, y));
    popup.style.left = x + 'px';
    popup.style.top = y + 'px';
    popup.style.right = "auto";
    popup.style.bottom = "auto";
    moved = true;
  });
  document.addEventListener('mouseup', function(e){
    isDrag = false;
    popup.style.transition = '';
    document.body.style.userSelect = '';
  });

  // Mobile
  popup.addEventListener('touchstart', function(e){
    if (e.target.tagName === "BUTTON" || e.target.classList.contains("mini-reel-btn")) return;
    isDrag = true; moved = false;
    let t = e.touches[0];
    startX = t.clientX; startY = t.clientY;
    offsetX = popup.offsetLeft; offsetY = popup.offsetTop;
    popup.style.transition = 'none';
  });
  document.addEventListener('touchmove', function(e){
    if (!isDrag) return;
    let t = e.touches[0];
    let x = offsetX + (t.clientX - startX);
    let y = offsetY + (t.clientY - startY);
    x = Math.max(0, Math.min(window.innerWidth - popup.offsetWidth, x));
    y = Math.max(0, Math.min(window.innerHeight - popup.offsetHeight, y));
    popup.style.left = x + 'px';
    popup.style.top = y + 'px';
    popup.style.right = "auto";
    popup.style.bottom = "auto";
    moved = true;
  });
  document.addEventListener('touchend', function(){
    isDrag = false;
    popup.style.transition = '';
  });

  // Chỉ mở reel lớn khi click vào video, KHÔNG phải khi drag
  const video = document.getElementById("mini-reel-video");
  let clickStart = 0;
  video.onclick = function(e){
    // Nếu vừa drag thì không mở
    if (moved) return;
    openReelFull();
  }
}

window.addEventListener("DOMContentLoaded", function () {
  setMiniReel(0);
  enableMiniReelDraggable();
});