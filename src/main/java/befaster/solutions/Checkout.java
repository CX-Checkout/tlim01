package befaster.solutions;

import java.util.HashMap;

public class Checkout {
	public static Integer checkout(String skus) {
		boolean flag = true;
		int total = 0;
		
		int num_of_A = 0;
		int num_of_B = 0;
		int num_of_C = 0;
		int num_of_D = 0;
		
		HashMap<String, Integer> costs = new HashMap<>();
		
		costs.put("A", 50);
		costs.put("B", 30);
		costs.put("C", 20);
		costs.put("D", 15);
		
		for(char c: skus.toCharArray()) {
			flag = true;
			if(c == 'A') {
				num_of_A++;
			}
			else if(c == 'B') { num_of_B++;}
			else if(c == 'C') { num_of_C++;}
			else if(c == 'D') { num_of_D++;}
			else {
				flag = false;
				break;
			}
		}
		
		total += (num_of_A/3) * 130;
		total += (num_of_A%3) * costs.get("A");
		
		total += (num_of_B/2) * 45;
		total += (num_of_B%2) * costs.get("B");
		
		total += num_of_C * costs.get("C");
		total += num_of_D * costs.get("D");
		
		// illegal input is -1
		return flag ? total: -1;
	}
}
