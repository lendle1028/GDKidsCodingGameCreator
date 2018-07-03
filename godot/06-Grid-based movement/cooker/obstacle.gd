extends AnimatedSprite

# class member variables go here, for example:
# var a = 2
# var b = "textvar"


var step=0;
func _ready():
	# Called every time the node is added to the scene.
	# Initialization here
	set_process(true)
	pass

func _process(delta):
	if(Input.is_action_pressed("attack")):
		attacking=true
	if(attacking==true && step<10):
		step=step+1
		
	if(step==10 || attacking==false):
		attacking=false
		step=0
	self.set_frame(step)
