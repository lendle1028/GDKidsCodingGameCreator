extends Node

onready var Obstacle = preload("res://maze/Obstacle.tscn")
var count=0
var timer
func _ready():
	var global=get_node("/root/global");
	var u=preload("res://uuid.gd")
	global.mapid=String(u.v4())
	global.list=[]
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
	var block_start=get_node("palette/block_start")
	block_start.connect("pressed", self, "test", [block_start, "block_start"])
	var block_end=get_node("palette/block_end")
	block_end.connect("pressed", self, "test", [block_end, "block_end"])
	var reuse=get_node("palette/reuse")
	reuse.connect("pressed", self, "test", [reuse, "reuse"])
	var start=get_node("palette/start");
	start.connect("pressed", self, "startRunning");
	var start=get_node("palette/back");
	start.connect("pressed", global, "back2SceneSwitcher");

func startRunning():
	var global=get_node("/root/global");
	global.gameStatus="normal"
	#convert user steps into expanded steps(blocks are flattened)
	var state="normal" #normal/in_block
	var reusableBlock=[]
	for step in global.steps:
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
	print(global.expandedSteps)
	global.running=true;
	global.complete="Y"
	
func test(object, action): #[物件, 動作值]
	var commands=get_node("commands")
	var commands1=get_node("commands1")
	#var o=Button.new()
	var o=null
	if action=="up":
		#o.text="U"
		o=get_node("command_up").duplicate()
	elif action=="down":
		#o.text="D"
		o=get_node("command_down").duplicate()
	elif action=="left":
		#o.text="L"
		o=get_node("command_left").duplicate()
	elif action=="right":
		#o.text="R"
		o=get_node("command_right").duplicate()
	elif action=="block_start":
		#o.text=">"
		o=get_node("command_block_start").duplicate()
	elif action=="block_end":
		#o.text="<"
		o=get_node("command_block_end").duplicate()
	elif action=="reuse":
		#o.text="G"
		o=get_node("command_reuse").duplicate()

	print(o.get_minimum_size())
	o.set_pos(Vector2(count*55+25, 10))
	count=count+1
	if global.steps.size()<20:
		commands.add_child(o)
	else:
		commands1.add_child(o)
	#宣告一個全域陣列(or project setting->Autoload)
	var global=get_node("/root/global");
	var u=preload("res://uuid.gd")  
	global.steps.append(action);#把陣列的值掛上去
	global.list.append([String(u.v4()),global.mapid,"add",action,String(OS.get_unix_time()),String(count)])
	o.connect("pressed", self, "deleteCommandFrom", [o, global.steps.size()-1]);

func deleteCommandFrom(o, index): 
	var commands=get_node("commands")
	var commands1=get_node("commands1")
	var parentNode=o.get_parent()
	var currentIndex=0
	for object in commands.get_children():
		if currentIndex>=index:
			commands.remove_child(object)
		currentIndex=currentIndex+1
	for object in commands1.get_children():
		if currentIndex>=index:
			commands1.remove_child(object)
		currentIndex=currentIndex+1
	currentIndex=global.steps.size()-1
	while currentIndex>=index:
		global.steps.pop_back()
		currentIndex=currentIndex-1
	print(global.steps)

func _input(event):
	if event.is_action_pressed("pause"):
		if get_tree().is_paused():
			get_tree().set_pause(false)
		else:
			get_tree().set_pause(true)
			
func show_fail():
	print("show fail")
	get_node("Grid/fail").show()
	var grid=get_node("Grid")
	timer = Timer.new()
	timer.set_one_shot(true)
	timer.set_timer_process_mode(0)
	timer.set_wait_time(3)
	timer.connect("timeout", self, "reset")
	grid.add_child(timer)
	timer.start()

func show_success():
	get_node("Grid/success").show()
	var grid=get_node("Grid")
	timer = Timer.new()
	timer.set_one_shot(true)
	timer.set_timer_process_mode(0)
	timer.set_wait_time(3)
	timer.connect("timeout", self, "reset")
	grid.add_child(timer)
	timer.start()

func upload_game_result():
	var global=get_node("/root/global");
	global.point=global.currentGame+"_"+str(global.currentLevel+1)
	var global=get_node("/root/global");
	var s={"value":global.list}.to_json()
	global.gamepoint(global.mapid,global.studentid,global.point,String(OS.get_unix_time()),String(global.steps.size()),global.complete)
	global.gamestatus(s);
	print("finished upload")

func reset():
	print("reset")
	var global=get_node("/root/global");
	var grid=get_node("Grid")
	global.reset()
	grid.reset()

func _on_button_readme_pressed():
	get_node("popup_readme").popup()
	pass # replace with function body
