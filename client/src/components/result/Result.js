import React from 'react';
import styled from 'styled-components';
import { fadeInPure } from '../../util/keyframes';
import ShareUrl from './ShareUrl';
import ResetBtn from './ResetBtn';
import copyToClip from '../../util/copyToClip';

const baseUrl = 'http://localhost:4000/share/retrieve?dirID=';
const canCopy = navigator.clipboard;

const Result = ({ shareCode, reset }) => {
	const shareUrl = `${baseUrl}${shareCode}`;
	const copy = () => copyToClip(shareUrl);

	return (
		<StyledDiv>
			<ResetBtn reset={reset} />
			<p className="share">
				Share the link below with anyone you want to be able to see and download your files!
			</p>
			<span id="emoji" role="img" className="no-select">
				👇
			</span>
			<ShareUrl shareUrl={shareUrl} copy={canCopy ? copy : null} />
			{canCopy && (
				<span className="no-select" onClick={copy}>
					Click to Copy
				</span>
			)}
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
