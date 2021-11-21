package pers.kaigian.learning.designpattern;

/**
 * @Author BrianHu
 * @Create 2021-04-21 14:23
 **/
public class FactoryMethod {
	public static void main(String[] args) {
		Factory factory = new FactoryA();
		Product product = factory.createProduct();
		product.method();
	}
}

abstract class Factory {
	public abstract Product createProduct();

}

class FactoryA extends Factory {
	@Override
	public Product createProduct() {
		return new ProductA();
	}
}

class FactoryB extends Factory {
	@Override
	public Product createProduct() {
		return new ProductB();
	}
}

class Product{
	public void method(){

	}
}

class ProductA extends Product{
	@Override
	public void method() {
		System.out.println("A");
	}
}

class ProductB extends Product{
	@Override
	public void method() {
		System.out.println("B");
	}
}

