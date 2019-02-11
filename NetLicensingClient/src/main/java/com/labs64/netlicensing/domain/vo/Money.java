/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.labs64.netlicensing.domain.vo;

import java.math.BigDecimal;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

import com.labs64.netlicensing.domain.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds amount of money with associated currency.
 * <p>
 * Consider more comprehensive implementation using one of:
 * <ul>
 * <li>http://joda-money.sourceforge.net/</li>
 * <li>http://java.net/projects/javamoney</li>
 * </ul>
 */
public class Money {

	private static final Logger logger = LoggerFactory.getLogger(Money.class);

	private BigDecimal amount;

	private String currencyCode;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(final String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public static Money convertPrice(final String rawPrice, final String rawCurrency) {
		final Money target = new Money();
		if (StringUtils.isNotBlank(rawPrice)) {
			try {
				target.setAmount(DatatypeConverter.parseDecimal(rawPrice));
			} catch (final NumberFormatException e) {
				logger.error(e.getMessage(), e);
				throw new IllegalArgumentException(new StringBuilder().append("'").append(Constants.PRICE)
						.append("' format is not correct, expected '0.00' format").toString());
			}
			if (StringUtils.isNotBlank(rawCurrency)) {
				if (Currency.parseValueSafe(rawCurrency) == null) {
					throw new IllegalArgumentException("Unsupported currency!");
				}
				target.setCurrencyCode(rawCurrency);
			} else {
				throw new IllegalArgumentException(new StringBuilder().append("'").append(Constants.PRICE)
						.append("' field must be accompanied with the '").append(Constants.CURRENCY).append("' field")
						.toString());
			}
		} else { // 'price' is not provided
			if (StringUtils.isNotBlank(rawCurrency)) {
				throw new IllegalArgumentException(new StringBuilder().append("'").append(Constants.CURRENCY)
						.append("' field can not be used without the '").append(Constants.PRICE).append("' field")
						.toString());
			}
		}
		return target;
	}

}
