$(document).ready(function(){

	var url = "http://localhost:8080/api/v1/mesas";
	var interval = 1000;   //milliseconds to refresh
	var lista_id_pedidos = [];
	var botao = null;
	
    var refresh = function() {

		$.post(url,
			{
				list: lista_id_pedidos.toString()
			},
				function(data, status){
			},
				"json"
		).done(function(data) {
				
			$("#clientTemplate").tmpl(data).appendTo("#todos-pedidos");

			$('.btn').off('click').on('click', function(e){
				
				botao = $(this);
				
				var text_str = 'pago';
				
				if(botao.text() == 'Entregue!') text_str = 'entregue';
				
				var valor_idpedido = $(this).parent().parent().find('#idpedido').val();
				
				$.get( "http://localhost:8080/api/v1/pedidos/"+valor_idpedido+"/"+text_str) 
				.done(function() {
					$('.description').hide();
					  
						var pai = botao.closest('.article');
						pai.fadeOut("fast", function(){
							pai.remove();	
						});
						
						//remove
						var index = lista_id_pedidos.indexOf(Number(valor_idpedido));
						
						if (index >= 0) {
							lista_id_pedidos.splice( index, 1 );
						}
				})
				  
				.fail(function() {
					alert( "error" );
				});
			});
				
			$('.item').off('click').on('click',function(e) {

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
			//$('.item').off('click').on('click',function(e) {
			$('a').off('click').on('click', function(e){
				var temp = $(this).parents();
				temp.removeClass('current');
				temp.children('.description').hide("slow");
				temp.find('.item #btn-container .btn').removeClass('hide');
				temp.find('.item #btn-container .btn').addClass('show');
				temp.find('.item > #btn-container > h2').addClass('hide');
			});

			//$('.item').off('click').on('click',function(e) {
			$('input').off('click').on('click', function(e){
					
				var eu = $(this);
				var ckList = $(this).closest('li');//pega o Li clicado
				var paicheck = ckList.parent();//pega a ul pai
				var primeiroFilho = paicheck.find('li:first-child');//pega o primeiro filho da ul
				var segundoFilho = primeiroFilho.siblings().hasClass('temp'); //checa se tem proximo
				
				var valor_idpedido = $(this).parent().parent().parent().find('#idpedido2').val();

				var checa = ckList.hasClass('checkList');
				if(!checa){
					ckList.addClass('checkList');
				}
				else{
					ckList.removeClass('checkList');
				}

				var checaFilho = primeiroFilho.hasClass("checkList");
				var checaTudo = primeiroFilho.nextAll().hasClass("checkList");

				if((checaTudo == true && checaFilho == true) || (checaFilho == true && segundoFilho == false)){
				 var confirma = confirm("Todos os itens do pedido foram selecionados. Deseja baixar o pedido?");
					if(confirma == true){
						
						$.get( "http://localhost:8080/api/v1/pedidos/"+valor_idpedido+"/"+"entregue")
						.done(function() {
							var pai = eu.closest('.article');
							pai.fadeOut("fast", function(){	
								pai.remove();
							});
								
							//remove
							var index = lista_id_pedidos.indexOf(Number(valor_idpedido));
							
							if (index >= 0) {
								lista_id_pedidos.splice( index, 1 );
							}
						})
						  
						.fail(function() {
							alert( "error" );
						});
					}
				}
			});
		
			//$('.item').off('click').on('click',function(e) {
			$('.btn-danger').off('click').on('click', function(e){
				var corfirmacao = confirm("Tem Certeza que deseja excluir o item?");
				if(corfirmacao == true){
					
					var eu = $(this);
					var div = eu.parent().parent();
					var value = $(this).parent().parent().find('#iditempedido').val();
					var valor_idpedido = $(this).parent().parent().find('#idpedido2').val();

					$.get( "http://localhost:8080/api/v1/pedidos/"+value+"/"+"cancel") 
					.done(function() {
						var lista = eu.parent().parent();
						 
						var botaozin = eu.closest('li');
						botaozin.remove();

						//verifica se é o ultimo item
						if(lista.children("li").length == 0){

							//envia um get para cancelar o pedido no banco
							
							$.get( "http://localhost:8080/api/v1/pedidos/"+valor_idpedido+"/"+"cancelpedido")
							.done(function() {
								
								//removemos esse pedido
								var pai = div.closest('.article');
								pai.fadeOut("fast", function(){
									pai.remove();	
									
									//remove
									var index = lista_id_pedidos.indexOf(Number(valor_idpedido));
									
									if (index >= 0) {
										lista_id_pedidos.splice( index, 1 );
									}
								})
							})
							.fail(function() {
								alert( "error" );
							});
						}
						//atualizo o total
						else{
						  //TODO
						}
					  
					})
					  
					.fail(function() {
						alert( "error" );
					});
					
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
					
			setTimeout(function() {
				
				//data possui os idPedidos novos que foram inseridos nesse novo refresh
				for(var i = 0; i < data.length; i++){
					lista_id_pedidos.push(data[i].idPedido);
				}
				
				refresh();
			}, interval);
			
		});
	}
	
	refresh();

});

	// Largura do logo principal
	// var ew = $(".igBigode").width();
	// var eh = ew/3.56;

	// $(".igBigode").css("height", eh);
	// END

