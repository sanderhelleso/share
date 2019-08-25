import React from 'react';
import styled from 'styled-components';
import FileEle from './FileEle';

const FilesList = ({ files }) => {
	const renderFiles = () => {
		return files.map((file, i) => {
			return <FileEle key={i} {...file} />;
		});
	};

	return <StyledMain>{renderFiles()}</StyledMain>;
};

export default FilesList;

const StyledMain = styled.main`
	max-width: 700px;

	@media screen and (max-width: 1350px) {
		max-width: 100%;
	}
`;
