import React from 'react';
import styled from 'styled-components';
import FileEle from './FileEle';

const FilesList = ({ files, shareCode }) => {
	const renderFiles = () => {
		return files.map((file, i) => {
			return <FileEle key={i} {...file} shareCode={shareCode} />;
		});
	};

	return <StyledMain>{renderFiles()}</StyledMain>;
};

export default FilesList;

const StyledMain = styled.main`
	max-width: 700px;
	padding-bottom: 3rem;

	@media screen and (max-width: 1350px) {
		max-width: 100%;
		padding-bottom: 120px;
	}
`;
