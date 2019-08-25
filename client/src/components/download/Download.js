import React, { useState, useEffect, Fragment } from 'react';
import _fetch from '../../util/_fetch';
import { withRouter } from 'react-router-dom';
import styled from 'styled-components';
import FilesList from './FilesList';
import Stats from './Stats';
import Header from './Header';
import { ArrowLeft } from 'react-feather';
import ActionBtn from '../../util/ActionBtn';
import { fadeInPure } from '../../util/keyframes';
import Loader from '../../util/Loader';

const baseUrl = 'http://localhost:4000/share/retrieve?dirID=';

const Download = ({ history, match: { params: { shareCode } } }) => {
	const [ data, setData ] = useState();

	useEffect(() => {
		fetchShare();
	}, []);

	const fetchShare = async () => {
		let _data = false;
		let t = new Date().getTime();

		try {
			const response = await _fetch(`${baseUrl}${shareCode}`);
			_data = await response.json();
		} finally {
			const timeTaken = new Date().getTime() - t;
			setTimeout(() => setData(_data), timeTaken < 1000 ? 1000 - timeTaken : 0);
		}
	};

	const renderDownload = () => {
		const { info, files, totalSize } = data;
		const stats = { totalSize, ...info };
		const { expiresAt, sharedFromCountry } = info;

		return (
			<Fragment>
				<ActionBtn onClick={() => history.push('/')} title="reset" icon={<ArrowLeft />} />
				<StyledDiv>
					<Header numFiles={files.length} expiresAt={expiresAt} sharedFrom={sharedFromCountry} />
					<StyledGrid>
						<FilesList files={files} />
						<Stats stats={stats} />
					</StyledGrid>
				</StyledDiv>
			</Fragment>
		);
	};

	const render = () => {
		if (data) {
			return renderDownload();
		}

		if (data === false) {
			return <h1>Invalid code</h1>;
		}

		return (
			<StyledLoaderCont>
				<Loader text="Gathering files..." />
			</StyledLoaderCont>
		);
	};

	return render();
};

export default withRouter(Download);

const StyledDiv = styled.div`
	padding-top: 10rem;
	min-height: calc(90vh - 10rem);
	max-width: 1100px;
	margin: 0 auto;
	animation: ${fadeInPure} 0.8s ease forwards;

	@media screen and (max-width: 1350px) {
		max-width: 85%;
	}
`;

const StyledGrid = styled.div`
	display: grid;
	grid-template-columns: 2fr 1fr;
	grid-column-gap: 3rem;

	@media screen and (max-width: 1350px) {
		grid-template-columns: 1fr;
	}
`;

const StyledLoaderCont = styled.div`
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
`;
