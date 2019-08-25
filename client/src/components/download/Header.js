import React from 'react';
import styled from 'styled-components';

const Header = ({ sharedFrom, expiresAt }) => {
	return (
		<StyledHeader>
			<h2>From {sharedFrom}</h2>
			<p>Expires {new Date(expiresAt).toUTCString()}</p>
		</StyledHeader>
	);
};

export default Header;

const StyledHeader = styled.header`
	margin-bottom: 4rem;

	h2 {
		color: #ffffff;
		margin: 0;
		font-family: 'Comfortaa', cursive;
		font-size: 2rem;
	}

	p {
		margin-top: 1rem;
		color: #ffffff;
		opacity: 0.7;
		font-size: 0.9rem;
	}
`;
