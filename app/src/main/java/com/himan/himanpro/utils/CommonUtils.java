package com.himan.himanpro.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用工具类
 * @author Administrator
 *
 */
public class CommonUtils {
	private Context context;

	private CommonUtils(Context context) {
		this.context = context;
	}


	/**
	 * 判断程序是否在后台运行
	 * @param context
	 * @return
	 */
	public static boolean isBackground(Context context) {  
        ActivityManager activityManager = (ActivityManager) context  
                .getSystemService(Context.ACTIVITY_SERVICE);  
        List<RunningAppProcessInfo> appProcesses = activityManager  
                .getRunningAppProcesses();  
        for (RunningAppProcessInfo appProcess : appProcesses) {  
            if (appProcess.processName.equals(context.getPackageName())) {  
                if (appProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {  
                    return true;  
                } else {  
                    return false;  
                }  
            }  
        }
        return false;  
    }  
	
/**
 * 网络连接是否畅通
 * @return
 */
	public boolean isNetWorkAvailable() {
		boolean result = false;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null) {
			result = netinfo.isConnected();
		}
		return result;
	}


/**
 * 判断是否是数字
 * @param str
 * @return
 */
	public static boolean isNumeric(String str) {
		boolean result = str.matches("^[0-9]*$");
		return result;
	}
/**
 * 验证邮箱格式
 * @param email 输入邮箱
 * @return true 正确邮箱 false 错误邮箱
 */
	public static boolean checkMail(String email) {
		boolean flag = false;
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		flag = matcher.matches();
		return flag;
	}
/**
 * 验证手机格式
 * @param mobile 手机号
 * @return true 正确手机号 false 错误手机号
 */
	public static boolean checkMobile(String mobile) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
/**
 * 空值判断
 * @param data 字符串
 * @return true 不为空 false 为空或者null
 */
	public static boolean isNotEmpty(Object data) {
		if (data != null && !"".equals(data)) {
			return true;
		}
		return false;
	}
/**
 * 获取库位
 * @param locationCode 库位条码 
 * @return 库位
 */
	public static String getKuWei(String locationCode) {
		String KuWei = "";
		if (isNotEmpty(locationCode)) {
			KuWei = Integer.parseInt(locationCode.substring(7, 11)) + "";
		}
		return KuWei;
	}
	/**
	 * 根据库位码获取楼层
	 * @param locationCode
	 * @return
	 */
	public static String getLouCeng(String locationCode) {
		String LouCeng = "";
		if (isNotEmpty(locationCode)) {
			LouCeng = Integer.parseInt(locationCode.substring(11, 12)) + "";
		}
		return LouCeng;
	}
	/**
	 * 根据库位码获取通道号
	 * @param locationCode
	 * @return
	 */
	public static String getTongDaoHao(String locationCode) {
		String TongDaoHao = "";
		if (isNotEmpty(locationCode)) {
			TongDaoHao = Integer.parseInt(locationCode.substring(2, 6)) + "";
		}
		return TongDaoHao;
	}
/**
 * 获取格口层数
 * @param geKou 格口条码
 * @return 格口层数
 */
	public static String getGeKouCengShu(String geKou) {
		if (CommonUtils.isNotEmpty(geKou)) {
			return geKou.substring(geKou.length() - 1, geKou.length());
		}
		return "";
	}
/**
 * 获取格口号
 * @param getKou 格口条码
 * @return 格口号
 */
	public static String getGeKouNo(String getKou) {
		if (CommonUtils.isNotEmpty(getKou)) {
			return getKou.substring(getKou.length() - 5, getKou.length() - 1);
		}
		return "";
	}
/**
 * 精确到小数点后两位
 * @param num double类型的浮点数
 * @return 保留两位小数的字符串
 */
	public static String getDoubleFormat(double num) {
		DecimalFormat df = new DecimalFormat("###0.00");
		return df.format(num);
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String dateUtil() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sim.format(cal.getTime()) + "";
		return date;
	}


