import React from 'react';
import styled from 'styled-components';
import { X } from 'react-feather';
import { fadeIn } from '../../util/keyframes';

const ResetBtn = ({ reset }) => (
	<StyledDiv className="no-select" onClick={reset} title="Reset">
		<X />
	</StyledDiv>
);

export default ResetBtn;

const StyledDiv = styled.div`
	animation: ${fadeIn} 0.4s ease forwards;
	min-width: 70px;
	min-height: 70px;
	border-radius: 50%;
	background-color: #7e6bc4;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	box-shadow: 0px 7.5px 15px rgba(0, 0, 0, 0.1);
	position: absolute;
	top: 2rem;
	left: 2rem;

	svg {
		stroke: #d6c8ff;
	}
`;
