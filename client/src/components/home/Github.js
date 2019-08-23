import React from 'react';
import styled from 'styled-components';
import { GitHub } from 'react-feather';

const Github = () => (
	<StyledA href="https://github.com/sanderhelleso/share" target="_blank" title="View on GitHub">
		<GitHub />
	</StyledA>
);

export default Github;

const StyledA = styled.a`
	text-decoration: none;
	color: #ffffff;

	position: absolute;
	top: 2rem;
	right: 2rem;

	svg {
		height: 2rem;
		width: 2rem;
		opacity: 0.7;
	}
`;
