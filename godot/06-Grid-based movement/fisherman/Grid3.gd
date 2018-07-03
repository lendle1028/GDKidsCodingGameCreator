# Collection of functions to work with a Grid. Stores all its children in the grid array
extends TileMap

enum ENTITY_TYPES {PLAYER, OBSTACLE, COLLECTIBLE}

var tile_size = Vector2(65,65)#get_cell_size()
var half_tile_size = tile_size / 2
var grid_size = Vector2(13,7)#Vector2(16, 16)

var o
var grid = []
var grid_inst=[]
onready var Obstacle_1 = preload("res://fisherman/sheep.tscn")
onready var Obstacle_2 = preload("res://fisherman/piranha.tscn")
onready var Player = preload("res://fisherman/Player.tscn")


#define the map
onready var map={
	[0,3]:"Obstacle_1",  
	[2,0]:"Obstacle_2",
	[2,1]:"Obstacle_2",
	[2,2]:"Obstacle_2", 
	[2,3]:"Obstacle_2", 
	[4,4]:"Obstacle_2", 
	[4,5]:"Obstacle_2",
	[4,6]:"Obstacle_2",
	[6,0]:"Obstacle_2",
	[6,1]:"Obstacle_2",
	[6,2]:"Obstacle_2", 
	[6,3]:"Obstacle_2", 
	[8,4]:"Obstacle_2", 
	[8,5]:"Obstacle_2",
	[8,6]:"Obstacle_2",
	[10,0]:"Obstacle_2",
	[10,1]:"Obstacle_2",
	[10,2]:"Obstacle_2", 
	[10,3]:"Obstacle_2"
}

func _ready():
	var global=get_node("/root/global");
	global.point = "fisherman_4"
	for x in range(grid_size.x):
		grid.append([])
		grid_inst.append([])
		for y in range(grid_size.y):
			grid[x].append(null)
			grid_inst[x].append(null)

	# Player
	var new_player = Player.instance()
	new_player.set_pos(map_to_world(Vector2(0,0)) + half_tile_size)
	add_child(new_player)

	# Obstacles
	var positions = []

	for pos in positions:
		var new_obstacle = Obstacle_1.instance()
		new_obstacle.set_pos(map_to_world(pos) + half_tile_size)
		grid[pos.x][pos.y] = new_obstacle.get_name()
		add_child(new_obstacle)

	#process map
	for entry in map:
		var pos=Vector2(entry[0], entry[1])
		positions.append(pos)
		var new_obstacle=null
		if(map[entry]=="Obstacle_1"):
			new_obstacle=Obstacle_1.instance()
		elif(map[entry]=="Obstacle_2"):
			new_obstacle=Obstacle_2.instance()
		new_obstacle.set_pos(map_to_world(pos) + half_tile_size)
		grid[pos.x][pos.y] = new_obstacle.get_name()
		grid_inst[pos.x][pos.y]=new_obstacle
		add_child(new_obstacle)

func get_cell_content(pos=Vector2()):
	return grid[pos.x][pos.y]


func is_cell_vacant(pos=Vector2(), direction=Vector2()):
	var grid_pos = world_to_map(pos) + direction
	if grid_pos.x < grid_size.x and grid_pos.x >= 0:
		if grid_pos.y < grid_size.y and grid_pos.y >= 0:
			if  grid[grid_pos.x][grid_pos.y] != null:
				if grid[grid_pos.x][grid_pos.y]=="piranha":
					 return false
			return true if grid[grid_pos.x][grid_pos.y] == null else true
	return false


func update_child_pos(new_pos, direction, type):
	# Remove node from current cell, add it to the new cell, returns the new target move_to position
	var grid_pos = world_to_map(new_pos)
	grid[grid_pos.x][grid_pos.y] = null
	var new_grid_pos = grid_pos + direction
	grid[new_grid_pos.x][new_grid_pos.y] = type
	var target_pos = map_to_world(new_grid_pos) + half_tile_size
		
	return target_pos
	

func is_goal(pos):
	if grid[pos.x][pos.y]==null:
		return false
	if String(grid[pos.x][pos.y])=="0":
		return false
	return pos.x==11 and pos.y==3
	

