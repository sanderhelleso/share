import React, { useState, useEffect } from 'react';
import _fetch from '../../util/_fetch';
import { withRouter } from 'react-router-dom';
import styled from 'styled-components';
import FilesList from './FilesList';
import Stats from './Stats';

const baseUrl = 'http://localhost:4000/share/retrieve?dirID=';

const Download = ({ match: { params: { shareCode } } }) => {
	const [ data, setData ] = useState();

	useEffect(() => {
		fetchShare();
	}, []);

	const fetchShare = async () => {
		try {
			const response = await _fetch(`${baseUrl}${shareCode}`);
			const data = await response.json();
			console.log(data);
			setData(data);
		} catch (error) {
			setData(false);
		}
	};

	const renderDownload = () => {
		const { info, files, totalSize } = data;
		const stats = { totalSize, ...info };

		return (
			<StyledDiv>
				<FilesList files={files} />
				<Stats stats={stats} />
			</StyledDiv>
		);
	};

	const render = () => {
		if (data) {
			return renderDownload();
		}

		if (data === false) {
			return <h1>Invalid code</h1>;
		}

		return <h1>Loading...</h1>;
	};

	return render();
};

export default withRouter(Download);

const StyledDiv = styled.div`
	padding-top: 8rem;
	display: grid;
	grid-template-columns: 2fr 1fr;
	grid-column-gap: 3rem;
	min-height: calc(90vh - 8rem);
	max-width: 1200px;
	margin: 0 auto;

	@media screen and (max-width: 1400px) {
		max-width: 1000px;
	}
`;
