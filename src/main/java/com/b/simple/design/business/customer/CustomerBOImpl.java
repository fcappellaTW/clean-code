package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

public class CustomerBOImpl implements CustomerBO {

	@Override
	public Amount getCustomerProductsSum(List<Product> products)
			throws DifferentCurrenciesException {
		if (products.size() == 0)
			return new AmountImpl(BigDecimal.ZERO, Currency.EURO);

		validateAllProductsHaveSameCurrency(products);

		return sumAllProductsValue(products);
	}

	private void validateAllProductsHaveSameCurrency(List<Product> products)
			throws DifferentCurrenciesException {

		Currency firstProductCurrency = products.get(0).getAmount().getCurrency();

		boolean allProductsHaveSameCurrency = products.stream()
				.allMatch(product -> product.getAmount().getCurrency().equals(firstProductCurrency));

		if (!allProductsHaveSameCurrency) {
			throw new DifferentCurrenciesException();
		}
	}

	private Amount sumAllProductsValue(List<Product> products) {

		Currency defaultCurrency = products.get(0).getAmount().getCurrency();

		BigDecimal totalValue = products.stream()
				.map(Product::getAmount).map(Amount::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);

		return new AmountImpl(totalValue, defaultCurrency);
	}
}