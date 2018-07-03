#objects shown on the grid
var node
var name
var type
var parentGrid
var gridX
var gridY

func init(_name, _type, _tscnPath):
	name=_name
	type=_type
	node=load(_tscnPath).instance()
	return

func getType():
	return type

func getNode():
	return node

func getName():
	return name

func getParentGrid():
	return parentGrid

func setParentGrid(grid):
	parentGrid=grid
#will this object block player or not
func isBlocking():
	return false

func setPos(_gridX, _gridY):
	gridX=_gridX
	gridY=_gridY
	node.set_pos(parentGrid.map_to_world(Vector2(gridX, gridY)) + parentGrid.half_tile_size)

func getGridPos():
	return Vector2(gridX, gridY)

func getWorldPos():
	return parentGrid.map_to_world(Vector2(gridX, gridY)) + parentGrid.half_tile_size

func isKinematicObject():
	return false