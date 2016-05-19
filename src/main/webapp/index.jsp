<!doctype html>
<html>
	<head>

		<meta charset="utf-8">
		<link href="./css/bootstrap.min.css" rel="stylesheet" media="all">
		<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="./css/style.css">

	 <!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
		<script src="js/jquery.js"></script>
		<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
		<script src="js/main.js"></script>

		<script id="clientTemplate" type="text/html">
		
			{{each pedidos}}
				{{if status == "ENTREGUE"}}
					{{each itens}}
						<div class="article entregue-master-class">
							<div class="item row">
								<div class="col-xs-9">
									<p class="source"><strong>A mesa ${numeroMesa} teve o pedido entregue!</strong></p>
								</div>
								<div class="col-xs-3" id="btn-container">
									<button class="btn btn-entregue show"><strong>Pago!</strong></button>
									<h2 class="hide">Total: <span class="preco fonte2">R$ 51,00</span></h2>
								</div>
							</div>
							<div class="description row">
								<div class="col-xs-12 container-itens">
									<ul>
										<li><input class="cklist" type="checkbox" name="demo" value="one" /> ${nome}
											<span class="not-bold texto-dir"> ${qtd} x 
												<span class="preco">R$ ${preco}</span>
											</span>
  										<button type="button" class="btn-danger btn-xs" aria-label="Right Align">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
										</li>
										<hr>
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
					{{/each}}
				{{else status == "PAGO"}}
					{{each itens}}
						<div class="article pago-master-class">
							<div class="item row">
								<div class="col-xs-9">
									<p class="source"><strong>A mesa ${numeroMesa} pediu para fechar a conta!</strong></p>
								</div>
								<div class="col-xs-3" id="btn-container">
									<button class="btn btn-pago show"><strong>Finalizado!</strong></button>
									<h2 class="hide">Total: <span class="preco fonte2">R$ 51,00</span></h2>
								</div>
							</div>
							<div class="description row">
								<div class="col-xs-12 container-itens">
									<ul>
										<li><input class="cklist" type="checkbox" name="demo" value="one" /> ${nome} <span class="not-bold texto-dir">${qtd} x <span class="preco">R$ ${preco}</span></span>
										<button type="button" class="btn-danger btn-xs" aria-label="Right Align">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
										</li>
										<hr>
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
					{{/each}}
				{{else}}
					{{each itens}}
						<div class="article ativo-master-class">
							<div class="item row">
								<div class="col-xs-9">
									<p class="source"><strong>A mesa ${numeroMesa} fez um pedido!</strong></p>
								</div>
								<div class="col-xs-3" id="btn-container">
									<button class="btn btn-ativo show"><strong>Entegue!</strong></button>
									<h2 class="hide">Total: <span class="preco fonte2">R$ 51,00</span></h2>
								</div>
							</div>
							<div class="description row">
								<div class="col-xs-12 container-itens">
									<ul>
										<li><input class="cklist" type="checkbox" name="demo" value="one" /> ${nome} <span class="not-bold texto-dir">${qtd} x <span class="preco">R$ ${preco}</span></span>
										<button type="button" class="btn-danger btn-xs" aria-label="Right Align">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
										</li>
										<hr>
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
					{{/each}}
				{{/if}}
			{{/each}}
	
		</script>

	</head>
	<body>

		<div class="container">
			<div class="row igBigode"></div>
		</div>

		<div class="container" id="todos-pedidos"></div>

	</body>
</html>