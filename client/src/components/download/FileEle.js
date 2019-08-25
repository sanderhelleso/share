import React from 'react';
import styled from 'styled-components';
import FileEleDownloadBtn from './FileEleDownloadBtn';

const FileEle = ({ name, size, type, shareCode }) => {
	return (
		<StyledDiv>
			<div>
				<h5>{name.length > 50 ? `${name.substring(0, 50)}...` : name}</h5>
				<p>{type}</p>
			</div>
			<FileEleDownloadBtn size={size} fileName={`${shareCode}/${name}`} />
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

	@media screen and (max-width: 1350px) {
		min-width: calc(100% - 4rem);
		padding: 0 1.5rem;
	}

	@media screen and (max-width: 600px) {
		h5 {
			font-size: 0.85rem !important;
		}

		p {
			font-size: 10px !important;
		}
	}

	h5 {
		margin: 0;
		color: #ffffff;
		font-family: 'Comfortaa', cursive;
		font-size: 1rem;
		word-break: break-all;
	}

	p {
		margin-top: 0.45rem;
		font-size: 12px;
		color: #d6c8ff;
	}
`;
