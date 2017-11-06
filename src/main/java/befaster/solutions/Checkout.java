package befaster.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Checkout {
	
	static HashMap<String, Integer> costs = new HashMap<>();
	static HashMap<String, Integer> counts = new HashMap<>();
	static HashMap<String, Integer> specials = new HashMap<>();
	static HashMap<String, List<Integer>> available = new HashMap<>();
	
	static boolean DEBUG = true;
	static int NOSPECIAL = -1;
	
	public static Integer checkout(String skus) {
		boolean flag = true;
		int total = 0;
		
		costs.put("A", 50);
		costs.put("B", 30);
		costs.put("C", 20);
		costs.put("D", 15);
		costs.put("E", 40);
		
		counts.put("A", 0);
		counts.put("B", 0);
		counts.put("C", 0);
		counts.put("D", 0);
		counts.put("E", 0);
		
		specials.put("3A", 130);
		specials.put("5A", 200);
		specials.put("2B", 45);
		
		available.put("A", Arrays.asList(2, 3));
		available.put("B", Arrays.asList(2));
		
		for(char c: skus.toCharArray()) {
			flag = true;
			if(isValid(c)) {
				increment(c);
			} else {
				flag = false;
				break;
			}
		}
		
		// how cool is that, if you take 2E you get a B free

		if(counts.get("B") >= counts.get("E") / 2) {
			counts.put("B", counts.get("B") - counts.get("E") / 2);
		}
		
		for(String key: counts.keySet()) {
			total += applySpecials(key);
		}
		
		if(DEBUG) {
			for(String key: counts.keySet()) {
				System.out.println(key + ":" + counts.get(key));
			}
		}
		
		// illegal input is -1
		return flag ? total: -1;
	}
	
	public static int applySpecials(String sku) {
		
		int k = NOSPECIAL;
		List<Integer> i = available.get(sku);
		if(i == null) {
			// do nothing
		} else if(i.size() == 1) {
			k = i.get(0);
		}
		
		if(sku.equals("A") && isPresent("A")) {
			int amount = 0;
			while(isPresent("A")) {
				System.out.println("A CountDown:" + counts.get("A"));
				System.out.println("A%5:" + counts.get("A") % 5);
				System.out.println("A%3:" + counts.get("A") % 3);
				while(counts.get("A") % 5 == 0 && isPresent("A")) {
					System.out.println("adding special");
					amount += specials.get("5A");
					System.out.println("amount:" + amount);
					for(int j = 0; j < 5; j++) 
							decrement("A");
					System.out.println("As:" + counts.get("A"));
					break;
				}
			
				while(counts.get("A") % 3 == 0 && isPresent("A")
						&& doesExistAFactorOf5(counts.get("A"))) {
					amount += specials.get("3A");
					for(int j = 0; j < 3; j++) 
						decrement("A");
					break;
				}
				
				if(isPresent("A")) {
					amount += costs.get("A");
					decrement("A");
				}
				
//				if(counts.get("A") % 5 != 0 && counts.get("A") > 5) {
//					amount += costs.get("A");
//				} else if(counts.get("A") % 3 != 0 && counts.get("A") > 3) {
//					amount += costs.get("A");
//					decrement("A");
//				} else if(counts.get("A") < 3) {
//					amount += costs.get("A");
//					decrement("A");
//				}
				
				System.out.println("A%5:" + counts.get("A") % 5);
				System.out.println("A%3:" + counts.get("A") % 3);
				
//				if(counts.get("A") % 5 != 0) {
//					amount += costs.get("A");
//					decrement("A");
				System.out.println("As after individual:" + counts.get("A"));
				System.out.println("amount for As after individual:" + amount);
//				} else if(counts.get("A") % 3 != 0) {
//					amount = costs.get("A");
//					decrement("A");
//				} else if(counts.get("A") % 3 != && counts.get("A") % 3 != 0) {
//					amount += costs.get("A");
//					decrement("A");
//				}
			}
			
			return amount;
		}
		
		if(sku.equals("B")) 
			return applySpecials("B", k);
		if(sku.equals("C")) 
			return applySpecials("C", k);
		if(sku.equals("D")) 
			return applySpecials("D", k);
		if(sku.equals("E")) 
			return applySpecials("E", k);
		return 0;
	}
	
	public static boolean doesExistAFactorOf5(int count) {
		while(count > 0) {
			if(count % 5 == 0) return true;
			if(count == 0) return false;
			count --;
		}
		
		return false;
	}
	
	public static int applySpecials(String sku, int count) {
		int amount = 0;
		String key = count + sku; // eg 2B
		
		if(specials.get(key) != null) {
			amount += (counts.get(sku) / count) * specials.get(key);
			amount += (counts.get(sku) % count) * costs.get(sku);
		} else {
			amount += counts.get(sku) * costs.get(sku);
		}
		return amount;
	}
	
	public static void increment(char sku) {
		String s = String.valueOf(sku);
		counts.put(s, counts.get(s) + 1);
	}
	
	public static void decrement(char sku) {
		decrement(String.valueOf(sku));
	}
	
	public static void decrement(String sku) {
		counts.put(sku, counts.get(sku) - 1);
	}
	
	public static boolean isPresent(String sku) {
		return counts.get(sku) > 0;
	}
	
	public static boolean isValid(char sku) {
		return (sku == 'A' ||
				sku == 'B' ||
				sku == 'C' ||
				sku == 'D' ||
				sku == 'E');
	}
}
