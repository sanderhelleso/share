import React from 'react';
import styled from 'styled-components';
import { Clipboard } from 'react-feather';

const ShareCode = ({ shareCode }) => {
	return (
		<StyledCode>
			<h2>{shareCode}</h2>
			<Clipboard />
		</StyledCode>
	);
};

export default ShareCode;

const StyledCode = styled.div`
	min-width: 500px;
	min-height: 60px;
	border-radius: 4px;
	border: 2px solid #ffbd39;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 1rem;
	cursor: pointer;
	margin-top: 4rem;
	margin-bottom: 2rem;

	h2 {
		color: #ffffff;
		font-size: 1.25rem;
		margin: 0;
	}

	svg {
		stroke: #ffffff;
		opacity: 0.5;
	}
`;
