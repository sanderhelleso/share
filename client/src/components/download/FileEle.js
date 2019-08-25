import React from 'react';
import styled from 'styled-components';
import FileEleDownloadBtn from './FileEleDownloadBtn';

const FileEle = ({ name, size, type, dlPath }) => {
	return (
		<StyledDiv>
			<div>
				<h5>{name}</h5>
				<p>{type}</p>
			</div>
			<FileEleDownloadBtn size={size} dlPath={dlPath} />
		</StyledDiv>
	);
};

export default FileEle;

const StyledDiv = styled.div`
	min-height: 100px;
	min-width: 500px;
	background-color: #7e6bc4;
	border-radius: 4px;
	margin-bottom: 2rem;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 2rem;
	box-shadow: 0px 7.5px 15px rgba(0, 0, 0, 0.1);

	h5 {
		margin: 0;
		color: #ffffff;
		font-family: 'Comfortaa', cursive;
		font-size: 1rem;
	}

	p {
		margin-top: 0.45rem;
		font-size: 12px;
		color: #d6c8ff;
	}
`;
