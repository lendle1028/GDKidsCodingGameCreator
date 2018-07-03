extends Node2D

# class member variables go here, for example:
# var a = 2
# var b = "textvar"
func _ready():
	var global=get_node("/root/global");
	var bt1=get_node("1")
	bt1.connect("pressed", self, "switchpoint", ["fisherman_1"])  #[物件, 動作值]
	var bt2=get_node("2")
	bt2.connect("pressed", self, "switchpoint", ["fisherman_2"]) 
	var bt3=get_node("3")
	bt3.connect("pressed", self, "switchpoint", ["fisherman_3"])
	var bt4=get_node("4")
	bt4.connect("pressed", self, "switchpoint", ["fisherman_4"])
	var bt5=get_node("home")
	bt5.connect("pressed", global, "back2SceneSwitcher")
	
	pass

func switchpoint(point):
	if point=="fisherman_1":
		get_tree().change_scene("res://fisherman/Game.tscn")
		var global=get_node("/root/global");
		global.reset()
		
	elif point=="fisherman_2":
		get_tree().change_scene("res://fisherman/Game1.tscn")
		var global=get_node("/root/global");
		global.reset()
		
	elif point=="fisherman_3":
		get_tree().change_scene("res://fisherman/Game2.tscn")
		var global=get_node("/root/global");
		global.reset()
	
	elif point=="fisherman_4":
		get_tree().change_scene("res://fisherman/Game3.tscn")
		var global=get_node("/root/global");
		global.reset()
		
