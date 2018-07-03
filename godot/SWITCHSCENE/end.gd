extends Panel


func _ready():
	var name=get_node("/root/global").getPlayerName()
	get_node("Label").set_text(str(name,"is the greated in the world"))
	var powerLevel=Globals.get("MAX_POWER_LEVEL")
	get_node("Label 2").set_text(str("Maximum power level is",powerLevel))

