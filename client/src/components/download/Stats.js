import React from 'react';
import styled from 'styled-components';
import StatsCol from './StatsCol';
import bytesToUnit from '../../util/bytesToUnit';

const Stats = ({ stats }) => {
	const cols = [
		{
			highlight: bytesToUnit(stats.totalSize),
			subtext: 'Total Size'
		},
		{
			highlight: stats.totalDownloads,
			subtext: 'Downloads'
		},
		{
			highlight: stats.sharedFromCountry,
			subtext: 'Shared From'
		}
	];

	const renderCols = () => {
		return cols.map((col, i) => {
			return <StatsCol key={i} {...col} />;
		});
	};

	return <StyledAside>{renderCols()}</StyledAside>;
};

export default Stats;

const StyledAside = styled.aside`max-width: 300px;`;
