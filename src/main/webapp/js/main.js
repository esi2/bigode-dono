$(document).ready(function(){

	var mesa =	[
		{
			"numeroMesa":1,
			"pedidos":[
				{
					"id":1,
					"itens":[
						{
							"id":1,
							"nome":"Cerveja",
							"preco":5.0,
							"qtd":1
						}
					]
					,
					"status":"ATIVO"	
				},
				{
					"id":2,
					"itens":[
						{
							"id":2,
							"nome":"Coxinha",
							"preco":3.0,
							"qtd":1
						}
					],
					"status":"ATIVO"
				},
				{
					"id":5,
					"itens":[
						{
							"id":2,
							"nome":"blay",
							"preco":8.0,
							"qtd":1
						}
					],
					"status":"ATIVO"
				},
				{
					"id":3,
					"itens":[
						{
							"id":3,
							"nome":"Cerveja",
							"preco":5.0,
							"qtd":1
						}
					],
					"status":"ENTREGUE"
				},
				{
					"id":4,
					"itens":[
						{
							"id":4,
							"nome":"Coxinha",
							"preco":3.0,
							"qtd":1
						}
					],
					"status":"PAGO"
				}
			]
		}
	];

	$("#clientTemplate").tmpl(mesa).appendTo("#todos-pedidos");

/*	function httpGetAsync(theUrl, callback)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}*/

	// Com Ajax

	// $.ajax({
	// 	dataType: "jsonp",
	// 	url: "http://143.107.58.177:8080/bigode-dono/api/v1/mesas",
	// 	jsonp: "$callback",
	// 	success: showMovies
	// });

	// function showMovies(data) {
	// 	$("#clientTemplate").tmpl(data).appendTo("#todos-pedidos");
	// }

	// (function() {
	// 	var url = "http://143.107.58.177:8080/bigode-dono/api/v1/mesas";
	// 	$.getJSON( url, {
	// 		format: "json"
	// 	}).done(function(data) {
	// 			$("#clientTemplate").tmpl(data).appendTo("#todos-pedidos");
	// 		});
	// })();

	$('.article').click(function(e) {

		$('.article').not(this).find('.item #btn-container h2').addClass('hide');
		$('.article').not(this).find('.item #btn-container .btn-entregue').addClass('show');
		$('.article').not(this).find('.item #btn-container .btn-pago').addClass('show');
		$('.article').not(this).find('.item #btn-container .btn-ativo').addClass('show');

		var current_active = $(this).hasClass('current');
		var current_total = $(this).find('.item > #btn-container > h2').hasClass('hide');

		if(!current_total) {
			$(this).find('.item > #btn-container > h2').addClass('hide');
		} else {
			$(this).find('.item > #btn-container > h2').removeClass('hide');
		}

		var current_btn_e = $(this).find('.item #btn-container .btn-entregue').hasClass('show');
		var current_btn_p = $(this).find('.item #btn-container .btn-pago').hasClass('show');
		var current_btn_a = $(this).find('.item #btn-container .btn-ativo').hasClass('show');

		if(!current_btn_e) {
			$(this).find('.item > #btn-container > .btn-entregue').removeClass('hide');
			$(this).find('.item > #btn-container > .btn-entregue').addClass('show');
		} else {
			$(this).find('.item > #btn-container > .btn-entregue').removeClass('show');
			$(this).find('.item > #btn-container > .btn-entregue').addClass('hide');
		}

		if(!current_btn_p) {
			$(this).find('.item > #btn-container > .btn-pago').removeClass('hide');
			$(this).find('.item > #btn-container > .btn-pago').addClass('show');
		} else {
			$(this).find('.item > #btn-container > .btn-pago').removeClass('show');
			$(this).find('.item > #btn-container > .btn-pago').addClass('hide');
		}

		if(!current_btn_a) {
			$(this).find('.item > #btn-container > .btn-ativo').removeClass('hide');
			$(this).find('.item > #btn-container > .btn-ativo').addClass('show');
		} else {
			$(this).find('.item > #btn-container > .btn-ativo').removeClass('show');
			$(this).find('.item > #btn-container > .btn-ativo').addClass('hide');
		}

		if(!current_active) {
			$('.article').removeClass('current');
			$('.description').hide();
			$(this).addClass('current');
			$(this).children('.description').show("slow");
		}
		else {
			$(this).removeClass('current');
			$(this).children('.description').hide("slow");
		}
	});

	$('.btn').click(function(){
			$('.description').hide();
			var pai = $(this).closest('.article');
				pai.fadeOut("fast", function(){
					pai.remove();
				})
	});

	$(document).keypress(function(event) {
		if(event.which === 32) {

			$('.description').hide("slow");
		}

		else if(event.which === 115) {
			var currentArticle = $('.current');
			var nextArticle = currentArticle.next();

			if(nextArticle.length === 0) {
				nextArticle = $('.article').first();
			}

			currentArticle.removeClass('current');
			nextArticle.addClass('current');
			$('.description').hide("slow");
			nextArticle.children('.description').show("slow");

		}

		else if(event.which === 119) {
			var currentArticle = $('.current');
			var prevArticle = currentArticle.prev();

			if(prevArticle.length === 0) {
				prevArticle = $('.article').last();
			}

			currentArticle.removeClass('current');
			prevArticle.addClass('current');
			$('.description').hide("slow");
			prevArticle.children('.description').show("slow");
		}
	});

	// Largura do logo principal
	// var ew = $(".igBigode").width();
	// var eh = ew/3.56;

	// $(".igBigode").css("height", eh);
	// END

});