extends Node2D
#test

func _ready():
	set_process(true)
	
func _process(delta):
	if(Input.is_mouse_button_pressed(BUTTON_LEFT)):
		#get_node("/root/global").gamepoint("id55","10325209","point2","325225222","25","N")
		get_node("/root/global").gamestatus("gameid1","mapid25","add","up","225222")
		

