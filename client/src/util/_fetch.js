export default async (url, method, body) => {
	try {
		const response = await fetch(url, {
			method,
			body
		});

		if (response.status > 205) {
			const { message } = await response.json();
			throw message;
		}

		return response;
	} catch (error) {
		throw error;
	}
};
