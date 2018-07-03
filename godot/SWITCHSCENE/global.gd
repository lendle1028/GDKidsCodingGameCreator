extends Node
var currentScence=null
var PlayerName="Milk"

func _ready():
	currentScence=get_tree().get_root().get_child(get_tree().get_root().get_child_count()-1)
	Globals.set("MAX_POWER_LEVEL", 9000)

func set_Scence(scence):   
	currentScence.queue_free()
	var s = ResourceLoader.load(scence)
	currentScence=s.instance()
	get_tree().get_root().add_child(currentScence)
func getPlayerName():
	return PlayerName