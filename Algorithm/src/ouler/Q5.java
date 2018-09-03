package ouler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.lang3.StringUtils;
import org.omg.PortableServer.POA;

import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.sun.xml.internal.bind.v2.model.core.ArrayInfo;

import sun.security.pkcs10.PKCS10;
import util.algorithm.number.CpfUtilInt;
import util.algorithm.number.PrimeNumber;
import util.algorithm.number.SpecialNumber;
import util.common.CPFIOUtil;

public class Q5
{
    
    public static void main(String[] args) {
        question44();
    }

    /**
     *  1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45(能被3整除)
     *  1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 = 36(能被3整除)
     *  1 + 2 + 3 + 4 + 5 + 6 + 7 = 28
     *  故直接从7开始
     */
    public static void question41() {
        for (int n = 7654321; n > 2; n -= 2){
            if (SpecialNumber.isPandigital(n) && PrimeNumber.isprime(n)){
                System.err.println(n);
                break;
            }
        }
    }
    
    
    /**
     *  1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45(能被3整除)
     *  1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 = 36(能被3整除)
     *  1 + 2 + 3 + 4 + 5 + 6 + 7 = 28
     *  故直接从7开始
     */
    public static void question42() {
        StringBuffer triangleNumbers = new StringBuffer(",");
        int sum = 0;
        for (int i = 1; i < 25; i ++) {
            sum += i;
            triangleNumbers.append(sum + ",");
        }
        String constr = triangleNumbers.toString();
        System.out.println(constr);
        String filepath = CPFIOUtil.getOulerFilePath("p042_words.txt");
        String fileString = CPFIOUtil.readFile(filepath);
        System.out.println(fileString);
        char[] charArray = fileString.replaceAll("\"", "").toCharArray();
        sum = 0;
        int count = 0;
        for (char ch : charArray){
            if (ch == ','){
                if (constr.indexOf("," + Integer.toString(sum) + ",") > -1){
                    System.out.println(sum);
                    count ++;
                }
                if (sum > 300){
                    throw new IndexOutOfBoundsException();
                }
                sum = 0;
            } else {
                sum += (ch - 64);
            }
        }
        if (constr.indexOf("," + Integer.toString(sum) + ",") > -1){
            System.out.println(sum);
            count ++;
        }
        System.out.println(sum);
        System.out.println(count);
    }
    
    
    
