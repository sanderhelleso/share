import React from 'react';
import styled from 'styled-components';

const StatsCol = ({ highlight, subtext, withBorder }) => (
	<StyledDiv withBorder={withBorder}>
		<h3>{highlight}</h3>
		<p>{subtext}</p>
	</StyledDiv>
);

export default StatsCol;

const StyledDiv = styled.div`
	max-height: 150px;
	min-height: 150px;
	padding: 1.5rem 0;
	min-width: 100%;
	border-radius: 4px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	border-bottom: ${({ withBorder }) => (withBorder ? '1px solid #eeeeee' : 'none')};

	h3 {
		font-weight: 800;
		letter-spacing: 3px;
		font-size: 2rem;
	}

	p {
		color: #9e9e9e;
	}
`;
