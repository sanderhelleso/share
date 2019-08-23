import React from 'react';
import styled from 'styled-components';
import Upload from '../upload/Upload';
import UploadBtn from '../upload/UploadBtn';

const Home = () => {
	return (
		<StyledHome>
			<h1>SHARE</h1>
			<p>Upload up to 500mb of files and share to whoever you want</p>
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

	p {
		color: #ffffff;
	}

	max-width: 75%;
	min-height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin: 0 auto;
`;
