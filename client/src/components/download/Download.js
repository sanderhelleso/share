import React, { useState, useEffect } from 'react';
import _fetch from '../../util/_fetch';
import { withRouter } from 'react-router-dom';

const baseUrl = 'http://localhost:4000/share/retrieve?dirID=';

const Download = ({ match: { params } }) => {
	const [ data, setData ] = useState();

	useEffect(() => {
		fetchShare();
	}, []);

	const fetchShare = async () => {
		const { shareCode } = params;

		try {
			const response = await _fetch(`${baseUrl}${shareCode}`);
			const data = await response.json();
			setData(data);
		} catch (error) {
			setData(false);
		}
	};

	const render = () => {
		if (data) {
			return <h1>Valid</h1>;
		}

		if (data === false) {
			return <h1>Invalid code</h1>;
		}

		return <h1>Loading...</h1>;
	};

	return render();
};

export default withRouter(Download);
