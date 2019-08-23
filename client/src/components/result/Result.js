import React from 'react';
import styled from 'styled-components';
import { fadeInPure } from '../../util/keyframes';

const Result = ({ shareCode, reset }) => {
	return (
		<StyledDiv>
			<span>Share code: </span>
			<h2>{shareCode}</h2>
		</StyledDiv>
	);
};

export default Result;

const StyledDiv = styled.div`
	animation: ${fadeInPure} 0.8s ease forwards;

	h2 {
		color: #ffffff;
		font-size: 2rem;
	}
`;
