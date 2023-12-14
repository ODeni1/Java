package com.ecommercewebsite.elurre.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommercewebsite.elurre.datatransferobject.ProductDTO;
import com.ecommercewebsite.elurre.global.GlobalData;
import com.ecommercewebsite.elurre.model.Product;
import com.ecommercewebsite.elurre.service.CategoryService;
import com.ecommercewebsite.elurre.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "home";
	}

	@GetMapping("/shop")
	public String getShop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String getShopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		return "shop";
	}


	@GetMapping("/shop/viewproduct/{id}")
	public String getViewProduct(Model model, @PathVariable int id) {
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("productDTO", new ProductDTO());
		return "viewProduct";
	}

	@PostMapping("/shop/viewproduct/{id}")
	public String postProductsAdd(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("productImage")MultipartFile file, @RequestParam("img")String img, Model model) throws IOException {
		Product product = new Product();
		product.setSize(productDTO.getSize());
		product.setQuantity(productDTO.getQuantity());

	    GlobalData.cart.add(product);

		return "/shop/viewproduct/{id}";

	}



}
