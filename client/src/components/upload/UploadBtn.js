import React from 'react';
import styled from 'styled-components';

const UploadBtn = ({ text, disabled, onClick }) => {
	return (
		<StyledButton disabled={disabled} onClick={onClick}>
			{text}
		</StyledButton>
	);
};

export default UploadBtn;

const StyledButton = styled.button`
	cursor: pointer;
	padding: 0.75rem 2rem;
	border: none;
	outline: none;
	text-transform: uppercase;
	letter-spacing: 1px;
	border-radius: 4px;
	font-weight: 800;
	transition: 0.3s ease-in-out;
	min-width: 175px;
	min-height: 60px;
	position: relative;
	${({ disabled }) => {
		if (disabled) return disabledStyles;
		return normalStyles;
	}};

	svg {
		position: absolute;
		right: 20px;
		top: 20px;
		opacity: 0.5;
		height: 1.25rem;
		width: 1.25rem;
	}
`;

const normalStyles = `
    box-shadow: 0px 7.5px 15px rgba(0, 0, 0, 0.1);
	color: #ffffff;
    background: #F2994A;  /* fallback for old browsers */
        background: -webkit-linear-gradient(to right, #F2C94C, #F2994A);  /* Chrome 10-25, Safari 5.1-6 */
        background: linear-gradient(to right, #F2C94C, #F2994A); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

`;

const disabledStyles = `
	color: #9e9e9e;
	box-shadow: none;
    background-color: #e0e0e0;
	cursor: not-allowed;
`;
