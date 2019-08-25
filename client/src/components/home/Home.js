import React, { useState } from 'react';
import styled from 'styled-components';
import Upload from '../upload/Upload';
import Loader from '../../util/Loader';
import { fadeInPure } from '../../util/keyframes';
import Result from '../result/Result';

const Home = () => {
	const [ loading, setLoading ] = useState(false);
	const [ shareCode, setShareCode ] = useState('');

	const reset = () => {
		setLoading(false);
		setShareCode('');
	};

	const renderHeader = () => {
		if (shareCode) {
			return <Result shareCode={shareCode} reset={reset} />;
		}

		if (loading) {
			return <Loader text="Uploading..." />;
		}

		return (
			<StyledDiv>
				<h1>SHARE</h1>
				<p>Upload and share up to 500MB for FREE</p>
				<span>Files automatically expire after 1 hour</span>
				<Upload setLoading={setLoading} setShareCode={setShareCode} />
			</StyledDiv>
		);
	};

	return <StyledHome>{renderHeader()}</StyledHome>;
};

export default Home;

const StyledHome = styled.main`
	max-width: 75%;
	min-height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin: 0 auto;

	@media screen and (max-width: 700px) {
		max-width: 80%;
	}

	h1 {
		color: #ffbd39;
		font-size: 3.5rem;
		letter-spacing: 4px;

		@media screen and (max-width: 700px) {
			font-size: 2.5rem;
		}
	}

	p {
		margin-bottom: 0.3rem;
		@media screen and (max-width: 700px) {
			font-size: 0.8rem;
		}
	}

	p,
	span {
		color: #ffffff;
	}

	span {
		font-size: 12px;
		margin-top: 1rem;
		opacity: 0.7;
	}
`;

const StyledDiv = styled.div`
	animation: ${fadeInPure} 0.8s ease forwards;
	text-align: center;
`;
