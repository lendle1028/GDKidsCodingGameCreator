# Collection of functions to work with a Grid. Stores all its children in the grid array
extends TileMap

enum ENTITY_TYPES {PLAYER, OBSTACLE, COLLECTIBLE}

var tile_size = Vector2(65,65)#get_cell_size()
var half_tile_size = tile_size / 2
var grid_size = Vector2(13,7)#Vector2(16, 16)

var o
var grid = []
var grid_inst=[]

var node2ObjectMap={}

onready var Player = preload("res://fisherman/Player.tscn")

func initMap():
	return

#override this to change tile size, which defaults to 65*65
func getTileSize():
	return Vector2(65, 65)

#override this to change grid size, which defaults to 13*7
func getGridSize():
	return Vector2(13, 7)
	
func spawnObject(gridX, gridY, gdPath, name):
	var object=load(gdPath).new()
	object.init(name)
	node2ObjectMap[object.getNode()]=object
	addObject(gridX, gridY, object)
	return object

func getObjectFromNode(node):
	return node2ObjectMap[node]

func addObject(gridX, gridY, gridObject):
	if(grid[gridX][gridY]==null):
		grid[gridX][gridY]=[]
	var holder=grid[gridX][gridY]
	gridObject.setParentGrid(self)
	holder.append(gridObject)
	gridObject.setPos(gridX, gridY)
	var node=gridObject.getNode()
	add_child(node)

func removeObject(gridObject):
	var gridPos=gridObject.getGridPos()
	var holder=grid[gridPos.x][gridPos.y]
	holder.erase(gridObject)
	remove_child(gridObject.getNode())

func removeObjects(gridX, gridY):
	var holder=grid[gridX][gridY]
	for o in holder:
		remove_child(o.getNode())
	holder.clear()

func getObjects(gridX, gridY):
	return grid[gridX][gridY]

func getObjectByName(gridX, gridY, name):
	var holder=grid[gridX][gridY]
	for o in holder:
		if o.getName()==name:
			return o
	return null

func getFirstObject(gridX, gridY):
	print(grid)
	var holder=grid[gridX][gridY]
	if holder.size()>0:
		return holder[0]
	return null

func _ready():
	tile_size=self.getTileSize()
	grid_size=self.getGridSize()
	half_tile_size=tile_size/2

	for x in range(grid_size.x):
		grid.append([])
		grid_inst.append([])
		for y in range(grid_size.y):
			grid[x].append(null)
			grid_inst[x].append(null)
	for x in range(grid_size.x):
		for y in range(grid_size.y):
			grid[x][y]=[]
	
	initMap()

func get_cell_content(pos=Vector2()):
	return grid[pos.x][pos.y]

func isGridCellVacant(gridX, gridY, direction=Vector2()):
	var grid_pos = Vector2(gridX, gridY) + direction
	if grid_pos.x < grid_size.x and grid_pos.x >= 0:
		if grid_pos.y < grid_size.y and grid_pos.y >= 0:
			if(grid[grid_pos.x][grid_pos.y]==null or
				grid[grid_pos.x][grid_pos.y].empty()):
				return true
			var holder=grid[grid_pos.x][grid_pos.y]
			for o in holder:
				if(o.isBlocking()):
					return false
			return true
	return false

func is_cell_vacant(pos=Vector2(), direction=Vector2()):
	var grid_pos = world_to_map(pos) + direction
	if grid_pos.x < grid_size.x and grid_pos.x >= 0:
		if grid_pos.y < grid_size.y and grid_pos.y >= 0:
			if(grid[grid_pos.x][grid_pos.y]==null or
				grid[grid_pos.x][grid_pos.y].empty()):
				return true
			var holder=grid[grid_pos.x][grid_pos.y]
			for o in holder:
				if(o.isBlocking()):
					return false
			return true
	return false


func getWorldPos(gridX, gridY):
	var gridPos=Vector2(gridX, gridY)
	return map_to_world(gridPos) + half_tile_size
	
func getMapPos(worldX, worldY):
	return world_to_map(Vector2(worldX, worldY))

func moveObject(gridObject, newX, newY):
	removeObject(gridObject)
	addObject(newX, newY, gridObject)

func is_goal(pos):
	return true



