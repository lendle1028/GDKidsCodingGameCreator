extends 'res://GridObject.gd'

func init(name):
	.init(name, "Onion", "res://cooker/Obstacle_3.tscn")

func getSmallIconName():
	return "Obstacle_3"