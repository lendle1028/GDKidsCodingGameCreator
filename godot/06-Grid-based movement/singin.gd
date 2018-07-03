extends Node2D

# class member variables go here, for example:
# var a = 2
# var b = "textvar"

func _ready():
	# Called every time the node is added to the scene.
	# Initialization here
	var bt1=get_node("singin")
	bt1.connect("pressed", self, "singin")  
	
	pass
func singin():
		get_tree().change_scene("res://LevelSelection.tscn")
		var global=get_node("/root/global")
		var stuid=get_node("LineEdit").get_text()
		global.studentid = stuid
