import React from 'react';
import styled from 'styled-components';
import Upload from '../upload/Upload';
import UploadBtn from '../upload/UploadBtn';

const Home = () => {
	return (
		<StyledHome>
			<h1>SHARE</h1>
			<p>Upload and share up to 500MB for FREE</p>
			<span>Files automatically expire after 1 hour</span>
			<Upload />
		</StyledHome>
	);
};

export default Home;

const StyledHome = styled.main`
	h1 {
		color: #ffbd39;
		font-size: 3.5rem;
		letter-spacing: 4px;
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

	max-width: 75%;
	min-height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin: 0 auto;
`;
