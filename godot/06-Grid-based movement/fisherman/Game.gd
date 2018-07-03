extends Node

onready var Obstacle_1 = preload("res://fisherman/sheep.tscn")
onready var Obstacle_2 = preload("res://fisherman/wolf.tscn")
onready var Obstacle_3 = preload("res://fisherman/grass.tscn")


var count=0


func _ready():
	var u=preload("res://uuid.gd")
	global.mapid=String(u.v4())
	global.list=[]
	set_process_input(true)
	set_pause_mode(PAUSE_MODE_PROCESS)
	var up=get_node("palette/up")
	up.connect("pressed", self, "test", [get_node("up_command"), "up"])  #[物件, 動作值]
	var down=get_node("palette/down")
	down.connect("pressed", self, "test", [get_node("down_command"), "down"])
	var left=get_node("palette/left")
	left.connect("pressed", self, "test", [get_node("left_command"), "left"])
	var right=get_node("palette/right")
	right.connect("pressed", self, "test", [get_node("right_command"), "right"])
	var block_start=get_node("palette/block_start")
	block_start.connect("pressed", self, "test", [get_node("le_command"), "block_start"])
	var block_end=get_node("palette/block_end")
	block_end.connect("pressed", self, "test", [get_node("ri_command"), "block_end"])
	var reuse=get_node("palette/reuse")
	reuse.connect("pressed", self, "test", [get_node("for_command"), "reuse"])
	var put=get_node("palette/put")
	put.connect("pressed", self, "test", [get_node("put_command"), "putdown"])
	var pick=get_node("palette/pick")
	pick.connect("pressed", self, "test", [get_node("pick_command"), "pickup"])
	var start=get_node("palette/start");
	start.connect("pressed", self, "startRunning");
	var back=get_node("palette/back");
	back.connect("pressed", global, "back2SceneSwitcher");

func startRunning():
	var global=get_node("/root/global");
	global.gameStatus="idle"
	var state="normal"
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
	global.running=true;
	
func test(object, action): #[物件, 動作值]
	var commands=get_node("commands")
	var command1=get_node("command1")
	var o=object.duplicate()
	o.set_pos(Vector2(count*55+25, 10))
	count=count+1
	if global.steps.size() < 13:
		commands.add_child(o)
	else :
		command1.add_child(o)
	var global=get_node("/root/global");
	var u=preload("res://uuid.gd")  
	global.steps.append(action);#把陣列的值掛上去
	global.list.append([String(u.v4()),global.mapid,"add",action,String(OS.get_unix_time()),String(count)])
	o.connect("pressed", self, "deleteCommandFrom", [o, global.steps.size()-1]);
	print(global.steps)

func deleteCommandFrom(o, index): 
	var commands=get_node("commands")
	var commands1=get_node("command1")
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
	get_tree().change_scene("res://fisherman/fail.tscn")
	
func show_success():
	get_tree().change_scene("res://fisherman/success.tscn")

func upload_game_result():
	
	var global=get_node("/root/global");
	var s={"value":global.list}.to_json()
	global.gamepoint(global.mapid,global.studentid,global.point,String(OS.get_unix_time()),String(global.steps.size()),global.complete)
	global.gamestatus(s);
	print(s)
	print("finished upload")

func _on_button_readme_pressed():
	get_node("Popup_read").popup()
	pass # replace with function body
	
func _on_read_2_pressed():
	get_node("Popup_read2").popup()
	pass # replace with function body
