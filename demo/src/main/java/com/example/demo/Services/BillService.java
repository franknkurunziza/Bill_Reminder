package com.example.demo.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Bill;
import com.example.demo.Repository.BillRepo;

@Service
public class BillService {
	
	@Autowired
	private BillRepo brepo;
	

	
	public Bill createBill(Bill bill) throws ParseException {
		String dateConvert=bill.getDate();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		bill.setPayDate(formatter.parse(dateConvert));
		return brepo.save(bill);
	}
	
	public List<Bill> findAll(){
		return brepo.findAll();
	}
	
	public Bill findBillbyId(Long id) {
		return this.brepo.findById(id).orElse(null);
	}
	
	public void deleteBill(Bill bill) {
		brepo.delete(bill);
	}
	
	public Bill updateBill(Bill bill)  {
//		Bill bill= brepo.findById(id).orElse(null);
//		bill.setName(name);
//		bill.setAmount(amount);
		return brepo.save(bill);
	}
	
	public void paybill(Bill bill) {
		bill.setPayed(true);
		brepo.save(bill);
	}
}
