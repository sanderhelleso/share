export default (files) => {
	const formData = new FormData();

	Array.from(files).forEach((file) => {
		formData.append('files', file);
	});

	return formData;
};
