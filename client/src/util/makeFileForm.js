export default (files) => {
	const formData = new FormData();

	Array.from(files).forEach((file, i) => {
		formData.append(`file_${i}`, file);
	});

	return formData;
};
