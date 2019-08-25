import React from 'react';
import styled from 'styled-components';

const Header = ({ numFiles, sharedFrom, expiresAt }) => {
	return (
		<StyledHeader>
			<h2>
				{numFiles} file{numFiles === 1 ? '' : 's'} from {sharedFrom}
			</h2>
			<p>Expires {new Date(expiresAt).toUTCString()}</p>
		</StyledHeader>
	);
};

export default Header;

const StyledHeader = styled.header`
	margin-bottom: 4rem;

	@media screen and (max-width: 600px) {
		h2 {
			font-size: 1.75rem !important;
			line-height: 1.45;
		}

		p {
			font-size: 0.8rem !important;
		}
	}

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
