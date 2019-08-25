import React from 'react';
import styled from 'styled-components';

const StatsCol = ({ highlight, subtext }) => (
	<StyledDiv>
		<h3>{highlight}</h3>
		<p>{subtext}</p>
	</StyledDiv>
);

export default StatsCol;

const StyledDiv = styled.div`
	max-height: 200px;
	min-height: 200px;
	margin-bottom: 3rem;
	min-width: 100%;
	background-color: #ffffff;
	border-radius: 4px;
	box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.1);
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;

	h3 {
		font-weight: 800;
		letter-spacing: 3px;
		font-size: 2rem;
	}

	p {
		color: #9e9e9e;
	}
`;
