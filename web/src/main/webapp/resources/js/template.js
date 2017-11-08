function hideDialogOnSuccess(args, dialogWidgetVar) {
    if (args && !args.validationFailed) {
        dialogWidgetVar.hide();
    }
}

function handleComplete(xhr, status, args) {
	if (args && !args.validationFailed) {
		dialog.hide();
	}
}