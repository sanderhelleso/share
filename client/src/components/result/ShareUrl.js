import React from 'react';
import styled from 'styled-components';
import { Clipboard } from 'react-feather';

const ShareUrl = ({ shareUrl, copy }) => {
	return (
		<StyledUrl canCopy={copy} onClick={copy}>
			<h2>{shareUrl}</h2>
			<Clipboard />
		</StyledUrl>
	);
};

export default ShareUrl;

const StyledUrl = styled.div`
	min-width: 500px;
	min-height: 60px;
	border-radius: 4px;
	border: 2px solid #ffbd39;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 1rem;
	cursor: ${({ canCopy }) => (canCopy ? 'pointer' : 'normal')};
	margin-top: 4rem;
	margin-bottom: 2rem;

	h2 {
		color: #ffffff;
		font-size: 0.9rem;
		margin: 0;
	}

	svg {
		stroke: #ffffff;
		opacity: 0.5;
		margin-left: 2rem;
	}
`;
