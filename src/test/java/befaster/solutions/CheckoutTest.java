package befaster.solutions;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

public class CheckoutTest {
	
	int A = 50;
	int B = 30;
	int C = 20;
	int D = 15;
	int E = 40;
	
	int _3A = 130;
	int _2B = 45;
	int _5A = 200;

	@Test
	public void getCheckoutValue() {
		assertThat(Checkout.checkout("A"), equalTo(50));
		assertThat(Checkout.checkout("B"), equalTo(30));
		assertThat(Checkout.checkout("C"), equalTo(20));
		assertThat(Checkout.checkout("D"), equalTo(15));
		assertThat(Checkout.checkout("E"), equalTo(40));
		
		// 5A is 100!
		//assertThat(Checkout.checkout("AAAAA"), equalTo(_5A));
		// 3A is 130!
		assert(!Checkout.doesExistAFactorOf5(3));
		assertThat(Checkout.checkout("AAA"), equalTo(_3A));
		
		// 6A could be 130 + 130 = 260
		// 6A could be 200 + 50 = 250
		// favouring the customer should choose 250
		assertThat(Checkout.checkout("AAA" + "AAA"), equalTo(_5A + A));
		
		// 8As
		// could be 5 + 3 == 130 + 200 == 330
		// could be 3 + 3 + 2 == 130 + 130 + 20 == 250
		assertThat(Checkout.checkout("AAAA" + "AAAA"), equalTo(330));
		assertThat(Checkout.checkout("AAAAAAAAA"), equalTo(380));
		assertThat(Checkout.checkout("AAAAAEEBAAABB"), equalTo(445));
		
		// how cool is that, if you take 2E you get a B free
		assertThat(Checkout.checkout("EB"), equalTo(E + B));
		assertThat(Checkout.checkout("EE"), equalTo(E + E));
		assertThat(Checkout.checkout("EEBB"), equalTo(E + E + B - B));
		assertThat(Checkout.checkout("EBEBB"), equalTo(E + E + B + B - B));
		
		assertThat(Checkout.checkout("AAAB"), equalTo(_3A + B));
		assertThat(Checkout.checkout("AAABB"), equalTo(_3A + _2B));
		
		assertThat(Checkout.checkout("AAABBA"), equalTo(_3A + _2B + A));
		assertThat(Checkout.checkout("AAABAB"), equalTo(_3A + _2B + A));
		assertThat(Checkout.checkout("AAAABB"), equalTo(_3A + _2B + A));
		
		assertThat(Checkout.checkout(""), equalTo(0));
		
		assertThat(Checkout.checkout("AxA"), equalTo(-1));
	}

}
