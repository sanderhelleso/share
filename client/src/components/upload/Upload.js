import React, { useRef, useState } from 'react';
import styled from 'styled-components';
import AddBtn from './AddBtn';
import UploadBtn from './UploadBtn';
import bytesToUnit from '../../util/bytesToUnit';
import _fetch from '../../util/_fetch';
import makeFileForm from '../../util/makeFileForm';

const MAX_UPLOAD_SIZE = 524288000; // 500mb

const Upload = ({ setLoading, setShareCode }) => {
	const inputRef = useRef();
	const [ files, setFiles ] = useState([]);
	const [ totSize, setTotSize ] = useState(0);

	const handleSubmit = async (e) => {
		e.preventDefault();
		setLoading(true);
		const t = new Date().getTime();

		const response = await _fetch(
			'http://localhost:4000/share/upload?timeout=55155000',
			'PUT',
			makeFileForm(files)
		);

		const shareCode = await response.text();

		const timeTaken = new Date().getTime() - t;
		setTimeout(() => setShareCode(shareCode), timeTaken < 1000 ? 1000 - timeTaken : 0);
	};

	const handleChange = () => {
		setFiles(inputRef.current.files);
		_setTotSize(inputRef.current.files);
	};

	const clearFiles = () => {
		setFiles([]);
		setTotSize(0);
		inputRef.current.value = '';
	};

	const setText = () => {
		if (!files.length) {
			return 'Select files...';
		}

		const grammar = `file${files.length === 1 ? '' : 's'}`;

		return `${files.length} ${grammar} selected (${bytesToUnit(totSize)})`;
	};

	const isDisabled = () => {
		return !files.length || totSize > MAX_UPLOAD_SIZE;
	};

	const _setTotSize = (files) => {
		if (!files.length) return 0;

		const size = Array.from(files).reduce(getSum, 0);
		setTotSize(size);
	};

	const getSum = (total, { size }) => total + Math.round(size);

	return (
		<StyledForm onSubmit={handleSubmit}>
			<input ref={inputRef} type="file" multiple={true} onChange={handleChange} required={true} />
			<StyledInput onClick={() => inputRef.current.click()}>
				<h5>{setText()}</h5>
				<AddBtn remove={files.length} clearFiles={clearFiles} />
			</StyledInput>
			<UploadBtn text="Upload" disabled={isDisabled()} />
		</StyledForm>
	);
};

export default Upload;

const StyledForm = styled.form`
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

	@media screen and (max-width: 700px) {
		min-width: 90%;
		max-width: 90%;
	}

	h5 {
		margin: 0;
		color: #d6c8ff;
		font-family: 'Comfortaa', cursive;
	}
`;
