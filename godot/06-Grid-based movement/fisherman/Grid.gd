extends 'res://GridBase.gd'

var map={
	[3,3]:["res://fisherman/Sheep.gd", "Sheep"],
	[9,2]:["res://fisherman/Wolf.gd", "Wolf1"],
	[9,4]:["res://fisherman/Wolf.gd", "Wolf2"],
	[9,6]:["res://fisherman/Wolf.gd", "Wolf3"],
	[11,1]:["res://fisherman/Wolf.gd", "Wolf4"],
	[11,3]:["res://fisherman/Wolf.gd", "Wolf5"],
	[11,5]:["res://fisherman/Wolf.gd", "Wolf6"],
}

func initMap():
	for entry in map:
		spawnObject(entry[0], entry[1], map[entry][0], map[entry][1])
	spawnObject(0, 0, "res://fisherman/FishermanPlayer.gd", "Player")
	return

func is_goal(pos):
	if grid[pos.x][pos.y]==null:
		return false
	if(getObjectByName(pos.x, pos.y, "Sheep")==null):
		return false
	return pos.x==9 and pos.y==0