	/**
	 * 手机号码格式化加横杠
	 * 
	 * @param etMobile
	 *            ： 手机号Edt
	 */
	public static void mobileFormat(final EditText etMobile) {
		etMobile.addTextChangedListener(new TextWatcher() {
			int beforeTextLength = 0;
			int onTextLength = 0;
			boolean isChanged = false;

			int location = 0;// 记录光标的位置
			private char[] tempChar;
			private StringBuffer buffer = new StringBuffer();
			int konggeNumberB = 0;

			boolean isDelete = false;

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (1 == s.length()) {
					if (!"1".equals(s.toString())) {
						isDelete = true;
						return;
					} else {
						isDelete = false;
					}
				} else {
					isDelete = false;
				}
				onTextLength = s.length();
				buffer.append(s.toString());
				if (onTextLength == beforeTextLength || onTextLength <= 3
						|| isChanged) {
					isChanged = false;
					return;
				}
				isChanged = true;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				beforeTextLength = s.length();
				if (buffer.length() > 0) {
					buffer.delete(0, buffer.length());
				}
				konggeNumberB = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == '-') {
						konggeNumberB++;
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

				if (isDelete) {
					etMobile.setText("");
					isDelete = false;
					return;
				}

				if (isChanged) {
					location = etMobile.getSelectionEnd();
					int index = 0;
					while (index < buffer.length()) {
						if (buffer.charAt(index) == '-') {
							buffer.deleteCharAt(index);
						} else {
							index++;
						}
					}

					index = 0;
					int konggeNumberC = 0;
					while (index < buffer.length()) {
						Object obj = buffer.charAt(0);
						// 可以输入0,1,4开头的号码
						// Character zero = '0';
						Character first = '1';
						// Character forth = '4';
						// if (0 == zero.compareTo(buffer.charAt(0))) {
						// if ((index == 4)) {
						// buffer.insert(index, '-');
						// konggeNumberC++;
						// }
						// }
						if (0 == first.compareTo(buffer.charAt(0))) {
							if ((index == 3 || index == 8 || index == 13)) {
								buffer.insert(index, '-');
								konggeNumberC++;
							}
						}
						// if (0 == forth.compareTo(buffer.charAt(0))) {
						// if ((index == 3 || index == 7 || index == 12)) {
						// buffer.insert(index, '-');
						// konggeNumberC++;
						// }
						// }

						index++;
					}

					if (konggeNumberC > konggeNumberB) {
						location += (konggeNumberC - konggeNumberB);
					}

					tempChar = new char[buffer.length()];
					buffer.getChars(0, buffer.length(), tempChar, 0);
					String str = buffer.toString();
					if (location > str.length()) {
						location = str.length();
					} else if (location < 0) {
						location = 0;
					}

					etMobile.setText(str);
					Editable etable = etMobile.getText();
					Selection.setSelection(etable, location);
					isChanged = false;
				}
			}
		});
	}

	/**
	 * 将Boolean类型转换为int类型(服务器特殊要求）
	 * @param b 布尔类型
	 * @return true 1 false 0
	 */
	public static int true1false0(boolean b) {
		if (b)
			return 1;
		else
			return 0;
	}


	/**
	 * 光标跳转
	 * 
	 * @param edittext1
	 *            ：原光标所在的edt
	 * @param edittext2
	 *            ：要跳转的edt
	 */
	public static void edtFocus1ToFocus2(EditText edittext1, EditText edittext2) {
		// 显示2的光标
		edittext2.requestFocus();
		// edittext2.setCursorVisible(true);
		// 隐藏1的光标
		edittext1.clearFocus();
		// edittext1.setCursorVisible(false);
	}
/**
 * dp转px
 * @param context 
 * @param dpValue dp
 * @return px
 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

/**
 * px转dp
 * @param context 
 * @param pxValue px
 * @return dp
 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 部分字符颜色变更
	 */
	public static SpannableStringBuilder changeTextColor(String strs,
			int color, int start, int end) {
		SpannableStringBuilder style = new SpannableStringBuilder(strs);
		style.setSpan(new ForegroundColorSpan(color), start, end,
				Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		return style;
	}

	public static SpannableStringBuilder changeTextColor(String strs, int color) {
		return changeTextColor(strs, color, 0, strs.length() - 1);
	}
/**
 * 检查是否插入SD卡
 * @param mContext 
 * @return ture 已插入 false 未插入
 */
	public static boolean checkSDCard(Context mContext) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { // 判断是否存在SD卡
			return true;
		} else {
			Toast.makeText(mContext, "检测到手机没有存储卡,请安装了内存卡后再升级。",
					Toast.LENGTH_LONG).show();
			return false;
		}
	}


    //汉字返回拼音，字母原样返回，都转换为小写(默认取得的拼音全大写)   
    public static String getPinYin(String input) {  
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(input);
        StringBuilder sb = new StringBuilder();  
        if (tokens != null && tokens.size() > 0) {  
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target);  
                } else {  
                    sb.append(token.source);  
                }  
            }  
        }  
        return sb.toString().toLowerCase();  
    }  
    
    //汉字返回拼音首字母，字母原样返回，都转换为小写(默认取得的拼音全大写)   
    public static String getPinYinHead(String input) {  
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(input);
        StringBuilder sb = new StringBuilder();  
        if (tokens != null && tokens.size() > 0) {  
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target.charAt(0));  
                } else {  
                    sb.append(token.source.charAt(0));  
                }  
            }  
        }  
        return sb.toString().toLowerCase();  
    } 
    
    public static byte[] change(String inputStr) {
	    byte[] result = new byte[inputStr.length() / 2];
	    for (int i = 0; i < inputStr.length() / 2; ++i) 
		        result[i] = (byte)(Integer.parseInt(inputStr.substring(i * 2, i * 2 +2), 16) & 0xff);
	    return result;
    }


	//创建文件夹
	private void makeDir(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
