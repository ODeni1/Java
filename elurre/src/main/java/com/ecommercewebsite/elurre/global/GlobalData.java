package com.ecommercewebsite.elurre.global;

import java.util.ArrayList;
import java.util.List;

import com.ecommercewebsite.elurre.model.Product;

public class GlobalData {
		public static List<Product> cart;
		static {
			cart = new ArrayList<>();
		}
}
