package com.b.simple.design.business.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;
import com.b.simple.design.model.customer.ProductImpl;
import com.b.simple.design.model.customer.ProductType;

public class CustomerBOTest {

	private CustomerBO customerBO = new CustomerBOImpl();

	private static final String PRODUCT_15_PRICE = "5.0";
	private static final String PRODUCT_20_PRICE = "6.0";
	private static final String FINAL_VALUE = "11.0";
	private static final Currency DEFAULT_CURRENCY = Currency.EURO;
	private static final Currency DIFFERENT_CURRENCY = Currency.INDIAN_RUPEE;

	@Test
	public void customerProductSum_TwoProductsWithSameCurrency()
			throws DifferentCurrenciesException {

		Amount[] amounts = {
			new AmountImpl(new BigDecimal(PRODUCT_15_PRICE), DEFAULT_CURRENCY),
			new AmountImpl(new BigDecimal(PRODUCT_20_PRICE), DEFAULT_CURRENCY)
		};

		List<Product> products = createProductsBasedOnAmounts(amounts);

		Amount expectedAmount = new AmountImpl(new BigDecimal(FINAL_VALUE), DEFAULT_CURRENCY);

		assertAmount(customerBO.getCustomerProductsSum(products), expectedAmount);
	}

	@Test
	public void customerProductSum_TwoProductsWithDifferentCurrencies() {

		Amount[] amounts = {
			new AmountImpl(new BigDecimal(PRODUCT_15_PRICE), DEFAULT_CURRENCY),
			new AmountImpl(new BigDecimal(PRODUCT_20_PRICE), DIFFERENT_CURRENCY)
		};

		List<Product> products = createProductsBasedOnAmounts(amounts);

		assertThrows(DifferentCurrenciesException.class, () -> customerBO.getCustomerProductsSum(products));
	}

	@Test
	public void customerProductSum_NoProducts() throws DifferentCurrenciesException {
		Amount actualAmount = customerBO.getCustomerProductsSum(new ArrayList<Product>());
		Amount expectedAmount = new AmountImpl(BigDecimal.ZERO, DEFAULT_CURRENCY);

		assertAmount(actualAmount, expectedAmount);
	}

	private List<Product> createProductsBasedOnAmounts(Amount[] amounts) {
		return Arrays.stream(amounts).map(amount -> new ProductImpl(100, "Product", ProductType.BANK_GUARANTEE, amount)).collect(Collectors.toList());
	}

	private void assertAmount(Amount actualAmount, Amount expectedAmount) {
		assertEquals(expectedAmount.getCurrency(), actualAmount.getCurrency());
		assertEquals(expectedAmount.getValue(), actualAmount.getValue());
	}

}