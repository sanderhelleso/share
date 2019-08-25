import React from 'react';
import styled from 'styled-components';

const FileEle = ({ name, size, type, dlPath }) => {
	return <StyledDiv />;
};

export default FileEle;

const StyledDiv = styled.div`
	min-height: 90px;
	min-width: 500px;
	background-color: #7e6bc4;
	border-radius: 4px;
	margin-bottom: 3rem;
	display: flex;
	align-items: center;
	padding: 0 2rem;
	box-shadow: 0px 7.5px 15px rgba(0, 0, 0, 0.1);
`;
