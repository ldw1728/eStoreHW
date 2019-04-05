package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	 
	@Autowired
	private ProductService productService;
	
	@RequestMapping
	public String adminPage() {
		return "admin";
	}
	
	@RequestMapping("/productInventory")
	public String getProduct(Model model) { //controller -> model
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		
		return "productInventory";
	}
	
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.GET)
	public String addProduct(Model model) {
		
		Product product = new Product();
		product.setCategory("컴퓨터");
		model.addAttribute("product",product);
		
		return "addProduct";
	}
	
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result, MultipartHttpServletRequest mhsr ,Model model) {
		
		if(result.hasErrors()) {
			System.out.println("Form data some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			
			return "addProduct";
		}
		
		if(!productService.addProduct(product)) {
			System.out.println("adding product cannot be done");
		}
		
		if(productService.uploadFile(mhsr,product)) {
			System.out.println("success upload!!");
		}
		else {
			model.addAttribute("fileError", true);
			return "updateProduct";
		}
		
		
		return "redirect:/admin/productInventory"; //products 리스트를 최신화하기 위해 리다이렏트 해준다.
		
	}
	
	@RequestMapping(value="/productInventory/deleteProduct/{id}", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable int id) {
		
		if(productService.deleteProduct(id)) {
			System.out.println("deleting product cannot be done");
		}
		
		return "redirect:/admin/productInventory";
	}
	
	@RequestMapping(value="/productInventory/updateProduct/{id}", method=RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {
		
		Product product = productService.getProductById(id);
		
		model.addAttribute("product", product);
		
		return "updateProduct";
	}
	
	@RequestMapping(value="/productInventory/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(@Valid Product product,BindingResult result, MultipartHttpServletRequest mhsr, Model model) {
		
		if(result.hasErrors()) {
			System.out.println("Form data some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			
			return "updateProduct";
		}
		
		//System.out.println(product);
		if(!productService.updateProduct(product)) {
			System.out.println("updating product cannot be done");
		}
		
		if(productService.uploadFile(mhsr,product)) {
			System.out.println("success upload!!");
		}
		else {
			model.addAttribute("fileError", true);
			return "updateProduct";
		}
		
		return "redirect:/admin/productInventory"; //products 리스트를 최신화하기 위해 리다이렏트 해준다.
		
	}
	
	
	
	

}
