extends 'res://GridObject.gd'
func init(name):
	.init(name, "Pot", "res://cooker/Obstacle.tscn")
	__init__()func isKinematicObject():
	return false
func __init__():
	return