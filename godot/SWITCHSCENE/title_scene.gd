extends TextureFrame

func _ready():
	set_process(true)
func _process(delta):
	var button1 = get_node("play1")	
	var button2 = get_node("play2")	
	if(button1.is_pressed()):
		get_node("/root/global").set_Scence("res://game.tscn")
	if(button2.is_pressed()):
		get_node("/root/global").set_Scence("res://2.tscn")






