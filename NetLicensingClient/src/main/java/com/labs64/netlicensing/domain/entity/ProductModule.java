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
package com.labs64.netlicensing.domain.entity;

import java.util.Collection;
import java.util.Map;

/**
 * Product module entity used internally by NetLicensing.
 * <p>
 * Properties visible via NetLicensing API:
 * <p>
 * <b>number</b> - Unique number (across all products of a vendor) that identifies the product module. Vendor can assign
 * this number when creating a product module or let NetLicensing generate one. Read-only after creation of the first
 * licensee for the product.
 * <p>
 * <b>active</b> - If set to false, the product module is disabled. Licensees can not obtain any new licenses for this
 * product module.
 * <p>
 * <b>name</b> - Product module name that is visible to the end customers in NetLicensing Shop.
 * <p>
 * <b>licensingModel</b> - Licensing model applied to this product module. Defines what license templates can be
 * configured for the product module and how licenses for this product module are processed during validation.
 */
public interface ProductModule extends BaseEntity {

    // Methods for working with properties

    String getName();

    void setName(String name);

    String getLicensingModel();

    void setLicensingModel(String licensingModel);

    // Methods for working with custom properties

    @Deprecated
    Map<String, String> getProductModuleProperties();

    // Methods for interacting with other entities

    Product getProduct();

    void setProduct(Product product);

    Collection<LicenseTemplate> getLicenseTemplates();

}
