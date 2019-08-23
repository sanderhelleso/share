import React from 'react';
import styled from 'styled-components';
import { ClipLoader } from 'react-spinners';

const Loader = () => {
	return (
		<LoaderCont>
			<ClipLoader sizeUnit={'px'} size={90} color="#ffbd39" loading={true} />
		</LoaderCont>
	);
};

export default Loader;

export const LoaderCont = styled.main`
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	margin: 2rem auto;
`;
