var s;
var scl = 20;
var count = 1;
var food1;
var food2;
var green = false;
function setup()
{
	var canv = createCanvas(windowWidth-220, windowHeight-110);
	canv.parent("bgCanvas");
	s = new snake();
	s.speed =  8;
	frameRate(s.speed);
	pickLocation();
}

function pickLocation(which){
	var cols = floor(width/scl);
	var rows = floor(height/scl);
	
	if(which == 1){
		food1 = createVector(floor(random(cols)),floor(random(rows)));
		food1.mult(scl);
	}else if(which == 2) {
		addspeed();
		food2.mult(scl);
	}else if(which == 3){
		food2 = createVector(floor(random(cols)),floor(random(rows)));
		food2.mult(scl);
	}else{
		food1 = createVector(floor(random(cols)),floor(random(rows)));
		food2 = createVector(floor(random(cols)),floor(random(rows)));
		food2.mult(scl);
		food1.mult(scl);
		green = false;
	}
	for(var i=0;i<s.tail.length;i++){
		if(s.tail[i] == food1){
			food1 = createVector(floor(random(cols)),floor(random(rows)));
			food1.mult(scl);
		}
	}
	for(var i=0;i<s.tail.length;i++){
		if(s.tail[i] == food2){
			food2 = createVector(floor(random(cols)),floor(random(rows)));
			food2.mult(scl);
		}
	}
	if(food1 == food2){
			food2 = createVector(floor(random(cols)),floor(random(rows)));
			food2.mult(scl);
	}
}

function addspeed(){
	frameRate(s.speed+15);
	this.draw(1);
	green = true;
	setTimeout("slow()",7000);
	setTimeout("change()",7000);
}

function slow(){
	green = false;
	frameRate(s.speed);
}

function change(){
	pickLocation(3);
	this.draw(0);
}
 
function draw(select){
	clear();
	fill(254,190,190);
	textAlign(RIGHT);
	textSize(14);
	text("W,A,S,D keys to move. Space to start , Enter to grow!", width-30, 30);
	if(select == 1){
		fill(0);
		rect(food2.x,food2.y,scl,scl);
	}else{	
		if(s.eat(food1)){
		pickLocation(1);
		}else if (s.eat(food2)){
			pickLocation(2);
		}
		s.death();
		s.show(green);
		s.update(count);
		fill("#D2691E");
		rect(food1.x,food1.y,scl,scl);
		fill("#00FF00");
		rect(food2.x,food2.y,scl,scl);
	}
	
}

function keyPressed(){
	if(keyCode == 87){
		s.dir(0,-1);
	}else if(keyCode == 83){
		s.dir(0,1);
	}else if(keyCode == 68){
		s.dir(1,0);
	}else if(keyCode == 65){
		s.dir(-1,0);
	}else if(keyCode == 13){
		s.total++;
		s.speed = s.speed + 0.15;
		frameRate(s.speed);
	}else if(keyCode == 32){
		count++;
	}
}
