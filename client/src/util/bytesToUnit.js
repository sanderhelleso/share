const UNITS = [ 'Bytes', 'KB', 'MB', 'GB', 'TB' ];
const ONE_KB = 1024;

export default (bytes) => {
	if (!bytes) return '0 Byte';

	const i = parseInt(Math.floor(Math.log(bytes) / Math.log(ONE_KB)));

	return Math.round(bytes / Math.pow(ONE_KB, i), 2) + ' ' + UNITS[i];
};