    /**
     * 数字1406357289是一个0到9的数字数字，因为它按某种顺序由数字0到9中的每一个组成，但它也有一个相当有趣的子字符串可分性属性。
     * 设d1为第1位，d2为第2位，依此类推。 这样，我们注意到以下几点： 
     * d2d3d4 = 406可以被2整除   d4一定是偶数
     * d3d4d5 = 063可被3整除      (d3 + d4 + d5) % 3 == 0
     * d4d5d6 = 635可被5整除      d6 : 0,5
     * d5d6d7 = 357可被7整除      
     * d6d7d8 = 572可被11整除 
     * d7d8d9 = 728可被13整除
     * d8d9d10 = 289可被17整除 
     * 使用此属性查找所有0到9个pandigital数字的总和。
     */
    public static void question43() {
        long start = System.currentTimeMillis();
        long sum = 0;
        int d[] = new int[11]; // d[0]弃用
        d[0] = -1;
        int max = 10;
        for (d[1] = 0; d[1] < max; d[1] ++){
            for (d[2] = 0; d[2] < max; d[2] ++){
                if (isrepeat(d, 2)){
                    continue;
                }
                for (d[3] = 0; d[3] < max; d[3] ++){
                    if (isrepeat(d, 3)){
                        continue;
                    }
                    for (d[4] = 0; d[4] < max; d[4] += 2){ // 必须是偶数
                        if (isrepeat(d, 4)){
                            continue;
                        }
                        for (d[5] = 0; d[5] < max; d[5] ++){
                            if (isrepeat(d, 5) || (d[3] + d[4] + d[5]) % 3 != 0){
                                continue;
                            }
                            for (d[6] = 0; d[6] < max; d[6] += 5){  // 0,5
                                if (isrepeat(d, 6)){
                                    continue;
                                }
                                for (d[7] = 0; d[7] < max; d[7] ++){
                                    if (isrepeat(d, 7) || geneNum(d, 5, 7)){
                                        continue;
                                    }
                                    for (d[8] = 0; d[8] < max; d[8] ++){
                                        if (isrepeat(d, 8) || geneNum(d, 6, 11)){
                                            continue;
                                        }
                                        for (d[9] = 0; d[9] < max; d[9] ++){
                                            if (isrepeat(d, 9) || geneNum(d, 7, 13)){
                                                continue;
                                            }
                                            for (d[10] = 0; d[10] < max; d[10] ++){
                                                if (isrepeat(d, 10) || geneNum(d, 8, 17)){
                                                    continue;
                                                }
                                                String str = Arrays.toString(Arrays.copyOfRange(d, 1, 11));
                                                str = StringUtils.strip(str, "[]").replaceAll(", ", "");
//                                                System.out.println(str);
                                                sum += Long.parseLong(str);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("end - start : " + (end - start));
        System.out.println("question43 : " + sum);
    }
    
    private static boolean geneNum(int[] ints, int start, int device) {
        int num = ints[start] * 100 + ints[start+1] * 10 + ints[start+2];
        return num % device != 0;
    }
    
    public static boolean isrepeat(int[] ints, int n) {
        for (int i = 1; i < n; i++){
            if (ints[n] == ints[i]){
                return true;
            }
        }
        return false;
    }
    
    
    
    
    
    
    
    
    public static void question44(){
    	isfuncp(5482660);
    	int maxlen = 2000_000;
    	long[] ps = new long[maxlen];
    	for (int i = 1; i < maxlen; i ++) {
    		ps[i] = funcp(i);
    		if (!isfuncp(ps[i])){
    			System.out.println(ps[i]);
    		}
    	}
    	
    	long maxpi = ps[maxlen-1] - ps[maxlen-2];
    	maxpi = 5482661;
    	
    	lbl : for (int i = 1; ps[i] < maxpi; i ++) {
    		long pi = ps[i];
    		for (int j = 1; j < maxlen-2; j ++) {
    			long pk = pi + ps[j];
    			
    			if (isfuncp(pk)){
    				long pL = pk + ps[j];
    				if (isfuncp(pL)) {
    					System.out.println(i + " " + ps[i]);
    					System.out.println(j + " " + ps[j]);
    					System.out.println( pk + " " + pL);
    					break lbl;
    				}
    			} else {
    				continue;
    			}
    			
    		}
    	}
    	System.out.println("end");
    }
    
    public static long funcp(long n) {
    	return n * (3 * n - 1) / 2;
    }

    public static boolean isfuncp(long pn) {
    	if (pn == 1) {
    		return true;
    	}
    	int n = (int)Math.sqrt(pn * 2 / 3);
    	while(n > 0) {
    		n ++;
    		long funcp = funcp(n);
    		if (funcp == pn){
    			return true;
    		} else if (funcp > pn) {
    			break;
    		}
    	}
    	return false;
    }
    
    
    
    
    /**
     * Triangle	 	    Tn=n(n+1)/2	 	    1, 3, 6,  10, 15, ...
     * Pentagonal	 	Pn=n(3n -1)/2	 	1, 5, 12, 22, 35, ...
     * Hexagonal	 	Hn=n(2n -1)	 	    1, 6, 15, 28, 45, ...
     * 
     * It can be verified that T285 = P165 = H143 = 40755.
     */
    public static void question45(){
    	BigInteger verify;
    	long i3 = 285,i5 = 1,i6 = 1;
    	BigInteger t3,t5,t6;
    	verify = new BigInteger("40755");
    	int flag = 0;
    	t3 = t5 = t6 = new BigInteger("10");
    	while (true) {
    		if (i3 > 300000){
    			System.out.println(t3);
    			break;
    		}

			while ((flag = t3.compareTo(verify)) < 0) {
				t3 = BigInteger.valueOf(++ i3 * (i3 + 1) / 2);
			}
			if (flag > 0) {
				verify = t3;
			} else if (flag == 0){
				t3 = verify;
			}
			
			while ((flag = t5.compareTo(verify)) < 0) {
				t5 = BigInteger.valueOf(++ i5 * (3 * i5 - 1) / 2);
			}
			if (flag > 0) {
				verify = t5;
			} else if (flag == 0){
				t5 = verify;
			}
			
			while ((flag = t6.compareTo(verify)) < 0) {
				t6 = BigInteger.valueOf(++ i6 * (2 * i6 - 1));
			}
			if (flag > 0) {
				verify = t6;
			} else if (flag == 0){
				t6 = verify;
			}
			
			if (t3 == t5 && t3 == t6){
				System.out.println(t3);
				System.out.println(i3);
				System.out.println(i5);
				System.out.println(i6);
				break;
			}
		}
    }
    
    
    public static void question46(){
    	List<Integer> primes = new ArrayList<>();
    	// 排除2， 因为奇合数减去2，  为奇数， 无法写成两倍数
    	for (int n = 3; n < 10000; n += 2) {
    		if (PrimeNumber.isprime(n)) {
    			primes.add(n);
    		} else if (!question46_1(primes, n)){
    			System.out.println(n);
    			break;
    		}
    	}
    }
    
    public static boolean question46_1(List<Integer> primes, int num){
		int pown;
		int sqrt;
    	for (Integer prime : primes) {
    		pown = (num - prime) / 2;
    		sqrt = (int)Math.sqrt(pown);
    		if ((int)Math.pow( sqrt, 2) == pown) {
    			return true;
    		}
		}
    	return false;
    }
    
    
    /**47、不同的质因数
     * 首次出现连续两个数均有两个不同的质因数是：
     * 14 = 2 × 7
     * 15 = 3 × 5
     * 首次出现连续三个数均有三个不同的质因数是：
     * 644 = 22 × 7 × 23
     * 645 = 3 × 5 × 43
     * 646 = 2 × 17 × 19
     * 首次出现连续四个数均有四个不同的质因数时，其中的第一个数是。
     * 
     */
    public static void question47(){
    	final int count = 4;
    	int index = 0;
    	int primes[] = new int[count]; 
    	for (int n = 2; n < 1000000; n ++) {
    		int i = CpfUtilInt.getPrimeDivisorCount(n);
    		if (i == count) {
    			if ((index == 0) || (index >= 1 && n == primes[index - 1] + 1)) {
        			primes[index] = n;
        			index ++;
    			} else {
    				index = 0;
    			}
    		} else {
				index = 0;
			}
    		if (index == count){
    			System.out.println(Arrays.toString(primes));
    			break;
    		}
    	}
    	System.out.println("end");
    }
    
    

    public static void question48(){
    	long limit = 10_000_000_000L;
    	long last10sum = 0;
    	long last10pow;
    	for (int n = 1; n < 1000; n++) {
    		last10pow = 1;
    		for (int m = n; m > 0; m --){
    			last10pow *= n;
    			last10pow %= limit;
    		}
    		last10sum += last10pow;
    		last10sum %= limit;
    	}
    	System.out.println(last10sum);
    }
    
    
    
    /**
     * 
     */
    public static void question49(){
    	Map<Integer, List<Integer>> map = new HashMap<>(5);
    	map.put(1, new ArrayList<>());
    	map.put(3, new ArrayList<>());
    	map.put(5, new ArrayList<>());
    	map.put(7, new ArrayList<>());
    	map.put(9, new ArrayList<>());
    	int cnt = 0;
    	for (int i = 1001; i < 10000; i ++) {
    		if (PrimeNumber.isprime(i)) {
    			map.get(i % 10).add(i);
    			cnt ++;
    		}
    	}
    	System.out.println(map);
    	System.out.println(cnt);
    }
    
    

    /**
     * 
     */
    public static void question49_2(){
//    	Map<Integer, List<Integer>> map = new HashMap<>(5);
    	int cnt = 0;
    	int arr[][] = {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
    	int ijk[] = new int[3];
    	List<Integer> list = new ArrayList<>(6);
    	for (int r1 = 1; r1 <= 9; r1 += 2) {
    		for (ijk[0] = 0; ijk[0] <= 9; ijk[0] ++) {
    			for (ijk[1] = ijk[0] + 1; ijk[1] <= 9; ijk[1] ++) {
    				for (ijk[2] = ijk[1] + 1; ijk[2] <= 9; ijk[2] ++) {
    					for (int i = 0, len = arr.length; i< len; i++){
    						int trr[] = arr[i];
    						if (ijk[trr[0]] > 0){
    							int num = ijk[trr[0]] * 1000 + ijk[trr[1]] * 100 + ijk[trr[2]] * 10 + r1;
    							if (PrimeNumber.isprime(num)){
    								list.add(num);
    							}
    						}
    					}
//    					if (list.size() >= 3) {
//    						System.out.println(list);
//    					}
    					switch(list.size()) {
						case 3: {
							if (list.get(1) * 2 == list.get(0) + list.get(2)) {
								System.err.println(list);
							}
							break;
						}
						case 4: {
							if (list.get(1) * 2 == list.get(0) + list.get(2)
							    || list.get(1) * 2 == list.get(0) + list.get(3)
							    || list.get(2) * 2 == list.get(0) + list.get(3)
							    || list.get(2) * 2 == list.get(1) + list.get(3)) {
								System.err.println(list);
							}
							break;
						}
						case 5: {
							if (list.get(1) * 2 == list.get(0) + list.get(2)
							    || list.get(1) * 2 == list.get(0) + list.get(3)
							    || list.get(1) * 2 == list.get(0) + list.get(4)
							    
							    || list.get(2) * 2 == list.get(0) + list.get(3)
							    || list.get(2) * 2 == list.get(0) + list.get(4)
							    || list.get(2) * 2 == list.get(1) + list.get(3)
							    || list.get(2) * 2 == list.get(1) + list.get(4)
							    
							    || list.get(3) * 2 == list.get(0) + list.get(4)
							    || list.get(3) * 2 == list.get(1) + list.get(4)
							    || list.get(3) * 2 == list.get(2) + list.get(4)
							    ) {
								System.err.println(list);
							}
						}
    					}
						list.clear();
    				}
    			}
    		}
    	}
    	System.out.println(cnt);
    }
    
    
    public static void question50() {
    	int limitNum = 1000_000;
    	int[] primes = new int[100000];
    	int plimit = 0;
    	for (int i = 0; i < limitNum; i ++) {
    		if (PrimeNumber.isprime(i)) {
    			primes[plimit ++] = i;
    		}
    	}
//    	plimit --;
    	
    	
    	
    	System.out.println(Arrays.toString(primes));
    	
    	int maxlength = 0;
    	int maxsum = 0;
    	int maxstart = 0;
    	for (int start = 0; start < plimit; start ++) {
    		for (int i = start, sum = 0, len = 0; i < plimit; i ++) {
    			sum += primes[i];
    			len ++;
    			if (sum >= limitNum){
    				break;
    			} else if (PrimeNumber.isprime(sum)) {
    				if (maxlength < len) {
    					maxlength = len;
    					maxsum = sum;
    					maxstart = start;
    				}
    			}
    		}
    	}
    	System.out.println(maxstart);
    	System.out.println(maxlength);
    	System.out.println(maxsum);
    	
//    	for (int i = maxstart, len = maxlength, sum = 0; ; i ++){
//    		sum += primes[i];
//    		System.out.print(primes[i] + "  ");
//    		if (-- len == 0){
//    			System.out.println();
//    			System.out.println(sum);
//    			break;
//    		}
//    	}
    	
    	
    	
    }
    


}
