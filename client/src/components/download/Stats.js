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
	position: -webkit-sticky; /* Safari */
	position: sticky;
	top: 5rem;
	max-width: 300px;
	min-width: 300px;
	max-height: 450px;
	min-height: 450px;
	background-color: #ffffff;
	box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.1);
	border-radius: 6px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	margin: 0 auto;

	@media screen and (max-width: 1350px) {
		position: -webkit-fixed; /* Safari */
		position: fixed;
		top: calc(100% - 100px);
		left: 50%;
		transform: translate(-50%);
		max-width: 100%;
		min-width: 100%;
		max-height: 100px;
		min-height: 100px;
		flex-direction: row;
		border-radius: 0;
		box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.3);
	}

	@media screen and (max-width: 800px) {
	}
`;
