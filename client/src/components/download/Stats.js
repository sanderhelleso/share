import React from 'react';
import styled from 'styled-components';
import StatsCol from './StatsCol';

const Stats = ({ stats }) => {
	return (
		<StyledAside>
			<StatsCol />
			<StatsCol />
			<StatsCol />
		</StyledAside>
	);
};

export default Stats;

const StyledAside = styled.aside`max-width: 300px;`;
