package com.fh.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * stringUtils类 是用来对字符串进行处理的类
 */
public abstract class StringUtils {
	private static DecimalFormat df = new DecimalFormat("##,###.00");

	private static NumberFormat nf = NumberFormat.getInstance();

	public static boolean isEmpty(String value) {
		int strLen;
		if ((value == null) || ((strLen = value.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(value.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length < 1) {
			return false;
		}
		int i = 0;
		if ((length > 1) && (chars[0] == '-')) {
		}
		for (i = 1; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	public static final int toNumber(final String str) {

		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static final int toNumber(final String str, int defaultValue) {

		if (isEmpty(str)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public static final long toLongNumber(final String str, long defaultValue) {

		if (isEmpty(str)) {
			return defaultValue;
		}
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public static final Boolean toBoolean(final String str, Boolean defaultValue) {
		if (isNotEmpty(str)) {
			return Boolean.parseBoolean(str);
		}
		return defaultValue;
	}

	public final boolean isNumber(String str) {

		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public final boolean isFloat(String str) {
		try {
			Float.parseFloat(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public final boolean isDouble(String str) {

		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String formatDouble(double d) {

		return df.format(d);
	}

	public static String formatInt(int d) {

		return nf.format(d);
	}

	public static String formatInt(double d) {

		return nf.format(d);
	}

	public static final String[] toLowerCaseWordArray(String s) {

		if (s == null || s.length() == 0)
			return new String[0];
		StringTokenizer stringtokenizer = new StringTokenizer(s, " ,\r\n.:/\\+");
		String as[] = new String[stringtokenizer.countTokens()];
		for (int i = 0; i < as.length; i++)
			as[i] = stringtokenizer.nextToken().toLowerCase();

		return as;
	}

	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if ((values == null) || (values.length == 0)) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	public static String stripNonValidXMLCharacters(String input) {
		if ((input == null) || ("".equals(input))) {
			return "";
		}
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			if ((current == '\t') || (current == '\n') || (current == '\r') || ((current >= ' ') && (current <= 55295))
					|| ((current >= 57344) && (current <= 65533)) || ((current >= 65536) && (current <= 1114111))) {
				out.append(current);
			}
		}
		return out.toString();
	}

	public static boolean isMobile(String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))[0-9]{8}$");

		Matcher match = pattern.matcher(mobile);
		return match.matches();
	}

	public static boolean isDate(String str) {

		boolean bValid = true;
		boolean bl = false;

		if (str != null) {
			try {
				if (str.length() == 10) {
					for (int i = 0; i < 10; i++) {
						String sTem = Integer.toString(i);
						if (str.endsWith(sTem)) {
							bl = true;
						}
					}

					if (bl) {
						DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
						formatter.setLenient(false);
						formatter.parse(str);
					} else {
						return false;
					}
				} else {
					return false;
				}
			} catch (ParseException e) {
				bValid = false;
			}
		} else {
			bValid = false;
		}

		return bValid;
	}

	/**
	 * email address validator
	 * 
	 * @param email
	 * @return true | false
	 * 
	 *         http://www.ex-parrot.com/pdw/Mail-RFC822-Address.html
	 */
	public static boolean isEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}

		Pattern pattern = Pattern.compile(
				"(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");

		Matcher match = pattern.matcher(email);
		return match.matches();
	}

	public static List<String[]> splitArray(String[] strs, int pageSize) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < strs.length; i++) {
			list.add(strs[i]);
		}

		List<String[]> sList = new ArrayList<String[]>();
		int count = list.size();
		int size;
		if (count % pageSize == 0) {
			size = count / pageSize;
		} else {
			size = count / pageSize + 1;
		}
		for (int i = 0; i < size; i++) {
			List<String> tempList;
			if (i == size - 1) {
				tempList = list.subList(i * pageSize, list.size());
			} else {
				tempList = list.subList(i * pageSize, (i + 1) * pageSize);
			}
			String[] mobiles = new String[tempList.size()];
			for (int j = 0; j < mobiles.length; j++) {
				mobiles[j] = tempList.get(j);
			}
			sList.add(mobiles);
		}
		return sList;
	}

	public static final String replace(String s, String s1, String s2) {

		if (s == null)
			return null;
		int i = 0;
		if ((i = s.indexOf(s1, i)) >= 0) {
			char ac[] = s.toCharArray();
			char ac1[] = s2.toCharArray();
			int j = s1.length();
			StringBuffer stringbuffer = new StringBuffer(ac.length);
			stringbuffer.append(ac, 0, i).append(ac1);
			i += j;
			int k;
			for (k = i; (i = s.indexOf(s1, i)) > 0; k = i) {
				stringbuffer.append(ac, k, i - k).append(ac1);
				i += j;
			}

			stringbuffer.append(ac, k, ac.length - k);
			return stringbuffer.toString();
		} else {
			return s;
		}
	}

	public static final String replaceIgnoreCase(String s, String s1, String s2) {

		if (s == null)
			return null;
		String s3 = s.toLowerCase();
		String s4 = s1.toLowerCase();
		int i = 0;
		if ((i = s3.indexOf(s4, i)) >= 0) {
			char ac[] = s.toCharArray();
			char ac1[] = s2.toCharArray();
			int j = s1.length();
			StringBuffer stringbuffer = new StringBuffer(ac.length);
			stringbuffer.append(ac, 0, i).append(ac1);
			i += j;
			int k;
			for (k = i; (i = s3.indexOf(s4, i)) > 0; k = i) {
				stringbuffer.append(ac, k, i - k).append(ac1);
				i += j;
			}

			stringbuffer.append(ac, k, ac.length - k);
			return stringbuffer.toString();
		} else {
			return s;
		}
	}

	public static final String escapeHTMLTags(String s) {

		if (s == null || s.length() == 0)
			return s;
		StringBuffer stringbuffer = new StringBuffer(s.length());

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '<')
				stringbuffer.append("&lt;");
			else if (c == '>')
				stringbuffer.append("&gt;");
			else
				stringbuffer.append(c);
		}

		return stringbuffer.toString();
	}

	public static String gbToUtf8(String src) {

		byte b[] = src.getBytes();
		char c[] = new char[b.length];
		for (int i = 0; i < b.length; i++)
			c[i] = (char) (b[i] & 0xff);
		return new String(c);
	}

	public static final String ConvertGBK(String s) {

		String s1 = "";
		String s2;
		if (s == null || s.trim().length() == 0)
			return "";
		try {
			s1 = new String(s.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception exception) {
			System.out.println("ConvertGBK():ex=" + exception.toString());
		}
		return s1;
	}

	public static final String NULLToSpace(String s) {

		if (s == null)
			return "";
		else
			return s.trim();
	}

	public static final String toHex(byte abyte0[]) {

		StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
		for (int i = 0; i < abyte0.length; i++) {
			if ((abyte0[i] & 0xff) < 16)
				stringbuffer.append("0");
			stringbuffer.append(Long.toString(abyte0[i] & 0xff, 16));
		}

		return stringbuffer.toString();
	}

	public static boolean hasCn(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		} else {
			if (str.getBytes().length > str.length())
				return true;
			else
				return false;
		}
	}

	public static String numToChinese(int number) {
		String[] chinese = new String[] { "零", "壹", "贰", "弎", "肆", "伍", "陆", "柒", "捌", "玖" };
		return chinese[number];
	}

	public static String getSubStr(String str, int startIndex, int byteNum) throws Exception {
		try {
			if (str == null) {
				return "";
			}
			byte[] b = str.getBytes("GBK");
			if (startIndex >= b.length) {
				return "";
			}
			int index = 0;
			int n = 0;
			for (; n < byteNum && index < b.length; index++) {
				if (b[index] != 0) {
					n++;
				}
			}

			return new String(b, startIndex, index, "GBK");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static int getBodySplitLength(String msg, String split, int count) {
		int length = 0;
		for (int i = 0; i < count; i++) {
			length += msg.indexOf(split) + 1;
			msg = msg.substring(msg.indexOf(split) + 1);
		}
		return length;
	}

	public static List<String> transferStringToArray(String msg, String split) {
		List<String> result = new ArrayList<String>();
		if (isNotEmpty(msg)) {
			String[] strs = msg.split(split);
			for (int i = 0; i < strs.length; i++) {
				result.add(strs[i]);
			}
		}
		return result;
	}

	public static String getDateTimeRandomStr() {
		StringBuffer str = new StringBuffer();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());

		str.append(calendar.get(Calendar.YEAR));
		str.append(((String.valueOf(calendar.get(Calendar.MONTH)).length() < 2)
				? "0" + String.valueOf(calendar.get(Calendar.MONTH)) : String.valueOf(calendar.get(Calendar.MONTH))));
		str.append(((String.valueOf(calendar.get(Calendar.DATE)).length() < 2)
				? "0" + String.valueOf(calendar.get(Calendar.DATE)) : String.valueOf(calendar.get(Calendar.DATE))));
		str.append(((String.valueOf(calendar.get(Calendar.HOUR)).length() < 2)
				? "0" + String.valueOf(calendar.get(Calendar.HOUR)) : String.valueOf(calendar.get(Calendar.HOUR))));
		str.append(((String.valueOf(calendar.get(Calendar.MINUTE)).length() < 2)
				? "0" + String.valueOf(calendar.get(Calendar.MINUTE)) : String.valueOf(calendar.get(Calendar.MINUTE))));
		str.append(((String.valueOf(calendar.get(Calendar.SECOND)).length() < 2)
				? "0" + String.valueOf(calendar.get(Calendar.SECOND)) : String.valueOf(calendar.get(Calendar.SECOND))));
		str.append((new Random()).nextFloat());
		return str.toString();
	}

	public static String trim(String stream, String trimstr) {
		// null或者空字符串的时候不处理
		if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
			return stream;
		}

		// 结束位置
		int epos = 0;

		// 正规表达式
		String regpattern = "[" + trimstr + "]*+";
		Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

		// 去掉结尾的指定字符
		StringBuffer buffer = new StringBuffer(stream).reverse();
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
		}

		// 去掉开头的指定字符
		matcher = pattern.matcher(stream);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			stream = stream.substring(epos);
		}

		// 返回处理后的字符串
		return stream;
	}

	public static String ltrim(String stream, String trimstr) {
		// null或者空字符串的时候不处理
		if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
			return stream;
		}

		// 结束位置
		int epos = 0;

		// 正规表达式
		String regpattern = "[" + trimstr + "]*+";
		Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

		// 去掉结尾的指定字符
		StringBuffer buffer = new StringBuffer(stream).reverse();
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
		}

		// 返回处理后的字符串
		return stream;
	}

	public static String rtrim(String stream, String trimstr) {
		// null或者空字符串的时候不处理
		if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
			return stream;
		}

		// 结束位置
		int epos = 0;

		// 正规表达式
		String regpattern = "[" + trimstr + "]*+";
		Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

		// 去掉结尾的指定字符
		StringBuffer buffer = new StringBuffer(stream).reverse();
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
		}

		// 返回处理后的字符串
		return stream;
	}
}
