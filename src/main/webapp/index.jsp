<!doctype html>
<html>
	<head>

		<meta charset="utf-8">
		<link href="./css/bootstrap.min.css" rel="stylesheet" media="all">
		<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="./css/style.css">

	 <!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
		<script src="./js/jquery.js"></script>
		<script src="./js/jquery.tmpl.min.js"></script>
		<script src="./js/main.js"></script>

		<script id="clientTemplate" type="text/html">

					{{if status == "ENTREGUE"}}
						<div class="article entregue-master-class">
							<div class="item row">
								<div class="col-xs-9">
									<p class="source"><strong>A mesa {{= idMesa}} teve o pedido entregue!</strong></p>
								</div>
								<div class="col-xs-3" id="btn-container">
									<button class="btn btn-entregue show"><strong>Pago!</strong></button>
									<h2 class="hide">Total: <span class="preco fonte2">{{= total}}</span></h2>
								</div>
							</div>
							<div class="description row">
								<div class="col-xs-12 container-itens">
									<ul>
									{{each itens}}
										<li class="temp">
										<input class="cklist" type="checkbox" name="demo" value="one" /> {{= nome}}
											<span class="not-bold texto-dir"> {{= qtd}} x 
												<span class="preco">R$ {{= preco}}</span>
											</span>
  										<button type="button" class="btn-danger btn-xs" aria-label="Right Align">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
										</li>
										<hr>
									{{/each}}
									</ul>
								</div>

								<div class="row">
									<div class="col-xs-3 menos-padd">
										<button class="btn btn-entregue"><strong>Pago!</strong></button>
									</div>
									<div class="col-xs-9 menos-padd">
										<a class="minimiza" href="#"><strong>Minimizar o pedido</strong></a>
									</div>
								</div>

							</div>
						</div>
					{{else status == "PAGO"}}
						<div class="article pago-master-class">
							<div class="item row">
								<div class="col-xs-9">
									<p class="source"><strong>A mesa {{= idMesa}} pediu para fechar a conta!</strong></p>
								</div>
								<div class="col-xs-3" id="btn-container">
									<button class="btn btn-pago show"><strong>Finalizado!</strong></button>
									<h2 class="hide">Total: <span class="preco fonte2">{{= total}}</span></h2>
								</div>
							</div>
							<div class="description row">
								<div class="col-xs-12 container-itens">
									<ul>
									{{each itens}}
										<li class="temp">
											<input class="cklist" type="checkbox" name="demo" value="one" /> {{= nome}} 
												<span class="not-bold texto-dir">{{= qtd}} x 
													<span class="preco">R$ {{= preco}}</span>
												</span>
										<button type="button" class="btn-danger btn-xs" aria-label="Right Align">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
										</li>
										<hr>
									{{/each}}
									</ul>
								</div>

								<div class="row">
									<div class="col-xs-3 menos-padd">
										<button class="btn btn-pago"><strong>Finalizado!</strong></button>
									</div>
									<div class="col-xs-9 menos-padd">
										<a class="minimiza" href="#"><strong>Minimizar o pedido</strong></a>
									</div>
								</div>

							</div>
						</div>
					{{else}}
						<div class="article ativo-master-class">
							<div class="item row">
								<div class="col-xs-9">
									<p class="source"><strong>A mesa {{= idMesa}} fez um pedido!</strong></p>
								</div>
								<div class="col-xs-3" id="btn-container">
									<button class="btn btn-ativo show"><strong>Entegue!</strong></button>
									<h2 class="hide">Total: <span class="preco fonte2">{{= total}}</span></h2>
								</div>
							</div>
							<div class="description row">
								<div class="col-xs-12 container-itens">
									<ul>
									{{each itens}}
										<li class="temp">
										<input class="cklist" type="checkbox" name="demo" value="one" /> {{= nome}} 
											<span class="not-bold texto-dir">{{= qtd}} x 
												<span class="preco">R$ {{= preco}}</span>
											</span>
										<button type="button" class="btn-danger btn-xs" aria-label="Right Align">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
										</li>
										<hr>
									{{/each}}
									</ul>
								</div>

								<div class="row">
									<div class="col-xs-3 menos-padd">
										<button class="btn btn-ativo"><strong>Entegue!</strong></button>
									</div>
									<div class="col-xs-9 menos-padd">
										<a class="minimiza" href="#"><strong>Minimizar o pedido</strong></a>
									</div>
								</div>

							</div>
						</div>
					{{/if}}		
		</script>

	</head>
	<body>

		<div class="container">
			<div class="row igBigode"></div>
		</div>

		<div class="container" id="todos-pedidos"></div>
		
		<script>
		function displayVals() {
		    $(document).ready(function(){

		    var oia = $('.article:nth-child(1)').find('.preco');
			//console.log(oia);
			});
		//	console.log(oia.val());
		} 
		//$( "select" ).change( displayVals );
		displayVals();
		</script>

	</body>
</html>