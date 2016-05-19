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
					],

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

	$('.item').click(function(e) {

		var artcur = $(this).parent();
		$('.article').not(artcur).find('.item #btn-container h2').addClass('hide');
		$('.article').not(artcur).find('.item #btn-container .btn-entregue').addClass('show');
		$('.article').not(artcur).find('.item #btn-container .btn-pago').addClass('show');
		$('.article').not(artcur).find('.item #btn-container .btn-ativo').addClass('show');
		//var botaoCima = artcur.find('.item #btn-container .btn');
		//botaoCima.removeClass('show');
		//botaoCima.addClass('hide');
		//var totalzin = artcur.find('.item > #btn-container > h2');
		//totalzin.removeClass('hide');

		var current_active = artcur.hasClass('current');
		var current_total = artcur.find('.item > #btn-container > h2').hasClass('hide');


		if(!current_total) {
			artcur.find('.item > #btn-container > h2').addClass('hide');
		} else {
			artcur.find('.item > #btn-container > h2').removeClass('hide');
		}

		var current_btn_e = artcur.find('.item #btn-container .btn-entregue').hasClass('show');//valor
		var current_btn_p = artcur.find('.item #btn-container .btn-pago').hasClass('show');
		var current_btn_a = artcur.find('.item #btn-container .btn-ativo').hasClass('show');

		if(!current_btn_e) {
			artcur.find('.item > #btn-container > .btn-entregue').removeClass('hide');
			artcur.find('.item > #btn-container > .btn-entregue').addClass('show');
		} 
		else {
			artcur.find('.item > #btn-container > .btn-entregue').removeClass('show');
			artcur.find('.item > #btn-container > .btn-entregue').addClass('hide');
		}

		if(!current_btn_p) {
			artcur.find('.item > #btn-container > .btn-pago').removeClass('hide');
			artcur.find('.item > #btn-container > .btn-pago').addClass('show');
		} 
		else {
			artcur.find('.item > #btn-container > .btn-pago').removeClass('show');
			artcur.find('.item > #btn-container > .btn-pago').addClass('hide');
		}

		if(!current_btn_a) {
			artcur.find('.item > #btn-container > .btn-ativo').removeClass('hide');
			artcur.find('.item > #btn-container > .btn-ativo').addClass('show');
		} 
		else {
			artcur.find('.item > #btn-container > .btn-ativo').removeClass('show');
			artcur.find('.item > #btn-container > .btn-ativo').addClass('hide');
		}

		if(!current_active) {
			$('.article').removeClass('current');
			$('.description').hide();
			artcur.addClass('current');
			artcur.children('.description').show("slow");
		}

		else {
			artcur.removeClass('current');
			artcur.children('.description').hide("slow");
		}
	
	});

	$('a').click(function(){
		var temp = $(this).parents();
		temp.removeClass('current');
		temp.children('.description').hide("slow");
		temp.find('.item #btn-container .btn').removeClass('hide');
		temp.find('.item #btn-container .btn').addClass('show');
		temp.find('.item > #btn-container > h2').addClass('hide');
	});

	$('input').click(function(){
		var ckList = $(this).closest('li');
		var checa = ckList.hasClass('checkList');
		if(!checa){
			ckList.addClass('checkList');
		}
		else{
			ckList.removeClass('checkList');
		}
	});


	$('.btn').click(function(){
			$('.description').hide();
			var pai = $(this).closest('.article');
				pai.fadeOut("fast", function(){
					pai.remove();
				})
	});

	$('.btn-danger').click(function(){
		var botaozin = $(this).closest('li');
		botaozin.remove();
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