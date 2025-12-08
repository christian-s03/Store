package service;

import Exceptions.NotAvailableException;

public interface CommandLine {
    void viewProducts();
    void addProductToCart();
    void removeProductFromCart();
    void setUpPc() throws NotAvailableException;
    void setUpPhone() throws NotAvailableException;
    void viewCart();
    void checkout();
}