package cn.antigenmhc.otaku.service.sms.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 17:51
 * @Version: 1.0
 **/
public class RandomUtils {

	private static final Random RANDOM = new Random();
	private static final DecimalFormat FOUR_DF = new DecimalFormat("0000");
	private static final DecimalFormat SIX_DF = new DecimalFormat("000000");

	public static String getFourBitRandom() {
		return FOUR_DF.format(RANDOM.nextInt(10000));
	}

	public static String getSixBitRandom() {
		return SIX_DF.format(RANDOM.nextInt(1000000));
	}

	/**
	 * 给定数组，抽取 n 个数据
	 */
	public static List<Object> getRandom(List<Object> list, int n) {

		HashMap<Object, Object> hashMap = new HashMap<>(10);

		//生成随机数字并存入HashMap
		for (int i = 0; i < list.size(); i++) {
			int number = RANDOM.nextInt(100) + 1;
			hashMap.put(number, i);
		}

		//从HashMap导入数组
		ArrayList<Object> r = new ArrayList<>(hashMap.values());
		return r.subList(0,n);
	}
}
