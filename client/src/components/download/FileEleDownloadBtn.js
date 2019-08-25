import React from 'react';
import styled from 'styled-components';
import { Download } from 'react-feather';
import bytesToUnit from '../../util/bytesToUnit';

const FileEleDownloadBtn = ({ size, dlPath }) => {
	return (
		<StyledBtn>
			<span>{bytesToUnit(size)}</span>
			<Download />
		</StyledBtn>
	);
};

export default FileEleDownloadBtn;

const StyledBtn = styled.button`
	min-width: 100px;
	min-height: 50px;
	border-radius: 4px;
	display: flex;
	background-color: #ffbd39;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	border-style: none;
	outline: none;
	box-shadow: 0px 7.5px 15px rgba(0, 0, 0, 0.1);
	padding: 0 1rem;

	svg {
		stroke: #424242;
		height: 1.25rem;
		width: 1.25rem;
		margin-left: 1rem;
	}

	span {
		stroke: #424242;
	}
`;
