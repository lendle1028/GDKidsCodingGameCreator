extends TextureFrame

func _ready():
	set_process(true)
func _process(delta):
	var After= get_node("A")	
	var Back = get_node("B")	
	if(After.is_pressed()):
		get_node("/root/global").set_Scence("res://1.tscn")
	if(Back.is_pressed()):
		get_node("/root/global").set_Scence("res://title_scene.tscn")
