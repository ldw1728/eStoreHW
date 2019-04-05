package kr.ac.hansung.cse.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.hansung.cse.dao.ProductDao;
import kr.ac.hansung.cse.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	
	public List<Product> getProducts(){
		return productDao.getProducts();
	}


	public boolean addProduct(Product product) {
		
		return productDao.addProduct(product);
		
	}


	public boolean deleteProduct(int id) {
		
		return productDao.deleteProduct(id);
	}


	public Product getProductById(int id) {
		
		return productDao.getProductById(id);
	}


	public boolean updateProduct(Product product) {
		
		return productDao.updateProduct(product);
	}
	
	public boolean uploadFile(MultipartHttpServletRequest mhsr,Product product) {
		
		MultipartFile mf = mhsr.getFile("image");
		if(!mf.isEmpty()) {
		
		String path = "F:\\dev\\workspace\\eStoreHW\\src\\main\\webapp\\resources\\upload";
		String[] type = mf.getContentType().split("/");
		if((!type[1].equals("jpg") && !type[1].equals("jpeg") && !type[1].equals("png")) || mf.getSize()>5242880 ) {
			return false;
		}
		String fileName = product.getName()+"."+type[1];
		
		long fileSize = mf.getSize();
		
		System.out.println("fileName : " + fileName);
		System.out.println("fileSize : " + fileSize);
		
		try {
			mf.transferTo(new File(path+"\\"+fileName));
			return true;
			
		}catch(IllegalStateException e) {
			e.printStackTrace();
			return false;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		}
		else 
			return false;
	}
	
	public String getProductImageName(Product product) {
		
		String path = "F:\\dev\\workspace\\eStoreHW\\src\\main\\webapp\\resources\\upload";
		
		File imgDir = new File(path);
		
		File[] imgFiles = imgDir.listFiles();
		
		for(int i=0; i<imgFiles.length;i++) {
			if(imgFiles[i].getName().contains(product.getName())) {
				return imgFiles[i].getName();
			}
		}
		
		return null;
	}
}
