package befaster.solutions;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

public class CheckoutTest {
	
	int A = 50;
	int B = 30;
	int C = 20;
	int D = 15;
	
	int _3A = 130;
	int _2B = 45;

	@Test
	public void getCheckoutValue() {
		assertThat(Checkout.checkout("A"), equalTo(50));
		assertThat(Checkout.checkout("B"), equalTo(30));
		assertThat(Checkout.checkout("C"), equalTo(20));
		assertThat(Checkout.checkout("D"), equalTo(15));
		
		assertThat(Checkout.checkout("AB"), equalTo(A+B));
		assertThat(Checkout.checkout("AAB"), equalTo(A+A+B));
		
		assertThat(Checkout.checkout("AAAB"), equalTo(_3A+B));
		assertThat(Checkout.checkout("AAABB"), equalTo(_3A+_2B));
		
		assertThat(Checkout.checkout("AAABBA"), equalTo(_3A+_2B+A));
		assertThat(Checkout.checkout("AAABAB"), equalTo(_3A+_2B+A));
		assertThat(Checkout.checkout("AAAABB"), equalTo(_3A+_2B+A));
		
		assertThat(Checkout.checkout(""), equalTo(0));
		
		assertThat(Checkout.checkout("AxA"), equalTo(-1));
	}

}
