function snake(){
	this.x = 0;
	this.y = 0;
	this.xspeed = 1;
	this.yspeed = 0;
	this.total = 0;
	this.tail = [];
	this.speed = 8;

	this.eat = function(pos){
		var d = dist(this.x,this.y,pos.x,pos.y);
		if(d<1){
			this.total++;
			this.speed = this.speed + 0.3;
			frameRate(s.speed);
			return true;
		}else{
			return false;
		}
	}

	this.dir = function(x,y){
		if(this.total >= 1){
			if(this.xspeed == -x || this.yspeed == -y){
				return 0;
			}
		}
		this.xspeed = x;
		this.yspeed = y;  
	}

	this.update = function(stop){

		if(stop%2 == 0){
			if(this.total === this.tail.length){
				for(var i=0;i<this.tail.length-1;i++){
					this.tail[i] = this.tail[i+1];
				}
			}
			if(this.total == 0){
				this.total = 2;
				this.x = 200;
				this.y = 200;
				this.tail[0] = createVector(200,200);
				this.tail[1] = createVector(199,200);
				frameRate(10);
			}

			this.tail[this.total - 1] = createVector(this.x,this.y);

			this.x = this.x + this.xspeed * scl;
			this.y = this.y + this.yspeed * scl;

			this.x = constrain(this.x,0,width-scl);
			this.y = constrain(this.y,0,height-scl);
		}else if(stop%2 == 1){
			this.x = this.x;
			this.y = this.y;
		}
	}

	this.death = function(){
		for(var i=0;i<this.tail.length;i++){
			var pos = this.tail[i];
			var d = dist(this.x,this.y,pos.x,pos.y);
			if(d<1){
				alert("泥已經屎惹!");
				this.total = 0;
				this.tail = [];
				this.speed = 8;
				green = false;
			}
		}
	}

	this.show = function(green){
		if(green == false){
			fill(245,150,170);
			for(var i=0;i<this.tail.length;i++){
				rect(this.tail[i].x,this.tail[i].y,scl,scl);
			}
			rect(this.x,this.y,scl,scl);
		}else if(green == true){
			fill(255);
			for(var i=0;i<this.tail.length;i++){
				fill(0,Math.random()*255,0)
				rect(this.tail[i].x,this.tail[i].y,scl,scl);
			}
			rect(this.x,this.y,scl,scl);
		}

	}
}