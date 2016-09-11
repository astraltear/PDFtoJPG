package com.astraltear.pdftojpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class ConvertPDFPagesToImages {
	public static void main(String[] args) {
		try {
			String sourceDirStr = "D:\\DDownloads\\original\\"; 
			String destinationDirStr = "D:\\DDownloads\\convert\\";
			
			File sourceDir = new File(sourceDirStr);
			
			
			
			File[] sourceFileList = sourceDir.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.toLowerCase().endsWith(".pdf");
			    }
			});
			
			for (File sourceFile : sourceFileList) {
				File destDir = new File(destinationDirStr+(String) File.separator+sourceFile.getName().replace(".pdf", ""));
				
				if (!destDir.exists()) {
					destDir.mkdirs();
				}
				System.out.println(destDir.getAbsolutePath());
				
				PDDocument document = PDDocument.load(sourceFile);
				PDFRenderer pdfRenderer = new PDFRenderer(document);
				
				for (int page = 0; page < document.getNumberOfPages(); ++page){ 
					System.out.println("folder["+destDir.getAbsolutePath()+"]total page["+document.getNumberOfPages()+"] make page["+page+"]");
					BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 150, ImageType.RGB);
					ImageIOUtil.writeImage(bim, destDir.getAbsolutePath() +(String) File.separator+sourceFile.getName().replace(".pdf", "")+ "-" + (page+1) + ".jpg", 150);
				}
			}

				System.out.println("END!!");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
