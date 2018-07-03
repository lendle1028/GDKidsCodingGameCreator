extends Node

func gamepoint(mapid,stuid,gamepoint,datetime,blocknum,complete):
    var err=0
    var http = HTTPClient.new() 
    var err = http.connect("imsofa.rocks",8080) 
    assert(err==OK) # Make sure connection was OK
    while( http.get_status()==HTTPClient.STATUS_CONNECTING or http.get_status()==HTTPClient.STATUS_RESOLVING):
        http.poll()
        print("Connecting..")
        OS.delay_msec(500)

    assert( http.get_status() == HTTPClient.STATUS_CONNECTED ) 

    var headers=[
       "User-Agent: Pirulo/1.0 (Godot)",
        "Accept: */*"
    ]
    var req="mapid="+mapid+"&stuid="+stuid+"&gamepoint="+gamepoint+"&datetime="+datetime+"&blocknum="+blocknum+"&complete="+complete
    err = http.request(HTTPClient.METHOD_GET,"/GameProgramLearningServer/GamePointService?"+req,headers) # Request a page from the site (this one was chunked..)

    assert( err == OK ) # Make sure all is OK

    while (http.get_status() == HTTPClient.STATUS_REQUESTING):
        # Keep polling until the request is going on
        http.poll()
        print("Requesting.."+String(http.get_status()))
        OS.delay_msec(500)

    assert( http.get_status() == HTTPClient.STATUS_BODY or http.get_status() == HTTPClient.STATUS_CONNECTED ) # Make sure request finished well.
    print("response? ",http.has_response()) 

func gamestatus(gameid,mapid,instruction,codeinstruction,time):
    var err=0
    var http = HTTPClient.new() 
    var err = http.connect("imsofa.rocks",8080) 
    assert(err==OK) # Make sure connection was OK
    while( http.get_status()==HTTPClient.STATUS_CONNECTING or http.get_status()==HTTPClient.STATUS_RESOLVING):
        http.poll()
        print("Connecting..")
        OS.delay_msec(500)

    assert( http.get_status() == HTTPClient.STATUS_CONNECTED ) 

    var headers=[
       "User-Agent: Pirulo/1.0 (Godot)",
        "Accept: */*"
    ]
    var req="gameid="+gameid+"&mapid="+mapid+"&instruction="+instruction+"&codeinstruction="+codeinstruction+"&time="+time
    err = http.request(HTTPClient.METHOD_GET,"/GameProgramLearningServer/GameStatusService?"+req,headers) # Request a page from the site (this one was chunked..)

    assert( err == OK ) # Make sure all is OK

    while (http.get_status() == HTTPClient.STATUS_REQUESTING):
        # Keep polling until the request is going on
        http.poll()
        print("Requesting.."+String(http.get_status()))
        OS.delay_msec(500)

    assert( http.get_status() == HTTPClient.STATUS_BODY or http.get_status() == HTTPClient.STATUS_CONNECTED ) # Make sure request finished well.
    print("response? ",http.has_response()) 