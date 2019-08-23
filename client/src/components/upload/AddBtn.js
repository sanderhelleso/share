import React from 'react';
import { Plus, Trash } from 'react-feather';
import styled from 'styled-components';

const AddBtn = ({ remove, clearFiles }) => {
	const _clearFiles = (e) => {
		if (remove) {
			e.stopPropagation();
			clearFiles();
		}
	};

	return (
		<StyledDiv remove={remove} title={`${remove ? 'Remove' : 'Add'} files`} onClick={_clearFiles}>
			{remove ? <Trash /> : <Plus />}
		</StyledDiv>
	);
};

export default AddBtn;

const StyledDiv = styled.div`
	min-height: 50px;
	min-width: 50px;
	border-radius: 0px;
	border-bottom-right-radius: 4px;
	border-top-right-radius: 4px;
	background-color: ${({ remove }) => (remove ? '#e61c5d' : '#ffbd39')};
	transition: 0.3s ease-in-out;
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
