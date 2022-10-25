package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

import java.math.BigDecimal;
import java.util.List;

public class MyCustomerBOImplRefactored implements CustomerBO {

	@Override
	public Amount getCustomerProductsSum(List<Product> products)
			throws DifferentCurrenciesException {

		if (products.size() == 0)
			return new AmountImpl(BigDecimal.ZERO, Currency.EURO);

		if (!isSameCurrency(products)) {
			throw new DifferentCurrenciesException();
		}

		return new AmountImpl(getSum(products), products.get(0).getAmount()
			.getCurrency());
	}

	private BigDecimal getSum(List<Product> products) {
		BigDecimal sum = BigDecimal.ZERO;

		for (Product product : products) {
			sum = sum.add(product.getAmount().getValue());
		}

		return sum;
	}

	private boolean isSameCurrency(List<Product> products) {
		Currency currency = products.get(0).getAmount()
				.getCurrency();

		for (Product product : products) {
			if (!product.getAmount().getCurrency().equals(currency)) {
				return false;
			}
		}

		return true;
	}
}