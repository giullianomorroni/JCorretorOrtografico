package com.giullianomorroni.jcorretor.unzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CompressorArquivo {

	public static List<File> unZipIt(String zipFile, String outputFolder) {
		List<File> files = new ArrayList<File>(); 
		byte[] buffer = new byte[1024];
		try {
			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry zipEntry = zis.getNextEntry();
			while (zipEntry != null) {
				String fileName = zipEntry.getName();
				File newFile = new File(outputFolder + File.separator + fileName);
				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();
				files.add(newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				zipEntry = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return files;
	}
}
