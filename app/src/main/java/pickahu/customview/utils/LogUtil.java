package pickahu.customview.utils;

import android.util.Log;


/**
 * Created by Administrator on 2017/3/6.
 */
public class LogUtil {
    /**
     * 默认的日志Tag标签.
     */
    static final String DEFAULT_TAG = "pickahu";
    /**
     * 此常量用于控制是否打日志到Logcat中 release版本中本变量应置为false.
     */
    public static final boolean LOGGABLE = true;

    /**
     * 打印debug级别的log.
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void d(String tag, String str) {
        if (LOGGABLE) {
            printLog(tag, str, Log.DEBUG, false);
        }
    }
    /**
     * 打印debug级别的log.
     *
     * @param str 内容
     */
    public static void d(String str) {
        if (LOGGABLE) {
            printLog(DEFAULT_TAG, str, Log.DEBUG, false);
        }
    }

    /**
     * 打印warning级别的log.
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void w(String tag, String str) {
        if (LOGGABLE) {
            printLog(tag, str, Log.WARN, false);
        }
    }


    /**
     * 打印warning级别的log.
     *
     * @param str 内容
     */
    public static void w(String str) {
        if (LOGGABLE) {
            printLog(DEFAULT_TAG, str, Log.WARN, false);
        }
    }

    /**
     * 打印error级别的log.
     *
     * @param tag tag标签
     * @param msg 内容
     * @param e   错误对象.
     */
    public static void e(String tag, String msg, Throwable e) {
        if (LOGGABLE) {
            Log.e(tag, msg, e);
        }
    }

    /**
     * 打印error级别的log.
     *
     * @param tag tag标签
     * @param msg 内容
     */
    public static void e(String tag, String msg) {
        if (LOGGABLE) {
            printLog(tag, msg, Log.ERROR, false);
        }
    }

    /**
     * 打印error级别的log.
     *
     * @param str 内容
     */
    public static void e(String str) {
        if (LOGGABLE) {
            printLog(DEFAULT_TAG, str, Log.ERROR, false);
        }
    }

    /**
     * 打印info级别的log.
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void i(String tag, String str) {
        if (LOGGABLE) {
            printLog(tag, str, Log.INFO, false);
        }
    }


    /**
     * 打印info级别的log.
     *
     * @param str 内容
     */
    public static void i(String str) {
        if (LOGGABLE) {
            printLog(DEFAULT_TAG, str, Log.INFO, false);
        }
    }

    /**
     * 打印verbose级别的log.
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void v(String tag, String str) {
        if (LOGGABLE) {
            printLog(tag, str, Log.VERBOSE, false);
        }
    }



    /**
     * 打印verbose级别的log.
     *
     * @param str 内容
     */
    public static void v(String str) {
        if (LOGGABLE) {
            printLog(DEFAULT_TAG, str, Log.VERBOSE, false);
        }
    }


    /**
     * 普通日志带行号输出
     * @param msg
     */
    /**
     * 日志输出
     *
     * @param tag            标签
     * @param msg            内容
     * @param logLevel       LOG等级
     * @param isOutputTraces 是否输出调用链
     */
    private static void printLog(String tag, String msg, int logLevel, boolean isOutputTraces) {
        Throwable state = new Throwable(msg);
        StackTraceElement[] stackTraces = state.getStackTrace();
        StringBuffer sBu = new StringBuffer();
        if (!isOutputTraces) {
            StackTraceElement stackTrace = stackTraces[2]; // 根据下标定位层级。
            sBu.append("(").append(stackTrace.getFileName()).append(":").append(stackTrace
                    .getLineNumber()).append("):").append(msg);
        } else {
            for (int i = 0; i < stackTraces.length; i++) {
                if (i > 2) {
                    sBu.append(stackTraces[i].toString()).append("\n");
                } else if (i == 2) {
                    StackTraceElement stackTrace = stackTraces[i];
                    sBu.append("(").append(stackTrace.getFileName()).append(":").append(stackTrace
                            .getLineNumber()).append("):").append(msg).append("\n");
                }
            }
        }
        switch (logLevel) {
            case Log.INFO :
                Log.d(tag, sBu.toString());
                break;
            case Log.VERBOSE :
                Log.v(tag, sBu.toString());
                break;
            case Log.DEBUG :
                Log.d(tag, sBu.toString());
                break;
            case Log.WARN :
                Log.w(tag, sBu.toString());
                break;
            case Log.ERROR :
                Log.e(tag, sBu.toString());
                break;
            default:
                break;
        }
    }


}
