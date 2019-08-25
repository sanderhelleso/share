import React, { Fragment } from 'react';
import styled from 'styled-components';
import { Download } from 'react-feather';
import bytesToUnit from '../../util/bytesToUnit';

const FileEleDownloadBtn = ({ size, dlPath, main }) => {
	const renderInner = () => {
		if (main) {
			return <h5>Download</h5>;
		}

		return (
			<Fragment>
				<span>{bytesToUnit(size)}</span>
				<Download />
			</Fragment>
		);
	};

	return (
		<StyledBtn id={main ? 'main-btn' : null} title="Donwload">
			{renderInner()}
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
		border-left: 0.25px solid rgba(0, 0, 0, 0.975);
		padding-left: 1rem;
		min-height: 30px;
	}

	span {
		stroke: #424242;
	}

	&#main-btn {
		border-radius: 8px;
		position: absolute;
		top: -2rem;
		min-width: 220px;
		min-height: 65px;
		flex-direction: column;
		background: #f2994a; /* fallback for old browsers */
		background: -webkit-linear-gradient(to right, #f2c94c, #f2994a); /* Chrome 10-25, Safari 5.1-6 */
		background: linear-gradient(
			to right,
			#f2c94c,
			#f2994a
		); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

		h5 {
			margin: 0;
			text-transform: uppercase;
			font-size: 1.45rem;
			letter-spacing: 2px;
		}
	}
`;
