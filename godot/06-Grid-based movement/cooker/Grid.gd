extends 'res://GridBase.gd'

var map={
	[10,5]:["res://cooker/Pot.gd", "Pot"],
	[10,1]:["res://cooker/Knife.gd", "Knife"],
	[8,1]:["res://cooker/Onion.gd", "Onion"],
	[8,5]:["res://cooker/Potato.gd", "Potato"],
	[3,3]:["res://cooker/Loop.gd", "Loop"],
	[12,7]:["res://cooker/Guest.gd", "Guest"],
	[7,6]:["res://cooker/One.gd", "One"],
	[6,1]:["res://cooker/Half.gd", "Half"],
}

func initMap():
	for entry in map:
		spawnObject(entry[0], entry[1], map[entry][0], map[entry][1])
	spawnObject(0, 0, "res://cooker/CookerPlayer.gd", "Player")
	return

func is_goal(pos):
	return true

func getGridSize():
	return Vector2(13, 8)