import React from 'react';
import styled from 'styled-components';
import { ClipLoader } from 'react-spinners';
import { fadeInPure } from '../util/keyframes';

const Loader = ({ text }) => {
	return (
		<LoaderCont>
			<ClipLoader sizeUnit={'px'} size={90} color="#ffbd39" loading={true} />
			<p>{text}</p>
		</LoaderCont>
	);
};

export default Loader;

export const LoaderCont = styled.main`
	animation: ${fadeInPure} 0.8s ease forwards;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;

	p {
		opacity: 0.7;
		margin-top: 4rem;
	}
`;
