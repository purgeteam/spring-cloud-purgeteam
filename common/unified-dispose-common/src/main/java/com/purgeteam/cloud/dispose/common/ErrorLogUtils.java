package com.purgeteam.cloud.dispose.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日常日志
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 0.4.0
 */
public class ErrorLogUtils {

    private final static Logger log = LoggerFactory.getLogger(ErrorLogUtils.class);

    /**
     * 打印异常
     *
     * @param e 异常
     */
    public static void showError(Throwable e) {
        log.error(getErrorMsg(e, -1));
    }


    /**
     * 打印异常
     *
     * @param e   异常
     * @param row 打印行数
     */
    public static void showError(Throwable e, int row) {
        log.error(getErrorMsg(e, row));
    }

    /**
     * 获取异常消息
     *
     * @param e   异常
     * @param row 行数
     * @return 异常消息
     */
    public static String getErrorMsg(Throwable e, int row) {
        int i = 0;
        StringBuilder sb = new StringBuilder("\n").append(e.toString());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            if (row != -1 && row <= i) {
                break;
            }
            sb.append("\n").append("\t at ").append(stackTraceElement.toString());
            i++;
        }
        return sb.toString();
    }
}
