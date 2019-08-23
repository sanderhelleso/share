import React from 'react';
import styled from 'styled-components';
import { fadeInPure } from '../../util/keyframes';
import ShareCode from './ShareCode';
import ResetBtn from './ResetBtn';

const baseUrl = 'http://localhost:4000/share/download?fileName=';

const Result = ({ shareCode, reset }) => {
	return (
		<StyledDiv>
			<ResetBtn reset={reset} />
			<p className="share">
				Share the link below with anyone you want to be able to see and download your files!
			</p>
			<span id="emoji" role="img" className="no-select">
				👇
			</span>
			<ShareCode shareCode={shareCode} />
			<span className="no-select">Click to Copy</span>
		</StyledDiv>
	);
};

export default Result;

const StyledDiv = styled.div`
	animation: ${fadeInPure} 0.8s ease forwards;
	text-align: center;

	p.share {
		display: block;
		font-size: 1rem;
		max-width: 400px;
		margin: 1rem auto;
	}

	span {
		cursor: pointer;

		&#emoji {
			font-size: 3rem;
			pointer-events: none;
		}
	}
`;
