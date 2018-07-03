extends 'res://GridBase.gd'

var map={
	[3,3]:["res://fisherman/Sheep.gd", "Sheep"],
	[3,1]:["res://fisherman/Wolf.gd", "Wolf"],
	[3,5]:["res://fisherman/Grass.gd", "Grass"]
}

func initMap():
	for entry in map:
		spawnObject(entry[0], entry[1], map[entry][0], map[entry][1])
	spawnObject(0, 0, "res://fisherman/FishermanPlayer.gd", "Player")
	return

func is_goal(pos):
	if(getObjectByName(10, 1, "Wolf")==null):
		return false
	if(getObjectByName(10, 3, "Sheep")==null):
		return false
	if(getObjectByName(10, 5, "Grass")==null):
		return false
	return true
