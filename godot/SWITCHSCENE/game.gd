extends TextureFrame

func _ready():
	set_process(true)
func _process(delta):
	var b = get_node("Button")		
	var back = get_node("Button 2")
	if(b.is_pressed()):
		get_node("/root/global").set_Scence("res://1.tscn")
	if(back.is_pressed()):
		get_node("/root/global").set_Scence("res://title_scene.tscn")



