package kr.co.cwit.common.util;

import websquare.upload.handl.AbstractUploadFileDefiner;

public class CustomUploadFileDefiner extends AbstractUploadFileDefiner {
	
	@Override
	public String getFileName(String clientFileName, String originalFileName) throws Exception {
		return originalFileName;
	}

	@Override
	public String getFilePath(String filePath) throws Exception {
		return filePath;
	}
}
