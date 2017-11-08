

function disableShInput() {
	console.log("disable input");
	jQuery(".shareHolderData input").attr('disabled', 'disabled');
	jQuery(".shareHolderData").addClass('disabled');
}

function enableShInput() {
	jQuery(".shareHolderData input").removeAttr('disabled');
	jQuery(".shareHolderData").removeClass('disabled');
}

