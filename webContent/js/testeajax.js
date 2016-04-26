function loadassets() {
	$.ajax({
	  script: "script.php",
	  accepts: {
	    mycustomtype: 'application/x-some-custom-type'
	  },
	 
	  // Instructions for how to deserialize a `mycustomtype`
	  converters: {
	    'text mycustomtype': function(result) {
	      // Do Stuff
	      return newresult;
	    }
	  },
	 
	  // Expect a `mycustomtype` back from server
	  dataType: 'jason'
	});

	if(data != null) {
		loadorders();
	}
}

function loadorders() {
	for(var i =0;i<dataorder.length(); i++) {
		<div class="article">
        <div class="item row">
          <div class="col-xs-3">
            <p class="source">peido1.mesa</p>
          </div>
          <div class="col-xs-6">
            <p class="title">Embraer adds third Legacy 500 prototype to flight test campaign</p>
          </div>
          <div class="col-xs-3">
            <p class="pubdate">Mar 23</p>
          </div>
        </div>
        <div class="description row">
          <div class="col-xs-3">&nbsp;</div>
          <div class="col-xs-6">
            <h1>Embraer adds third Legacy 500 prototype to flight test campaign</h1>
            <p>The third Legacy 500 has joined Embraer's flight test programme aimed at delivering the midsize business jet in 2014. The airtcraft, serial number 003...</p>
          </div>
          <div class="col-xs-3">&nbsp;</div>
        </div>
      </div>
	}
}

{
	pedido1: {
		mesa: 1,
		bebidas: [],
		comidas: []
	},
	pedido2: {
		mesa: 1,
		bebidas: [],
		comidas: []
	},
	pedido3: {
		mesa: 1,
		bebidas: [],
		comidas: []
	},
}