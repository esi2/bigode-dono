
 /*(function() {
	 	var url = "http://143.107.58.177:8080/bigode-dono/api/v1/mesas";
	 	$.getJSON( url, {
	 		format: "json"
	 	}).done(function(data) {
	 		
				$("#clientTemplate").tmpl(data).appendTo("#todos-pedidos");
		});
	})();

(function() {
	 	var url = "http://143.107.58.177:8080/bigode-dono/api/v1/mesas/pagamento";
	 	$.getJSON( url, {
	 		format: "json"
	 	}).done(function(data) {
	 			
				$("#clientTemplate").tmpl(data).appendTo("#todos-pedidos");
		});
	})();*/

$(document).ready(function(){


		var url = "http://143.107.58.177:8080/bigode-dono/api/v1/mesas";
		var url2 ="http://143.107.58.177:8080/bigode-dono/api/v1/mesas/pagamento";
	 	
	 	$.getJSON( url, {
	 		format: "json"
	 	}).done(function(data) {
				$("#clientTemplate").tmpl(data).appendTo("#todos-pedidos");
		});

		$.getJSON( url2, {
	 		format: "json"
	 	}).done(function(data) {		
				$("#clientTemplate").tmpl(data).appendTo("#todos-pedidos");
		});

		

	$( window ).load(function() {		
	 	$('.item').click(function(e){

		var artcur = $(this).parent();
		$('.article').not(artcur).find('.item #btn-container h2').addClass('hide');
		$('.article').not(artcur).find('.item #btn-container .btn-entregue').addClass('show');
		$('.article').not(artcur).find('.item #btn-container .btn-pago').addClass('show');
		$('.article').not(artcur).find('.item #btn-container .btn-ativo').addClass('show');


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
			$('ul').removeClass('ativado');
			$('.description').hide();
			artcur.addClass('current');
			artcur.children('.description').show("slow");
		}

		else {
			artcur.removeClass('current');
			$('ul').removeClass('ativado');
			artcur.children('.description').hide("slow");
		}
		console.log(artcur.find('.preco').val());
	
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
			
		var ckList = $(this).closest('li');//pega o Li clicado
		var paicheck = ckList.parent();//pega a ul pai 
		paicheck.addClass('ativado');
		var tamanhoLista = $('.ativado li').length;
		
		
		//console.log("L"+tamanhoLista);
		var primeiroFilho = paicheck.find('li:first-child');//pega o primeiro filho da ul
		var segundoFilho = primeiroFilho.siblings().hasClass('temp'); //checa se tem proximo

		var checa = ckList.hasClass('checkList');
		if(!checa){
			ckList.addClass('checkList');

		}
		else{
			ckList.removeClass('checkList');
		}

		var tikados = $('.ativado .checkList').length;
		//console.log(tikados);

		if(tamanhoLista == tikados){
			 var confirma = confirm("Todos os itens do pedido foram selecionados. Deseja baixar o pedido?");
		 	 if(confirma == true){
		  		var pai = $(this).closest('.article');
		  		pai.fadeOut("fast", function(){
			 		pai.remove();
			 	});
		  	}
		}
	
	});


	$('.btn').on('click', function(e){
				
				botao = $(this);
				var sessaoPedido = botao.find('strong').attr('class');	
				var text_str = 'pago';
				if(botao.text() == 'Entregue!'){
				   text_str = 'entregue';
				} 
				console.log("http://143.107.58.177:8080/bigode-dono/api/v1/pedidos/"+sessaoPedido+"/"+text_str);	
			//	var valor_idpedido = $(this).parent().parent().find('#idpedido').val();
				
				$.get( "http://143.107.58.177:8080/bigode-dono/api/v1/pedidos/"+sessaoPedido+"/"+text_str)
				.done(function() {
					$('.description').hide();
					  
						var pai = botao.closest('.article');
						pai.fadeOut("fast", function(){
							pai.remove();	
						});
						
						//remove
					//	var index = lista_id_pedidos.indexOf(Number(valor_idpedido));
						
					//	if (index >= 0) {
					//		lista_id_pedidos.splice( index, 1 );
					//	}
				})
				  
				.fail(function() {
					alert( "error" );
				});
	});

	$('.btn-danger').click(function(){
		var corfirmacao = confirm("Tem Certeza que deseja excluir o item?");
		if(corfirmacao == true){
		var botaozin = $(this).closest('li');
		botaozin.remove();
		}
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
  });
}); 		 


	// Largura do logo principal
	// var ew = $(".igBigode").width();
	// var eh = ew/3.56;

	// $(".igBigode").css("height", eh);
	// END

