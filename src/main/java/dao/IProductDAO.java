package dao;

import model.Product;

public interface IProductDAO
{
    // IV
    Product saveProduct(Product product);

    // V
    Product findProduct(int id);
}