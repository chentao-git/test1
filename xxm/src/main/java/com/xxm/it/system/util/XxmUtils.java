package com.xxm.it.system.util;

import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.xxm.it.system.vo.BaseDataVO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
public class XxmUtils {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(XxmUtils.class);

	/**
	 * 获取当前日期
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		return getCurrentDate(XxmConstant.BaseDataConstant.FORMATE_DATA1);
	}

	/**
	 * 获取当前日期
	 * 
	 * @param 自定义格式
	 * @return String
	 */
	public static String getCurrentDate(String format) {
		Calendar instance = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(instance.getTime());
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            需要格式化的字符串
	 * @param originalFormat
	 *            原字符串格式
	 * @param targetFormat
	 *            目标字符串格式
	 * @return
	 */
	public static String formatDate(String date, String originalFormat, String targetFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(originalFormat);
		try {
			return new SimpleDateFormat(targetFormat).format(sdf.parse(date));
		} catch (ParseException e) {
			logger.error("formatDate() error." + e.getMessage());
		}
		return date;
	}

	/**
	 * BASE64字符串转化成图片
	 * 
	 * @param imageData
	 * @return
	 * @throws Exception
	 */
	public static void generateImage(String imageData, String path) throws Exception {
		// 图像数据为空
		if (imageData != null && !"".equals(imageData)) {
			BASE64Decoder decoder = new BASE64Decoder();
			OutputStream out = null;
			try {
				// Base64解码
				byte[] b = decoder.decodeBuffer(imageData);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				out = new FileOutputStream(path);
				out.write(b);
				out.flush();
				logger.info("图片生成成功！" + path);
			} catch (Exception e) {
				logger.error("generateImage() 处理图片失败！ " + e.getMessage());
				throw new Exception("处理图片失败！" + e.getMessage());
			} finally {
				if (null != out) {
					try {
						out.close();
					} catch (IOException e) {
						logger.error("关闭图片流失败" + e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * 图片转化成base64字符串
	 * 
	 * @param imgFile
	 * @return String
	 * @throws Exception
	 */
	public static String getImageStr(String imgFile) throws Exception {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			logger.error("GetImageStr() 处理图片失败！ " + e.getMessage());
			throw new Exception("处理图片失败！" + e.getMessage());
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("关闭图片流失败" + e.getMessage());
				}
			}
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * Base64字符串转 二进制流
	 *
	 * @param base64String
	 *            Base64
	 * @return base64String
	 * @throws IOException
	 *             异常
	 */
	public static byte[] getStringImageBytes(String base64String) throws IOException {
		BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		return base64String != null ? decoder.decodeBuffer(base64String) : null;
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param path
	 *            将要删除的文件目录路劲
	 * @return boolean
	 * 
	 */
	public static boolean deleteDir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			return true;
		}
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		logger.info("delete temp file:" + path);
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean
	 * 
	 */
	public static boolean deleteDir(File dir) {
		if (!dir.exists()) {
			return true;
		}
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		logger.info("delete temp file:" + dir.getPath());
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return String
	 */
	public static String readFileReturnString(String fileName) {
		StringBuffer readFileReturnStringBuffer = XxmUtils.readFileReturnStringBuffer(fileName);
		if (null != readFileReturnStringBuffer && !"".equals(readFileReturnStringBuffer)) {
			return XxmUtils.readFileReturnStringBuffer(fileName).toString();
		}
		return null;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return StringBuffer
	 */
	public static StringBuffer readFileReturnStringBuffer(String fileName) {
		File file = new File(fileName);
		FileInputStream fis = null;
		InputStreamReader read = null;
		BufferedReader reader = null;
		StringBuffer result = new StringBuffer();
		try {
			fis = new FileInputStream(file);
			read = new InputStreamReader(fis, XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
			reader = new BufferedReader(read);
			int line = 1;
			String tempString = "";
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				logger.debug("line " + line + ": " + tempString);
				result.append(tempString.trim());
				line++;
			}
		} catch (IOException e) {
			result = null;
			logger.error("readFileByLines() error." + e.getMessage());
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e1) {
					logger.error("fis close error." + e1.getMessage());
				}
			}
			if (null != read) {
				try {
					read.close();
				} catch (IOException e1) {
					logger.error("read close error." + e1.getMessage());
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					logger.error("reader close error." + e1.getMessage());
				}
			}
		}
		return result;
	}

	/**
	 * 生成pdf文件
	 * 
	 * @param outputPDFFile
	 * @param strReader
	 * @throws Exception
	 */
	public static void generatePDF(File outputPDFFile, StringReader strReader) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outputPDFFile);
			PD4ML pd4ml = new PD4ML();
			pd4ml.setPageInsets(new Insets(20, 10, 10, 10));
			pd4ml.setHtmlWidth(950);
			pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
			pd4ml.useTTF("java:fonts", true);
			pd4ml.setDefaultTTFs("KaiTi_GB2312", "KaiTi_GB2312", "KaiTi_GB2312");
			pd4ml.enableDebugInfo();
			pd4ml.render(strReader, fos);
		} catch (FileNotFoundException e) {
			logger.error("readFileByLines() error." + e.getMessage());
		} catch (InvalidParameterException e) {
			logger.error("readFileByLines() error." + e.getMessage());
		} catch (MalformedURLException e) {
			logger.error("readFileByLines() error." + e.getMessage());
		} catch (IOException e) {
			logger.error("readFileByLines() error." + e.getMessage());
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					logger.error("fos close error." + e1.getMessage());
				}
			}
		}
	}

	/**
	 * 复制文件目录
	 * 
	 * @param file
	 *            源目录
	 * @param toFile
	 *            目标目录
	 * @throws Exception
	 */
	public static void copy(File file, File toFile) throws Exception {
		String filepath = file.getAbsolutePath().replaceAll("\\\\", "/");
		String toFilepath = toFile.getAbsolutePath().replaceAll("\\\\", "/");
		int lastIndexOf = filepath.lastIndexOf("/");
		toFilepath = toFilepath + filepath.substring(lastIndexOf, filepath.length());
		if (file.isDirectory()) {
			File copy = new File(toFilepath);
			// 复制文件夹
			if (!copy.exists()) {
				copy.mkdirs();
			}
			// 遍历文件夹
			for (File f : file.listFiles()) {
				copy(f, copy);
			}
		} else {
			if (toFile.isDirectory()) {
				// 写文件
				writeFile(file, new File(toFilepath));
			} else {
				// 写文件
				writeFile(file, toFile);
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param file
	 *            源文件对象
	 * @param toFile
	 *            目标文件对象
	 */
	public static void writeFile(File file, File toFile) {
		int a = 0;
		byte[] b = new byte[1024];
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			logger.info("开始复制文件：" + file.getName());
			logger.info("原始文件路径：【" + file.getAbsolutePath() + "】");
			logger.info("目标文件路径：【" + toFile.getAbsolutePath() + "】");
			fis = new FileInputStream(file);
			fos = new FileOutputStream(toFile);
			while ((a = fis.read(b)) != -1) {
				fos.write(b, 0, a);
			}
			fos.flush();
			logger.info("结束复制文件：" + file.getName());
		} catch (FileNotFoundException e) {
			logger.error("writeFile() error." + e.getMessage());
		} catch (IOException e) {
			logger.error("writeFile() error." + e.getMessage());
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error("fis close error." + e.getMessage());
				}
			}
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error("fos close error." + e.getMessage());
				}
			}
		}
	}

	/**
	 * 将字符串写入文件
	 * 
	 * @param filePath
	 * @param strContent
	 */
	public static void WriteStringToFile(String filePath, String strContent) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(filePath);
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(strContent);// 往已有的文件上添加字符串
			bw.close();
			fw.close();
			logger.info("文件写入完毕！" + file.getName());
		} catch (FileNotFoundException e) {
			logger.error("writeFile() error." + e.getMessage());
		} catch (IOException e) {
			logger.error("writeFile() error." + e.getMessage());
		} finally {
			if (null != fw) {
				try {
					fw.close();
				} catch (IOException e) {
					logger.error("fis close error." + e.getMessage());
				}
			}
			if (null != bw) {
				try {
					bw.close();
				} catch (IOException e) {
					logger.error("fos close error." + e.getMessage());
				}
			}
		}
	}

	// 过滤utf-8 4字节字符
	public static String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("?");
				return source;
			}
			return source;
		}
		return source;
	}

	/**
	 * 读取属性文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties loadProperties(String fileName) {
		Properties prop = new Properties();
		InputStream resourceAsStream = null;
		InputStreamReader inputStreamReader = null;
		try {
			logger.error(" load Properties for:" + fileName);
			resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			inputStreamReader = new InputStreamReader(resourceAsStream,
					XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);// 设置编码格式
			prop.load(inputStreamReader);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (null != resourceAsStream) {
				try {
					resourceAsStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return prop;
	}

	/**
	 * 数值的百分比计算
	 * 
	 * @param num1
	 *            被除数
	 * @param num2
	 *            除数
	 * @return 百分比结果
	 */
	public static String numPercentageCalculation(double num1, double num2) {
		// 除数不能为0
		if (num2 != 0) {
			double percent = num1 / num2;
			// 获取格式化对象
			NumberFormat nt = NumberFormat.getPercentInstance();
			// 设置百分数精确度2即保留两位小数
			nt.setMinimumFractionDigits(2);
			// 最后格式化并输出
			return nt.format(percent);
		}
		return "";
	}

	/**
	 * 从缓存获取配置信息
	 * 
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            缓存key
	 * @return pro 配置对象
	 */
	public static Properties getPropertiesConfig(String cacheName, String key) {
		Properties pro = null;
		if (null == cacheName || "".equals(cacheName)) {
			return pro;
		}
		if (null == key || "".equals(key)) {
			return pro;
		}
		logger.debug("--------------get cache config----------- cacheName:" + cacheName + " key:" + key);
		// 从缓存中获取配置
		Object object = XxmEhcacheUtils.get(cacheName, key);
		if (null != object) {
			pro = (Properties) object;
		}
		return pro;
	}

	/**
	 * 判断值能不能被除
	 * 
	 * @param value
	 *            除数
	 * 
	 * @return Boolean
	 */
	public static Boolean judgeDivision(String value) {
		Boolean isDivision = false;
		if (null != value && !"".equals(value)) {
			if (value.matches("[0-9]+")) {// 判断是否为整数
				if (!"0".equals(value)) {
					isDivision = true;
				}
			} else if (value.indexOf(".") > 0) {
				value = value.replaceAll("0+?$", "");// 去掉多余的0
				value = value.replaceAll("[.]$", "");// 如最后一位是.则去掉
				if (!"0".equals(value)) {
					isDivision = true;
				}
			}
		}
		return isDivision;
	}

	/**
	 * 从字符中获取数字（包含小数点）
	 * 
	 * @param value
	 * 
	 * 
	 * @return String
	 */
	public static String numberExtract(String value) {
		if (null != value && !"".equals(value)) {
			char[] b = value.toCharArray();
			String result = "";
			String Str = "0123456789.";
			for (int i = 0; i < b.length; i++) {
				if ((Str).indexOf(b[i] + "") != -1) {
					result += b[i];
				}
			}
			return result;
		} else {
			return null;
		}
	}

	/**
	 * 数组去重复
	 * 
	 * @param array
	 *            被去重的数组
	 * @return 去重后的数组
	 */
	public static String[] arrayDistinct(String[] array) {
		if (null != array && array.length > 0) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				for (int j = i + 1; j < array.length; j++) {
					if (array[i].equals(array[j])) {
						j = ++i;
					}
				}
				list.add(array[i]);
			}
			String[] newStr = (String[]) list.toArray(new String[list.size()]);
			// Arrays.sort(newStr);// 排序
			return newStr;
		}
		return null;
	}

	/**
	 * 获得纯数字格式的时间
	 * 
	 * @param
	 * 
	 * @return
	 */
	public static String getDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 判断空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String isEmptyChar(String str) {
		if (null != str && !"".equals(str)) {
			return str;
		}
		return "";
	}

	/**
	 * 根据基础数据code从缓存中获取基础数据
	 * 
	 * @param baseCode
	 *            基础数据编码
	 * @param baseCodeValue
	 *            基础数据编码值
	 * @return BaseDataVO 基础数据对象
	 */
	public static BaseDataVO getBaseDataByCodeEhcache(String baseCode, String baseCodeValue) {
		// 放入缓存中
		Object object = XxmEhcacheUtils.get(XxmEhcacheUtils.SHARE_CONFIG_CACHE, baseCode);
		if (null != object) {
			BaseDataVO baseDataVO = (BaseDataVO) object;
			List<BaseDataVO> childrenList = baseDataVO.getChildrenList();
			if (null != childrenList && childrenList.size() > 0) {
				for (BaseDataVO childBaseDataVO : childrenList) {
					if (baseCodeValue.equals(childBaseDataVO.getBaseCodeValue())) {
						return childBaseDataVO;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 根据基础数据code从缓存中获取基础数据属性（扩展字段1）值
	 * 
	 * @param baseCode
	 *            基础数据编码
	 * @param baseCodeValue
	 *            基础数据编码值
	 * @return BaseDataVO 基础数据对象
	 */
	public static String getBaseDataExtendField1(String baseCode, String baseCodeValue) {
		BaseDataVO baseVO = getBaseDataByCodeEhcache(baseCode, baseCodeValue);
		if (null != baseVO) {
			return baseVO.getExtendField1();
		}
		return null;
	}

	/**
	 * 根据基础数据code从缓存中获取基础数据属性（扩展字段2）值
	 * 
	 * @param baseCode
	 *            基础数据编码
	 * @param baseCodeValue
	 *            基础数据编码值
	 * @return BaseDataVO 基础数据对象
	 */
	public static String getBaseDataExtendField2(String baseCode, String baseCodeValue) {
		BaseDataVO baseVO = getBaseDataByCodeEhcache(baseCode, baseCodeValue);
		if (null != baseVO) {
			return baseVO.getExtendField2();
		}
		return null;
	}

	/**
	 * 根据基础数据code从缓存中获取基础数据属性（扩展字段3）值
	 * 
	 * @param baseCode
	 *            基础数据编码
	 * @param baseCodeValue
	 *            基础数据编码值
	 * @return BaseDataVO 基础数据对象
	 */
	public static String getBaseDataExtendField3(String baseCode, String baseCodeValue) {
		BaseDataVO baseVO = getBaseDataByCodeEhcache(baseCode, baseCodeValue);
		if (null != baseVO) {
			return baseVO.getExtendField3();
		}
		return null;
	}

	/**
	 * 根据基础数据code从缓存中获取基础数据属性（扩展字段4）值
	 * 
	 * @param baseCode
	 *            基础数据编码
	 * @param baseCodeValue
	 *            基础数据编码值
	 * @return BaseDataVO 基础数据对象
	 */
	public static String getBaseDataExtendField4(String baseCode, String baseCodeValue) {
		BaseDataVO baseVO = getBaseDataByCodeEhcache(baseCode, baseCodeValue);
		if (null != baseVO) {
			return baseVO.getExtendField4();
		}
		return null;
	}

	public static void main(String[] args) {
		String str = "17,19,18,11,12,16,3,4,5,17,19,18,27,21,28,20,23,24,30,31,32,28,20,17,19,18,27,21,28,20,29,25,23,24,30,31,32,11,12,13,16,6,7,8,9,15,10,22,3,4,5";
		String[] s = str.split(",");
		String[] arrayDistinct = XxmUtils.arrayDistinct(s);
		System.out.println(Arrays.toString(arrayDistinct));
	}

}
