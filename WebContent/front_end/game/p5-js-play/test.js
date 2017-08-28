var img;  // Declare variable 'img'.

function setup() {
  createCanvas(720, 400);
  img = createSprite(600, 200, 50, 100);
  img.addAnimation("sticker1088/1088_pafk_kira_deco_003.png","sticker1088/.png");  // Load the image
}

function draw() {
	background(50);
	img.attractionPoint(.2, mouseX, mouseY);
	img.maxSpeed = 4;
	drawSprites();
}
