extends Node2D

# class member variables go here, for example:
# var a = 2
# var b = "textvar"

func _ready():
	# Called every time the node is added to the scene.
	# Initialization here
	var bt1=get_node("buttonMonkeySaver")
	bt1.connect("pressed", self, "switchLevel", ["monkeySaver"])  #[物件, 動作值]
	var bt2=get_node("buttonFisherman")
	bt2.connect("pressed", self, "switchLevel", ["buttonFisherman"])  #[物件, 動作值]
	pass

func switchLevel(level):
	if level=="monkeySaver":
		get_tree().change_scene("res://maze/Game.tscn")
	elif level=="buttonFisherman":
		get_tree().change_scene("res://fisherman/Game.tscn")