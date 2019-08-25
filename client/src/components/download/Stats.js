import React from 'react';
import styled from 'styled-components';
import StatsCol from './StatsCol';
import bytesToUnit from '../../util/bytesToUnit';
import FileEleDownloadBtn from './FileEleDownloadBtn';

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
			return <StatsCol key={i} {...col} withBorder={i !== cols.length - 1} />;
		});
	};

	return (
		<StyledAside>
			<FileEleDownloadBtn main={true} expiresAt={new Date(stats.expiresAt).toUTCString()} />
			{renderCols()}
		</StyledAside>
	);
};

export default Stats;

const StyledAside = styled.aside`
	max-width: 300px;
	position: -webkit-sticky; /* Safari */
	position: sticky;
	top: 4rem;
	max-height: 650px;
	background-color: #ffffff;
	box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.1);
	border-radius: 6px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
`;
