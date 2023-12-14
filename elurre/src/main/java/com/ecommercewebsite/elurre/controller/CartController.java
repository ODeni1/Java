package com.ecommercewebsite.elurre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommercewebsite.elurre.datatransferobject.ProductDTO;
import com.ecommercewebsite.elurre.global.GlobalData;
import com.ecommercewebsite.elurre.model.Product;
import com.ecommercewebsite.elurre.service.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;


	@GetMapping("/addToCart/{id}")
	public String getAddToCart(@PathVariable int id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";

	}

	@GetMapping("/cart")
	public String getCart(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);

	    model.addAttribute("productDTO", new ProductDTO());

		return "cart";

	}

	@GetMapping("/cart/removeItem/{index}")
	public String getDeleteItem(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
}
