extends 'res://GridObject.gd'

func init(name):
	.init(name, "One", "res://cooker/number.tscn")

func getSmallIconName():
	return "number"