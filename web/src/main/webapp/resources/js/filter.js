
/*
 * Limpia un input pasando su id
 */
function clearInput(ac_element) {
	var selector = '[id="' + ac_element + '"]';
	jQuery(selector).val('');
}

/*
 * Limpia un autocomplete pasando su id
 */
function clearAutocompleteInput(ac_element) {
	var selector = '[id="' + ac_element + '_input"]';
	jQuery(selector).val('');
	var hselector = '[id="' + ac_element + '_hinput"]';
	jQuery(hselector).val('');
}

/*
 * Selecciona un radio buton por su id y su indice
 */
function clickRadio(ac_element, index) {
	var selector = '[id="' + ac_element + ':' + index + '"]';
	//console.log(ac_element + ':' + index + ' = ' + jQuery(selector).attr('checked'));
	jQuery(selector).click();
	//jQuery(selector).attr('checked',true);
	//jQuery(selector).trigger('click');
}