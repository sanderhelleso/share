import React, { useRef, useState } from 'react';
import styled from 'styled-components';
import AddBtn from './AddBtn';
import UploadBtn from './UploadBtn';
import bytesToUnit from '../../util/bytesToUnit';

const MAX_UPLOAD_SIZE = 524288000; // 500mb

const Upload = () => {
	const inputRef = useRef();
	const [ files, setFiles ] = useState([]);
	const [ totSize, setTotSize ] = useState(0);

	const handleChange = () => {
		setFiles(inputRef.current.files);
		_seTtotSize(inputRef.current.files);
	};

	const setText = () => {
		if (!files.length) {
			return 'Select files...';
		}

		return `${files.length} files selected (${bytesToUnit(totSize)})`;
	};

	const isDisabled = () => {
		return !files.length || totSize > MAX_UPLOAD_SIZE;
	};

	const _seTtotSize = (files) => {
		if (!files.length) return 0;

		const size = Array.from(files).reduce(getSum, 0);
		setTotSize(size);
	};

	const getSum = (total, { size }) => total + Math.round(size);

	return (
		<StyledForm method="put" enctype="multipart/form-data">
			<input ref={inputRef} type="file" multiple={true} onChange={handleChange} />
			<StyledInput onClick={() => inputRef.current.click()}>
				<h5>{setText()}</h5>
				<AddBtn />
			</StyledInput>
			<UploadBtn text="Upload" disabled={isDisabled()} />
		</StyledForm>
	);
};

export default Upload;

const StyledForm = styled.div`
	input {
		display: none;
	}

	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	padding-bottom: 3rem;
	border-top: 0.5px solid rgba(255, 255, 255, 0.8);
	margin-top: 3rem;
`;

const StyledInput = styled.div`
	min-height: 50px;
	min-width: 500px;
	max-width: 500px;
	background-color: #7e6bc4;
	border-radius: 4px;
	margin-top: 3rem;
	margin-bottom: 5rem;
	cursor: pointer;
	display: flex;
	align-items: center;
	padding: 0 2rem;
	position: relative;
	box-shadow: 0px 7.5px 15px rgba(0, 0, 0, 0.1);

	h5 {
		margin: 0;
		color: #d6c8ff;
		font-family: 'Comfortaa', cursive;
	}
`;
