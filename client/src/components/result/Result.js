import React, { useState } from 'react';
import styled from 'styled-components';
import { fadeInPure } from '../../util/keyframes';
import ShareUrl from './ShareUrl';
import ResetBtn from './ResetBtn';
import copyToClip from '../../util/copyToClip';

const baseUrl = 'http://localhost:3000/download/';
const canCopy = navigator.clipboard;

const Result = ({ shareCode, reset }) => {
	const shareUrl = `${baseUrl}${shareCode}`;
	const [ copied, setCopied ] = useState(false);

	const copy = () => {
		if (copied) return;

		copyToClip(shareUrl);
		setCopied(true);
		setTimeout(() => setCopied(false), 3000);
	};

	return (
		<StyledDiv>
			<ResetBtn reset={reset} />
			<p className="share">
				Share the link below with anyone you want to be able to see and download your files!
			</p>
			<span id="emoji" role="img" className="no-select" aria-label="Copy share link below">
				👇
			</span>
			<ShareUrl shareUrl={shareUrl} copy={canCopy ? copy : null} />
			{canCopy && (
				<span className="no-select" onClick={copy}>
					{copied ? 'Copied to Clipboard!' : 'Click to Copy'}
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
