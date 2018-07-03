extends 'res://GridObject.gd'

func init(name):
	.init(name, "Potato", "res://cooker/Obstacle_4.tscn")

func getSmallIconName():
	return "Obstacle_4"