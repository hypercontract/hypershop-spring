package org.hypercontract.hypershop.product.mock;

import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.data.repository.Repository;

interface ProductDataInitializerRepository extends Repository<Product, Id<Product>> {

    Product save(Product product);

}
