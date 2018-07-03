extends 'res://GridObject.gd'

var pickupedObject=null
var target_direction = Vector2()
var target_pos = Vector2()
const MAX_SPEED = 400
var speed = 0
var velocity = Vector2()
var action=null

func init(name):
	.init(name, "Player", "res://fisherman/Player.tscn")
	
func isKinematicObject():
	return true

func pickup(obj):
	pickupedObject=obj
	getParentGrid().removeObject(obj)

func putdown():
	if(pickupedObject==null):
		return false
	var pos=getGridPos()
	getParentGrid().addObject(pos.x, pos.y, pickupedObject)
	return true

func tick(delta):
	var node=getNode()
	var global=node.get_node("/root/global");
	var grid=getParentGrid()
	var game=grid.get_parent()
	var direction = null
	var grid_pos = getGridPos()
	if global.gameStatus=="idle":
		if(global.index==global.expandedSteps.size()):
			global.gameStatus="end"
			return
		if global.expandedSteps[global.index]=="up": 
			direction=Vector2(0, -1)
		elif global.expandedSteps[global.index]=="down":
			direction=Vector2(0, 1)
		elif global.expandedSteps[global.index]=="left":
			direction=Vector2(-1, 0)
		elif global.expandedSteps[global.index]=="right":
			direction=Vector2(1, 0)
		elif global.expandedSteps[global.index]=="pickup":
			action="pickup"
			global.gameStatus="action"
		elif global.expandedSteps[global.index]=="putdown":
			action="putdown"
			global.gameStatus="action"
		if direction!=null:
			target_direction = direction.normalized()
			if grid.isGridCellVacant(grid_pos.x, grid_pos.y, direction):
				var new_grid_pos = grid_pos + direction
				target_pos = grid.getWorldPos(new_grid_pos.x, new_grid_pos.y)
				global.gameStatus="moving"
			else:
				if grid.is_goal(grid_pos+direction):
					global.gameStatus="success"
				else:
					global.gameStatus="fail"
		global.index=global.index+1
		return
	elif global.gameStatus=="end":
		if grid.is_goal(grid_pos):
			global.gameStatus="success"
		else:
			global.gameStatus="fail"
		return
	elif global.gameStatus=="moving":
		speed = MAX_SPEED
		velocity = speed * target_direction * delta
		var pos = node.get_pos()
		var distance_to_target = pos.distance_to(target_pos)
		var move_distance = velocity.length()
		#以下判斷動作是否執行完了，是的話才執行排在下一個的動作
		if move_distance > distance_to_target:
			velocity = target_direction * distance_to_target
			global.gameStatus="idle"
		node.move(velocity)
		if(global.gameStatus=="idle"):
			#update pos
			grid.removeObject(self)
			var newGridPos=grid.getMapPos(node.get_pos().x, node.get_pos().y)
			grid.addObject(newGridPos.x, newGridPos.y, self)
	elif global.gameStatus=="action":
		if action=="pickup":
			self.pickup(grid.getFirstObject(grid_pos.x, grid_pos.y))
			global.gameStatus="idle"
			return
		elif action=="putdown":
			if(self.putdown()==false):
				global.gameStatus="fail"
				return
			global.gameStatus="idle"
			return
	elif global.gameStatus=="success":
		node.set_fixed_process(false)
		global.running=false
		global.complete="Y"
		game.upload_game_result()
		game.show_success()
	else:#fail	
		global.running=false
		global.complete="N"
		game.upload_game_result()
		game.show_fail()
