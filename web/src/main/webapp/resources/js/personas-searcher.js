
/*
 * Limpia el div de resultados de la busqueda de personas
 */
function clearPersonSearchResults(id) {
	jQuery(id).html("<img src='/personasonline/img/loading.gif' />");
	/*
	var loadingImg = "#{resource['images/loading.gif']}";
	jQuery(id).html('<img src="' + loadingImg + '" />');
	*/
}
