$(document).ready(function(){

	$('.article').click(function() {
		var teste = $(this).hasClass('current');
		var toggle_btn = $(this).find(".item > #btn-container").hasClass('hide');

		console.log(toggle_btn);
		if(!teste) {
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