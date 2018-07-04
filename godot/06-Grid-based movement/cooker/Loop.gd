extends 'res://GridObject.gd'
func getSmallIconName():
	return "Obstacle_2"
func init(name):
	.init(name, "Loop", "res://cooker/Obstacle_2.tscn")
	__init__()func isKinematicObject():
	return false
func __init__():
	return