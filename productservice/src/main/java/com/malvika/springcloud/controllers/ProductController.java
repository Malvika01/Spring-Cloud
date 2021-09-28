package com.malvika.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.malvika.springcloud.model.Coupon;
import com.malvika.springcloud.model.Product;
import com.malvika.springcloud.repos.ProductRepository;
import com.malvika.springcloud.restclient.CouponClient;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/productapi")
public class ProductController {

	@Autowired
	CouponClient couponClient;

	@Autowired
	ProductRepository repo;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	@Retry(name = "product-api", fallbackMethod = "handleError")
	public Product create(@RequestBody Product product) {
		System.out.println("Retry");
		Coupon coupon = couponClient.getCoupon(product.getCouponcode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);
	}

	public Product handleError(Product product, Exception exception) {
		System.out.println("Inside Handle Error");
		return product;
	}

}
