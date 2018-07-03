extends 'res://GridObject.gd'

func init(name):
	.init(name, "Half", "res://cooker/half.tscn")

func getSmallIconName():
	return "half"