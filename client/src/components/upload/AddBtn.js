import React from 'react';
import { Plus } from 'react-feather';
import styled from 'styled-components';

const AddBtn = () => (
	<StyledDiv>
		<Plus />
	</StyledDiv>
);

export default AddBtn;

const StyledDiv = styled.div`
	min-height: 50px;
	min-width: 50px;
	border-radius: 0px;
	border-bottom-right-radius: 4px;
	border-top-right-radius: 4px;
	background-color: #ffbd39;
	position: absolute;
	right: 0;
	display: flex;
	align-items: center;
	justify-content: center;

	svg {
		stroke: #ffffff;
		opacity: 0.7;
	}
`;
