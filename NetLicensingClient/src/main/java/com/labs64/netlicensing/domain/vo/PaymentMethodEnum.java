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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This enum defines payment methods supported by NetLicensing.
 */
public enum PaymentMethodEnum {

	/**
	 * "null" payment method is a placeholder for undefined/unset value.
	 */
	NULL,

	PAYPAL,

	PAYPAL_SANDBOX,

	STRIPE,

	STRIPE_TESTING;

	private static final Logger logger = LoggerFactory.getLogger(PaymentMethodEnum.class);

	public static PaymentMethodEnum parseString(final String paymentMethod) {
		if (StringUtils.isBlank(paymentMethod)) {
			return null;
		}

		try {
			return PaymentMethodEnum.valueOf(paymentMethod);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}
