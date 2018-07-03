extends Node

onready var Obstacle = preload("res://cooker/Obstacle.tscn")
onready var Obstacle_1 = preload("res://cooker/Obstacle_1.tscn")
onready var Obstacle_2 = preload("res://cooker/Obstacle_2.tscn")
onready var Obstacle_3 = preload("res://cooker/Obstacle_3.tscn")
onready var Obstacle_4 = preload("res://cooker/Obstacle_4.tscn")
onready var guest = preload("res://cooker/guest.tscn")
onready var number = preload("res://cooker/number.tscn")
onready var half = preload("res://cooker/half.tscn")
onready var expandingIndex=0

var count=0
var i=0

func _ready():
	set_process_input(true)
	set_pause_mode(PAUSE_MODE_PROCESS)
	var up=get_node("palette/up")
	up.connect("pressed", self, "test", [up, "up"])  #[物件, 動作值]
	var down=get_node("palette/down")
	down.connect("pressed", self, "test", [down, "down"])
	var left=get_node("palette/left")
	left.connect("pressed", self, "test", [left, "left"])
	var right=get_node("palette/right")
	right.connect("pressed", self, "test", [right, "right"])
	var start=get_node("palette/start");
	start.connect("pressed", self, "startRunning");

func startRunning():
	var global=get_node("/root/global");
	global.gameStatus="idle"
	var state="normal"
	var reusableBlock=[]

	for i in range(expandingIndex, global.steps.size()):
		var step=global.steps[i]
		if state=="normal":
			if step=="reuse":
				if reusableBlock.size()>0:
					for s in reusableBlock:
						global.expandedSteps.append(s)
			elif step=="block_start":
				state="in_block"
				reusableBlock=[]
			else:
				global.expandedSteps.append(step)
		elif state=="in_block":
			if step=="block_end":
				state="normal"
			else:
				reusableBlock.append(step)
	expandingIndex=global.steps.size()
	global.running=true;
	
func test(object, action): #[物件, 動作值]
	var commands=get_node("commands")
	var o=object.duplicate()
	o.set_pos(Vector2(count*55+25, 10))
	count=count+1
	commands.add_child(o)
	#宣告一個全域陣列(or project setting->Autoload)
	var global=get_node("/root/global");  
	global.steps.append(action);#把陣列的值掛上去
	print(global.steps)
	
func _input(event):
	if event.is_action_pressed("pause"):
		if get_tree().is_paused():
			get_tree().set_pause(false)
		else:
			get_tree().set_pause(true)

func show_fail():
	get_node("Grid/fail").show()
	var grid=get_node("Grid")
	var timer = Timer.new()
	timer.set_one_shot(true)
	timer.set_timer_process_mode(0)
	timer.set_wait_time(3)
	timer.connect("timeout", self, "reset")
	grid.add_child(timer)
	timer.start()

func show_success():
	get_node("Grid/success").show()
	var grid=get_node("Grid")
	var timer = Timer.new()
	timer.set_one_shot(true)
	timer.set_timer_process_mode(0)
	timer.set_wait_time(3)
	timer.connect("timeout", self, "reset")
	grid.add_child(timer)
	timer.start()
