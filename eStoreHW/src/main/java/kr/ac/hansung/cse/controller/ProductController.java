package kr.ac.hansung.cse.controller;

import java.awt.image.BufferedImage;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/products")
	public String getProducts(Model model) {
		
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		
		return "products"; // view's logical name
	}
	
	@RequestMapping(value="/viewProduct/{id}", method=RequestMethod.GET)
	public String infoProduct(@PathVariable int id, Model model) {
			
		Product product = productService.getProductById(id);
		model.addAttribute("product",product);
		
		String fileName = productService.getProductImageName(product);
		model.addAttribute("fileName", fileName);
	
		return "viewProduct";
	}
}
