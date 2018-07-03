extends KinematicBody2D

var grid
var game

var object

func _ready():
	grid = get_parent()
	game=grid.get_parent()
	object=grid.getObjectFromNode(self)
	set_fixed_process(true)

func _fixed_process(delta):
#主要在這邊處理
	var global=get_node("/root/global");
	if(!global.running):
		return;
	object.tick(delta)
