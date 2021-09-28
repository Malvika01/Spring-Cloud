package com.malvika.springcloud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malvika.springcloud.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);


}
