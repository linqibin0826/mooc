package com.linqibin.commonutils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 随机生成验证码
 * @param
 * @return
 * @author linqibin&youmei
 * @creed: ProjectForSDDM
 * @date 2020/10/29 23:31
 */
public class RandomUtil {

	private static final Random RANDOM = new Random();

	private static final DecimalFormat DECIMAL_FORMAT_FOUR = new DecimalFormat("0000");

	private static final DecimalFormat DECIMAL_FORMAT_SIX = new DecimalFormat("000000");

	public static String getFourBitRandom() {
		return DECIMAL_FORMAT_FOUR.format(RANDOM.nextInt(10000));
	}

	public static String getSixBitRandom() {
		return DECIMAL_FORMAT_SIX.format(RANDOM.nextInt(1000000));
	}

	/**
	 * 给定数组，抽取n个数据
	 * @param list
	 * @param n
	 * @return
	 */
	public static ArrayList getRandom(List list, int n) {

		Random random = new Random();

		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

		// 生成随机数字并存入HashMap
		for (int i = 0; i < list.size(); i++) {

			int number = random.nextInt(100) + 1;

			hashMap.put(number, i);
		}

		// 从HashMap导入数组
		Object[] robjs = hashMap.values().toArray();

		ArrayList r = new ArrayList();

		// 遍历数组并打印数据
		for (int i = 0; i < n; i++) {
			r.add(list.get((int) robjs[i]));
			System.out.print(list.get((int) robjs[i]) + "\t");
		}
		System.out.print("\n");
		return r;
	}
}
